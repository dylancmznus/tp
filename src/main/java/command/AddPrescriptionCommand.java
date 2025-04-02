package command;

import exception.UnloadedStorageException;
import manager.ManagementSystem;
import manager.Prescription;
import miscellaneous.Ui;

//@@author Basudeb2005
public class AddPrescriptionCommand extends Command {
    private final Prescription prescription;

    public AddPrescriptionCommand(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException {
        try {
            Prescription addedPrescription = manager.addPrescription(prescription);
            ui.showLine();
            System.out.println("Successfully added prescription:");
            System.out.println(addedPrescription.toString());
            System.out.println("");
            System.out.println("Prescription has been generated.");
            System.out.println("View the prescription for the patient with ID: " + addedPrescription.getPatientId());
            System.out.println("and prescription ID: " + addedPrescription.getPrescriptionId());
            ui.showLine();
        } catch (IllegalArgumentException e) {
            ui.showError("Failed to add prescription: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
} 

