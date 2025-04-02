package command;

import exception.UnloadedStorageException;
import manager.ManagementSystem;
import manager.Patient;
import manager.Prescription;
import miscellaneous.Ui;

import java.util.List;

//@@author Basudeb2005
public class ViewAllPrescriptionsCommand extends Command {
    private final String patientId;

    public ViewAllPrescriptionsCommand(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException {
        Patient patient = manager.viewPatient(patientId);
        if (patient == null) {
            ui.showError("Patient with ID " + patientId + " not found.");
            return;
        }

        List<Prescription> prescriptions = manager.getPrescriptionsForPatient(patientId);
        
        ui.showLine();
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions found for patient " + patient.getName() + " (" + patientId + ").");
        } else {
            System.out.println("Prescriptions for patient " + patient.getName() + " (" + patientId + "):");
            System.out.println("");
            
            for (Prescription prescription : prescriptions) {
                System.out.println("Prescription ID: " + prescription.getPrescriptionId());
                System.out.println("Date: " + prescription.getTimestamp().format(
                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                
                System.out.println("Symptoms:");
                for (String symptom : prescription.getSymptoms()) {
                    System.out.println("- " + symptom);
                }
                
                System.out.println("Medicines:");
                for (String medicine : prescription.getMedicines()) {
                    System.out.println("- " + medicine);
                }
                
                System.out.println("Notes: " + prescription.getNotes());
                System.out.println("");
            }
            
            System.out.println("Total prescriptions: " + prescriptions.size());
            System.out.println("Use 'view-prescription PRESCRIPTION_ID' to view details and generate HTML.");
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
} 

