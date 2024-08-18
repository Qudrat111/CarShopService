import commands.*;
import response.CarResponse;
import response.OrderResponse;
import response.UserResponse;

import java.util.HashMap;
import java.util.Map;

public class CarShopServiceApplication {
    private static UserResponse userResponse = new UserResponse();
    private static CarResponse carResponse = new CarResponse();
    private static OrderResponse orderResponse = new OrderResponse();
    private static ConsoleInput consoleInput = new ConsoleInput();
    private static ConsoleOutput consoleOutput = new ConsoleOutput();
    private static Map<Integer, Command> loginCommands = new HashMap<>();
    private static Map<Integer, Command> mainMenuCommands = new HashMap<>();

    public static void main(String[] args) {
        loginCommands.put(1, new RegisterCommand(userResponse, consoleInput, consoleOutput));
        loginCommands.put(2, new LoginCommand(userResponse, consoleInput, consoleOutput));
        mainMenuCommands.put(3, new ManageCarsCommand(carResponse, consoleInput, consoleOutput));
        mainMenuCommands.put(4, new ManageOrdersCommand(orderResponse, userResponse, consoleInput, consoleOutput,carResponse));

        while (true) {
            if (LoginCommand.currentUser == null) {
                showLoginMenu();
                int choice = consoleInput.getIntInput("");
                Command command = loginCommands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    consoleOutput.displayMessage("Invalid choice");
                }
            } else {
                showMainMenu();
                int choice = consoleInput.getIntInput("");
                Command command = mainMenuCommands.get(choice);
                if (command != null) {
                    command.execute();
                } else {
                    consoleOutput.displayMessage("Invalid choice");
                }
            }
        }
    }

    private static void showLoginMenu() {
        consoleOutput.displayMessage("1. Register");
        consoleOutput.displayMessage("2. Login");
    }

    private static void showMainMenu() {
        consoleOutput.displayMessage("3. Manage Cars");
        consoleOutput.displayMessage("4. Manage Orders");
        consoleOutput.displayMessage("5. Logout");
    }
}
