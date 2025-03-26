import command.Command;
import exception.DuplicatePatientIDException;
import exception.InvalidInputFormatException;

import exception.UnknownCommandException;
import manager.ManagementSystem;
import miscellaneous.Parser;
import miscellaneous.Ui;

public class ClinicEase {

    private ManagementSystem manager;
    private Ui ui;

    public ClinicEase() {
        this.manager = new ManagementSystem();
        this.ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean running = true;

        while (running) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(manager, ui);
                running = !command.isExit();
            } catch (InvalidInputFormatException | UnknownCommandException | DuplicatePatientIDException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new ClinicEase().run();
    }
}
