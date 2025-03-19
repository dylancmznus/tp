import exception.InvalidInputFormatException;

public class ClinicEase {
    public static void main(String[] args) {
        ManagementSystem manager = new ManagementSystem();
        Ui ui = new Ui();
        ui.showWelcome();

        boolean running = true;
        while (running) {
            try {
                String input = ui.readCommand();

                if (Parser.isBye(input)) {
                    ui.showBye();
                    running = false;
                } else if (Parser.isAddAppointment(input)) {
                    String[] details = Parser.parseAddAppointment(input);
                    manager.addAppointment(details);
                } else if (Parser.isDeleteAppointment(input)) {
                    String apptId = Parser.parseDeleteAppointment(input);
                    manager.deleteAppointment(apptId);
                } else if (Parser.isListAppointments(input)) {
                    manager.listAppointments();
                } else if (Parser.isAddPatient(input)) {
                    System.out.println("Feature not implemented yet.");
                } else if (Parser.isDeletePatient(input)) {
                    System.out.println("Feature not implemented yet.");
                } else if (Parser.isViewPatient(input)) {
                    System.out.println("Feature not implemented yet.");
                } else if (Parser.isViewHistory(input)) {
                    System.out.println("Feature not implemented yet.");
                } else {
                    Ui.showLine();
                    System.out.println("There is no such command. Please try again.");
                    Ui.showLine();
                }
            } catch (InvalidInputFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
