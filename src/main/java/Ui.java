import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static final String DIVIDER = "-".repeat(100);

    public static void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("Welcome to ClinicEase v1!");
        System.out.println("Type a command, or 'bye' to exit.");
        System.out.println(DIVIDER);
    }

    public void showBye() {
        System.out.println(DIVIDER);
        System.out.println("Goodbye!");
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        System.out.print("> ");
        return sc.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

}
