package hu.besp.hexagon.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(PlaceServiceTestConfiguration.class)
public class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void whenReturnPlaceListIsEmpty_thenNoPlacesShouldBeReturned() {
        List<Place> places = new ArrayList<>();
        when(placeRepository.findAll()).thenReturn(places);

        assertEquals(0, placeService.getAllPlaces().size());
    }

    @Test
    public void whenReturnPlaceList_thenPlacesShouldBeReturned() {
        List<Place> places = Arrays.asList(new Place("Budapest"), new Place("London"));
        when(placeRepository.findAll()).thenReturn(places);

        assertEquals(places.size(), placeService.getAllPlaces().size());
        assertEquals(places.get(0).getName(), placeService.getAllPlaces().get(0).getName());
        assertEquals(places.get(1).getName(), placeService.getAllPlaces().get(1).getName());
    }

    @Test
    public void whenPlaceIsCreated_thenRepositoryShouldBeCalled() {
        placeService.createPlace("Budapest");
        verify(placeRepository).createPlace(new Place("Budapest"));
    }
}
