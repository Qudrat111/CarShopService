package commands;

import model.Car;
import response.ApiResponse;
import response.CarResponse;

import java.util.List;

public class ManageCarsCommand implements Command {
    private final CarResponse carResponse;
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;

    public ManageCarsCommand(CarResponse carResponse, ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.carResponse = carResponse;
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute() {
        consoleOutput.displayMessage("1. View Cars");
        consoleOutput.displayMessage("2. Add Car");
        consoleOutput.displayMessage("3. Edit Car");
        consoleOutput.displayMessage("4. Remove Car");

        int choice = consoleInput.getIntInput("");

        switch (choice) {
            case 1:
                viewCars();
                break;
            case 2:
                addCar();
                break;
            case 3:
                editCar();
                break;
            case 4:
                removeCar();
                break;
            default:
                consoleOutput.displayMessage("Invalid choice");
        }
    }

    private void viewCars() {
        ApiResponse allCars = carResponse.getAllCars();
        List<Car> cars = (List<Car>) allCars.getData();
        for (Car car : cars) {
            consoleOutput.displayObject(car);
        }
    }

    private void addCar() {
        String make = consoleInput.getStringInput("Enter make: ");
        String model = consoleInput.getStringInput("Enter model: ");
        int year = consoleInput.getIntInput("Enter year: ");
        double price = consoleInput.getDoubleInput("Enter price: ");
        String condition = consoleInput.getStringInput("Enter condition: ");

        Car car = new Car(make, model, year, condition, price);
        ApiResponse apiResponse = carResponse.addCar(car);
        consoleOutput.displayObject(apiResponse);
    }

    private void editCar() {
        String make = consoleInput.getStringInput("Enter make: ");
        String model = consoleInput.getStringInput("Enter model: ");
        int year = consoleInput.getIntInput("Enter year: ");

        ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
        Car car = (Car) carByDetails.getData();
        if (car != null) {
            double price = consoleInput.getDoubleInput("Enter new price: ");
            String condition = consoleInput.getStringInput("Enter new condition: ");
            car.setPrice(price);
            car.setCondition(condition);
            consoleOutput.displayMessage(carByDetails.getMessage());
        } else {
            consoleOutput.displayMessage(carByDetails.getMessage());
        }
    }

    private void removeCar() {
        String make = consoleInput.getStringInput("Enter make: ");
        String model = consoleInput.getStringInput("Enter model: ");
        int year = consoleInput.getIntInput("Enter year: ");

        ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
        Car data = (Car) carByDetails.getData();
        if (data != null) {
            carResponse.deleteCar(data);
            consoleOutput.displayMessage(carByDetails.getMessage());
        } else {
            consoleOutput.displayMessage(carByDetails.getMessage());
        }
    }
}
