package service.impl;

import model.Car;
import service.BaseService;

import java.util.ArrayList;
import java.util.List;

public class CarService implements BaseService<Car> {
    private List<Car> cars = new ArrayList<>();
    @Override
    public void add(Car car) {
     cars.add(car);
    }

    @Override
    public void update(Car car) {
        Car byId = findById(car.getId());
        if (byId != null) {
            cars.remove(byId);
            cars.add(car);
        }
    }

    @Override
    public void delete(Car car) {
        Car byId = findById(car.getId());
        if (byId != null) {
            cars.remove(byId);
        }
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public Car findById(Integer id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public Car getCarByDetails(String make, String model, Integer year) {
        for (Car car : cars) {
            if (car.getMake().equals(make) && car.getModel().equals(model) && car.getYear() == year) {
                return car;
            }
        }
        return null;
    }
    public List<Car> searchCars(String make, String model, int year, double price, String condition) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            boolean matches = true;
            if (make != null && !car.getMake().equalsIgnoreCase(make)) matches = false;
            if (model != null && !car.getModel().equalsIgnoreCase(model)) matches = false;
            if (year != -1 && car.getYear() != year) matches = false;
            if (price != -1 && car.getPrice() != price) matches = false;
            if (condition != null && !car.getCondition().equalsIgnoreCase(condition)) matches = false;
            if (matches) result.add(car);
        }
        return result;
    }

}
