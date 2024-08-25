package response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.impl.CarService;

import java.util.List;

@RestController()
@RequestMapping("/cars")
public class CarController {
    private final ObjectMapper mapper;
    private final CarService carService;

    public CarController(ObjectMapper mapper, CarService carService) {
        this.mapper = mapper;
        this.carService = carService;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> addCar(@RequestBody String car) {
        try {
            Car car1 = mapper.readValue(car, Car.class);
            carService.add(car1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> editCar(@RequestBody String car) {
        try {
            Car car1 = mapper.readValue(car, Car.class);
            carService.update(car1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> deleteCar(@RequestBody String car) {
        try {
            Car car1 = mapper.readValue(car, Car.class);
            carService.delete(car1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCars() {
        List<Car> all = carService.findAll();
        try {
            String json = mapper.writeValueAsString(all);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getByDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCarByDetails(
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam Integer year) {
        Car carByDetails = (Car) carService.getCarByDetails(make, model, year);
        try {
            String json = mapper.writeValueAsString(carByDetails);
            if (carByDetails != null) {
                return new ResponseEntity<>(json, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
