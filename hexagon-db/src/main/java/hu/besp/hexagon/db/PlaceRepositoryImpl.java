package hu.besp.hexagon.db;

import hu.besp.hexagon.core.Place;
import hu.besp.hexagon.core.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceDataRepository placeDataRepository;

    @Autowired
    public PlaceRepositoryImpl(PlaceDataRepository placeDataRepository) {
        this.placeDataRepository = placeDataRepository;
    }

    @Override
    public List<Place> findAll() {
        var iteratorResult = this.placeDataRepository.findAll();
        return StreamSupport.stream(iteratorResult.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void createPlace(Place place) {
        this.placeDataRepository.save(place);
    }
}
