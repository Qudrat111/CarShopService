package response;

import model.Car;
import service.impl.CarService;

import java.util.List;

public class CarResponse {
    private CarService carService = new CarService();

    public ApiResponse addCar(Car car) {
        carService.add(car);
        return new ApiResponse("success",200,car);
    }

    public ApiResponse editCar(Car car) {
        carService.update(car);
        return new ApiResponse("success",200,car);
    }
    public ApiResponse deleteCar(Car car) {
        carService.delete(car);
        return new ApiResponse("success",200,car);
    }
    public ApiResponse getAllCars() {
        List<Car> all = carService.findAll();
        return new ApiResponse("success",200,all);
    }

    public ApiResponse getCarByDetails(String make, String model, int year) {
        Car carByDetails = (Car) carService.getCarByDetails(make, model, year);
        if(carByDetails != null) {
            return new ApiResponse("success", 200, carByDetails);
        }
        return new ApiResponse("failed", 404, null);
    }
}
