package hu.besp.hexagon.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return this.placeRepository.findAll();
    }

    public void createPlace(String name) {
        var place = new Place(name);
        this.placeRepository.createPlace(place);
    }
}
