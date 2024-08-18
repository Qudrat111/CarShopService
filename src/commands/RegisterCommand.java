package commands;

import model.User;
import response.ApiResponse;
import response.UserResponse;

public class RegisterCommand implements Command {
    private final UserResponse userResponse;
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;

    public RegisterCommand(UserResponse userResponse, ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.userResponse = userResponse;
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute() {
        String username = consoleInput.getStringInput("Enter username: ");
        String password = consoleInput.getStringInput("Enter password: ");
        String role = consoleInput.getStringInput("Enter role (admin, manager, client): ");

        User user = new User(username, password, role);
        ApiResponse register = userResponse.register(user);
        consoleOutput.displayObject(register);
    }
}
