import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryManager {
    private static final List<Patient> patients = new ArrayList<>();

    public MedicalHistoryManager() {}

    public void storeMedicalHistory(String name, String nric, String medHistory) {
        String cleanedNric = nric.trim();
        String cleanedName = name.trim();

        Patient existingPatient = findPatientByNric(cleanedNric);

        if (existingPatient == null) {
            existingPatient = new Patient(cleanedNric, cleanedName, "", "", "", "");
            patients.add(existingPatient);
            System.out.println("New patient " + cleanedName + " (NRIC: " + cleanedNric + ") created.");
        }

        String[] historyEntries = medHistory.split(",\s*");
        for (String entry : historyEntries) {
            if (!existingPatient.getMedicalHistory().contains(entry.trim())) {
                existingPatient.getMedicalHistory().add(entry.trim());
            }
        }
        System.out.println("Medical history added for " + cleanedName + " (NRIC: " + cleanedNric + ").");
    }

    public void viewMedicalHistoryByNric(String nric) {
        Patient patient = findPatientByNric(nric.trim());
        if (patient == null) {
            System.out.println("No patient found with NRIC " + nric + ".");
        } else {
            printPatientHistory(patient);
        }
    }

    public void viewMedicalHistoryByName(String name) {
        List<Patient> matchedPatients = findPatientsByName(name.trim());
        if (matchedPatients.isEmpty()) {
            System.out.println("No patients found with name '" + name + "'.");
        } else {
            System.out.println("Found " + matchedPatients.size() + " patient(s) with name '" + name + "':");
            for (Patient p : matchedPatients) {
                printPatientHistory(p);
            }
        }
    }


    public Patient findPatientByNric(String nric) {
        String target = nric.trim().toUpperCase(); // 统一转为大写
        for (Patient p : patients) {
            String patientId = p.getId().trim().toUpperCase();
            if (patientId.equals(target)) {
                return p;
            }
        }
        return null;
    }


    public List<Patient> findPatientsByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getName().trim().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    private void printPatientHistory(Patient patient) {
        System.out.println("Medical History for " + patient.getName() + " (NRIC: " + patient.getId() + "):");
        List<String> histories = patient.getMedicalHistory();
        if (histories.isEmpty()) {
            System.out.println("No medical history recorded.");
        } else {
            for (String h : histories) {
                System.out.println("- " + h);
            }
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
