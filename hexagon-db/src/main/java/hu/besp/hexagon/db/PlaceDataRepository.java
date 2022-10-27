package hu.besp.hexagon.db;

import hu.besp.hexagon.core.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceDataRepository extends CrudRepository<Place, Long> {
}
