package commands;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public int getIntInput(String prompt) {
        System.out.println(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }

    public String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public double getDoubleInput(String prompt) {
        System.out.println(prompt);
        double input = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return input;
    }
}
