package hu.besp.hexagon.app;

import hu.besp.hexagon.core.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "hu.besp.hexagon"})
@EnableJpaRepositories(basePackages = { "hu.besp.hexagon"})
@EntityScan(basePackages = { "hu.besp.hexagon"})
public class HexagonApp implements CommandLineRunner {

    @Autowired
    private PlaceService placeService;

    public static void main(String[] args) {
        SpringApplication.run(HexagonApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (placeService.getAllPlaces().size() < 1) {
            placeService.createPlace("Budapest");
            placeService.createPlace("Gárdony");
            placeService.createPlace("Szeged");
        }
    }
}
