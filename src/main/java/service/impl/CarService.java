package service.impl;

import model.Car;
import org.springframework.stereotype.Service;
import repository.CarRepository;
import service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements BaseService<Car> {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(Car car) {
        carRepository.save(car);
    }


    @Override
    public void update(Car car) {
        Car byId = carRepository.findById(car.getId());
        if (byId != null) {
            carRepository.update(car);
        }
    }

    @Override
    public void delete(Car car) {
        Car byId = carRepository.findById(car.getId());
        if (byId != null) {
            carRepository.remove(car);
        }
    }

    @Override
    public Car get(Integer id) {
        Car byId = carRepository.findById(id);
        return byId;
    }

    @Override
    public List<Car> findAll() {
        Optional<List<Car>> all = carRepository.findAll();
        return all.orElseGet(ArrayList::new);
    }

    public List<Car> getCarByDetails(String make, String model, Integer year) {
        Optional<List<Car>> carByDetails = carRepository.getCarByDetails(make, model, year);
        return carByDetails.get();
    }

    public List<Car> searchCars(String make, String model, int year, double price, String condition) {
        Optional<List<Car>> cars = carRepository.searchCars(make, model, year, price, condition);
        return cars.get();
    }

}
