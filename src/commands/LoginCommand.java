package commands;

import model.User;
import response.ApiResponse;
import response.UserResponse;

public class LoginCommand implements Command {
    private final UserResponse userResponse;
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    public static User currentUser;

    public LoginCommand(UserResponse userResponse, ConsoleInput consoleInput, ConsoleOutput consoleOutput) {
        this.userResponse = userResponse;
        this.consoleInput = consoleInput;
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void execute() {
        String username = consoleInput.getStringInput("Enter username: ");
        String password = consoleInput.getStringInput("Enter password: ");

        ApiResponse login = userResponse.login(username, password);
        if (login.getData() != null) {
            currentUser = (User) login.getData();
        }
        consoleOutput.displayObject(login);
    }
}
