package hu.besp.hexagon.core;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PlaceServiceTestConfiguration {

    @Autowired
    private PlaceRepository placeRepository;

    @Bean
    public PlaceService placeService() {
        return new PlaceService(placeRepository);
    }

    @Bean
    public PlaceRepository placeRepository() {
        return Mockito.mock(PlaceRepository.class);
    }

}
