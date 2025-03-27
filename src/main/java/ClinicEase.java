import command.Command;

import exception.DuplicatePatientIDException;
import exception.InvalidInputFormatException;

import exception.UnknownCommandException;
import exception.UnloadedStorageException;
import manager.ManagementSystem;
import miscellaneous.Parser;
import miscellaneous.Ui;
import storage.Storage;

public class ClinicEase {

    private ManagementSystem manager;
    private Ui ui;
    private Storage storage;

    public ClinicEase(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.ui = new Ui();
        this.storage= new Storage(filePath);

        try {
            this.manager = new ManagementSystem(Storage.loadPatients());
        } catch (UnloadedStorageException e) {
            ui.showError("Could not load data: " + e.getMessage());
            this.manager = new ManagementSystem(null);
        }

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
            } catch (InvalidInputFormatException | UnknownCommandException | DuplicatePatientIDException |
                     UnloadedStorageException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new ClinicEase("src/main/java/storage").run();
    }
}
