package com.example.carshopwithspringboot.controller;

import com.example.carshopwithspringboot.dto.CarDto;
import com.example.carshopwithspringboot.model.Car;
import com.example.carshopwithspringboot.service.impl.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "CarController", description = "This is for manipulation of Car")
@RestController()
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            description = "This will add new car",
            responses = {
                    @ApiResponse(description = "Car created successfully", responseCode = "201"),
                    @ApiResponse(description = "Bad request something was wrong", responseCode = "400"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "You should give me car in request body and I will return it in json"
            )
    )
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> addCar(
            @Valid
            @RequestBody CarDto carDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Car add = carService.add(carDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @Operation(
            description = "This will edit exist car",
            responses = {
                    @ApiResponse(description = "Car updated successfully", responseCode = "202"),
                    @ApiResponse(description = "Bad request something was wrong", responseCode = "400"),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true, description = "You should give me car in request body and I will return it in json"
            ),
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of car which you want update"
                    )
            }
    )
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> editCar(
            @Valid
            @PathVariable("id") Integer id,
            @RequestBody CarDto carDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carDto.setId(id);
        Car update = carService.update(carDto);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @Operation(description = "This is for deleting car",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
            },
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of car which you want delete"
                    )
            })
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteCar(
            @PathVariable("id") Integer carId) {
        Integer delete = carService.delete(carId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @Operation(description = "This is for getting car",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
            },
            parameters = {
                    @Parameter(
                            name = "id", required = true, description = "This is id of car which you want get"
                    )
            })
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getCar(
            @PathVariable("id") Integer id
    ) {
        Optional<Car> car = carService.get(id);
        return new ResponseEntity<>(car.orElse(null), HttpStatus.OK);
    }

    @Operation(description = "This is for getting all cars",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Access denied"),
            }
    )
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> all = carService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

}
