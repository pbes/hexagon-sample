package hu.besp.hexagon.rest;

import hu.besp.hexagon.core.Place;
import hu.besp.hexagon.core.PlaceService;
import hu.besp.hexagon.rest.dto.PlaceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlaceController {

    private final PlaceService placeService;
    private final ModelMapper modelMapper;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.modelMapper = new ModelMapper();
        this.placeService = placeService;
    }

    @GetMapping("/places")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PlaceDTO>> findPlaces() {
        var placeList = convertToPlaceDTOList(placeService.getAllPlaces());
        return ResponseEntity.ok(placeList);
    }

    private List<PlaceDTO> convertToPlaceDTOList(List<Place> places) {
        return places
                .stream()
                .map(e -> this.modelMapper.map(e, PlaceDTO.class))
                .collect(Collectors.toList());
    }

}
