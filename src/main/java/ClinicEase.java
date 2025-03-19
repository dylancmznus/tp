import exception.DuplicatePatientIDException;
import exception.InvalidInputFormatException;

import manager.ManagementSystem;
import manager.MedicalHistoryManager;
import miscellaneous.Parser;
import miscellaneous.Ui;

public class ClinicEase {
    public static void main(String[] args) {
        ManagementSystem manager = new ManagementSystem();
        MedicalHistoryManager mhManager = new MedicalHistoryManager();
        Ui ui = new Ui();
        ui.showWelcome();

        boolean running = true;
        while (running) {
            try {
                String input = ui.readCommand();

                if (Parser.isBye(input)) {
                    ui.showBye();
                    running = false;
                } else if (Parser.isAddPatient(input)) {
                    manager.addPatient(input);
                } else if (Parser.isDeletePatient(input)) {
                    manager.deletePatient(input);
                } else if (Parser.isViewPatient(input)) {
                    manager.viewPatient(input);
                } else if (Parser.isListPatient(input)) {
                    manager.listPatients();
                } else if (Parser.isViewHistory(input)) {
                    String[] typeAndValue = Parser.parseViewHistory(input);
                    String type = typeAndValue[0];
                    String value = typeAndValue[1];

                    if ("ic".equals(type)) {
                        mhManager.viewMedicalHistoryByNric(value);
                    } else {
                        mhManager.viewMedicalHistoryByName(value);
                    }
                } else if (Parser.isStoreHistory(input)) {
                    String[] details = Parser.parseStoreHistory(input);
                    mhManager.storeMedicalHistory(details[0], details[1], details[2]);
                } else if (Parser.isAddAppointment(input)) {
                    String[] details = Parser.parseAddAppointment(input);
                    manager.addAppointment(details);
                } else if (Parser.isDeleteAppointment(input)) {
                    String apptId = Parser.parseDeleteAppointment(input);
                    manager.deleteAppointment(apptId);
                } else if (Parser.isListAppointments(input)) {
                    manager.listAppointments();
                } else {
                    Ui.showLine();
                    System.out.println("Unknown command. Please try again.");
                    Ui.showLine();
                }
            } catch (InvalidInputFormatException | DuplicatePatientIDException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
