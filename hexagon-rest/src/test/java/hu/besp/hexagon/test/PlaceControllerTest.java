package hu.besp.hexagon.test;

import hu.besp.hexagon.core.Place;
import hu.besp.hexagon.core.PlaceService;
import hu.besp.hexagon.rest.PlaceController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {PlaceController.class})
@EnableWebMvc
@AutoConfigureMockMvc
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceService placeService;

    @Test
    public void whenNoPlacesAreReturned_thenJsonValueShouldHaveZeroLength() throws Exception {
        when(placeService.getAllPlaces()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/places").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    @Test
    public void whenPlacesAreReturned_thenJsonValueShouldHaveTheSameLengthAndSameData() throws Exception {
        var budapest = new Place("Budapest");
        var gardony = new Place("Gárdony");

        when(placeService.getAllPlaces()).thenReturn(Arrays.asList(budapest, gardony));

        mockMvc.perform(get("/api/places").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is(gardony.getName())));
    }
}
