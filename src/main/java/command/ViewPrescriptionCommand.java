package command;

import exception.UnloadedStorageException;
import manager.ManagementSystem;
import manager.Patient;
import manager.Prescription;
import miscellaneous.Ui;
import storage.Storage;

import java.io.File;

//@@author Basudeb2005
public class ViewPrescriptionCommand extends Command {
    private final String prescriptionId;

    public ViewPrescriptionCommand(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException {
        Prescription prescription = manager.getPrescriptionById(prescriptionId);
        if (prescription == null) {
            ui.showError("Prescription with ID " + prescriptionId + " not found.");
            return;
        }

        Patient patient = manager.viewPatient(prescription.getPatientId());
        
        ui.showLine();
        System.out.println("Prescription details:");
        System.out.println(prescription.toString());
        System.out.println("");
        
        // Generate HTML file
        Storage.savePrescriptionHtml(prescription, patient);
        
        String fileName = "prescription_" + prescription.getPatientId() + "_" 
                + prescription.getPrescriptionId().split("-")[1] + ".html";
        String filePath = new File("").getAbsolutePath() 
                + File.separator + "data" 
                + File.separator + "prescriptions" 
                + File.separator + fileName;
        
        System.out.println("Prescription HTML file generated at: " + filePath);
        System.out.println("Open this file in a web browser to view and print the prescription.");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
} 

