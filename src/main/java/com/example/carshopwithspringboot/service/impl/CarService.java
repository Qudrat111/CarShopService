package com.example.carshopwithspringboot.service.impl;


import com.example.carshopwithspringboot.dto.CarDto;
import com.example.carshopwithspringboot.model.Car;
import com.example.carshopwithspringboot.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    public CarService(CarRepository carRepository, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.objectMapper = objectMapper;
    }


    public Car add(CarDto carDto) {
        Car save = carRepository.save(objectMapper.convertValue(carDto, Car.class));
        return save;
    }


    public Car update(CarDto carDto) {
        Car save = carRepository.save(objectMapper.convertValue(carDto, Car.class));
        return save;
    }


    public Integer delete(Integer carId) {
        carRepository.deleteById(carId);
        return carId;
    }


    public Optional<Car> get(Integer carId) {
        Optional<Car> byId = carRepository.findById(carId);
        return Optional.ofNullable(byId.get());
    }


    public List<Car> findAll() {
        return carRepository.findAll();
    }

}
