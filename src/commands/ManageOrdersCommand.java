package commands;

import model.Car;
import model.Order;
import model.User;
import response.ApiResponse;
import response.CarResponse;
import response.OrderResponse;
import response.UserResponse;

import java.util.Date;
import java.util.List;

public class ManageOrdersCommand implements Command {
    private final OrderResponse orderResponse;
    private final UserResponse userResponse;
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final CarResponse carResponse;

    public ManageOrdersCommand(OrderResponse orderResponse, UserResponse userResponse, ConsoleInput consoleInput, ConsoleOutput consoleOutput, CarResponse carResponse) {
        this.orderResponse = orderResponse;
        this.userResponse = userResponse;
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
        this.carResponse = carResponse;
    }

    @Override
    public void execute() {
        consoleOutput.displayMessage("1. Create Order");
        consoleOutput.displayMessage("2. View Orders");
        consoleOutput.displayMessage("3. Update Order Status");
        consoleOutput.displayMessage("4. Cancel Order");

        int choice = consoleInput.getIntInput("");

        switch (choice) {
            case 1:
                createOrder();
                break;
            case 2:
                viewOrders();
                break;
            case 3:
                updateOrderStatus();
                break;
            case 4:
                cancelOrder();
                break;
            default:
                consoleOutput.displayMessage("Invalid choice");
        }
    }

    private void createOrder() {
        String clientUsername = consoleInput.getStringInput("Enter client username: ");
        User client = userResponse.existUser(clientUsername) ? new User(clientUsername, "", "client") : null;

        if (client != null) {
            String make = consoleInput.getStringInput("Enter car make: ");
            String model = consoleInput.getStringInput("Enter car model: ");
            int year = consoleInput.getIntInput("Enter car year: ");

            ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
            Car car = (Car) carByDetails.getData();
            if (car != null) {
                ApiResponse pending = orderResponse.createOrder(car, client, "pending", new Date());
                consoleOutput.displayObject(pending);
            } else {
                consoleOutput.displayMessage("Car not found");
            }
        } else {
            consoleOutput.displayMessage("Client not found");
        }
    }

    private void viewOrders() {
        ApiResponse allOrders = orderResponse.getAllOrders();
        List<Order> orders = (List<Order>) allOrders.getData();
        for (Order order : orders) {
            consoleOutput.displayObject(order);
        }
    }

    private void updateOrderStatus() {
        String orderId = consoleInput.getStringInput("Enter order ID: ");
        String status = consoleInput.getStringInput("Enter new status: ");

        ApiResponse apiResponse = orderResponse.updateOrderStatus(orderId, status);
        consoleOutput.displayObject(apiResponse);
    }

    private void cancelOrder() {
        int orderId = consoleInput.getIntInput("Enter order ID: ");
        ApiResponse apiResponse = orderResponse.cancelOrder(orderId);
        consoleOutput.displayObject(apiResponse);
    }
}
