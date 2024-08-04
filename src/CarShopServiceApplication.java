import model.Car;
import model.Order;
import model.User;
import response.ApiResponse;
import response.CarResponse;
import response.OrderResponse;
import response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CarShopServiceApplication {
    private static UserResponse userResponse = new UserResponse();
    private static OrderResponse orderResponse = new OrderResponse();
    private static CarResponse carResponse = new CarResponse();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }

    }

    private static void showLoginMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    private static void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter role (admin, manager, client): ");
        String role = scanner.nextLine();

        User user = new User(username, password, role);
        ApiResponse register = userResponse.register(user);
        System.out.println(register);
    }
    private static void login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        ApiResponse login = userResponse.login(username, password);
        if(login.getData() != null){
            currentUser = (User) login.getData();
        }
        System.out.println(login);
    }
    private static void showMainMenu() {
        System.out.println("1. Manage Cars");
        System.out.println("2. Manage Orders");
        System.out.println("3. View Clients and Employees");
        System.out.println("4. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                manageCars();
                break;
            case 2:
                manageOrders();
                break;
            case 3:
                viewClientsAndEmployees();
                break;
            case 4:
                currentUser =        null;
                break;
            default:
                System.out.println("Invalid choice");
        }

    }
    private static void manageCars() {
        System.out.println("1. View Cars");
        System.out.println("2. Add Car");
        System.out.println("3. Edit Car");
        System.out.println("4. Remove Car");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
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
                System.out.println("Invalid choice");
        }
    }

    private static void viewCars() {
        ApiResponse allCars = carResponse.getAllCars();
        List<Car> cars = (List<Car>) allCars.getData();
        for(Car car : cars){
            System.out.println(car);
        }
    }
    private static void addCar() {
        System.out.println("Enter make: ");
        String make = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter condition: ");
        String condition = scanner.nextLine();

        Car car = new Car(make, model, year, condition,price);
        ApiResponse apiResponse = carResponse.addCar(car);
        System.out.println(apiResponse);
    }

    private static void editCar() {
        System.out.println("Enter make: ");
        String make = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
        Car car = (Car)carByDetails.getData();
        if (car != null) {
            System.out.println("Enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter new condition: ");
            String condition = scanner.nextLine();
            car.setPrice(price);
            car.setCondition(condition);
            System.out.println(carByDetails.getMessage());
        } else {
            System.out.println(carByDetails.getMessage());
        }
    }
    private static void removeCar() {
        System.out.println("Enter make: ");
        String make = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
        Car data = (Car) carByDetails.getData();
        if (data != null) {
            carResponse.deleteCar(data);
            System.out.println(carByDetails.getMessage());
        } else {
            System.out.println(carByDetails.getMessage());
        }
    }
    private static void manageOrders() {
        System.out.println("1. Create Order");
        System.out.println("2. View Orders");
        System.out.println("3. Update Order Status");
        System.out.println("4. Cancel Order");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
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
                System.out.println("Invalid choice");
        }
    }
    private static void createOrder() {
        System.out.println("Enter client username: ");
        String clientUsername = scanner.nextLine();
        User client = userResponse.existUser(clientUsername) ? new User(clientUsername, "", "client") : null;

        if (client != null) {
            System.out.println("Enter car make: ");
            String make = scanner.nextLine();
            System.out.println("Enter car model: ");
            String model = scanner.nextLine();
            System.out.println("Enter car year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            ApiResponse carByDetails = carResponse.getCarByDetails(make, model, year);
            Car car = (Car) carByDetails.getData();
            if (car != null) {
                ApiResponse pending = orderResponse.createOrder(car, client, "pending", LocalDateTime.now());
                System.out.println(pending);
            } else {
                System.out.println("Car not found");
            }
        } else {
            System.out.println("Client not found");
        }
    }

    private static void viewOrders() {
        ApiResponse allOrders = orderResponse.getAllOrders();
        List<Order> orders = (List<Order>) allOrders.getData();
        for(Order order : orders){
            System.out.println(order);
        }
    }

    private static void updateOrderStatus() {
        System.out.println("Enter order ID: ");
        String orderId = scanner.nextLine();
        System.out.println("Enter new status: ");
        String status = scanner.nextLine();

        ApiResponse apiResponse = orderResponse.updateOrderStatus(orderId, status);
        System.out.println(apiResponse);
    }
    private static void cancelOrder() {
        System.out.println("Enter order ID: ");
        String orderId = scanner.nextLine();

        ApiResponse apiResponse = orderResponse.cancelOrder(orderId);
        System.out.println(apiResponse);

    }
    private static void viewClientsAndEmployees() {
        ApiResponse allUsers = userResponse.getAllUsers();
        List<User> users = (List<User>) allUsers.getData();
        for (User user : users){
            System.out.println(user);
        }
    }





}
