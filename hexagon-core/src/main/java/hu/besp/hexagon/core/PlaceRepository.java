package hu.besp.hexagon.core;

import java.util.List;

public interface PlaceRepository  {
    List<Place> findAll();

    void createPlace(Place place);
}
