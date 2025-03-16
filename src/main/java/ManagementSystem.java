import java.util.HashMap;
import java.util.Map;

class ManagementSystem {
    private Map<String, Patient> patients;

    public ManagementSystem() {
        patients = new HashMap<>();
    }

    public void addPatient(String line) {
        // Parse the line first, which will return an array of Strings
        // Output should be {id, name, dob, gender, address, contactInfo}
        // String [] details = new String[6];

        // if (patients.containsKey(details[0]) {
        //    System.out.println("Patient ID already exists!");
        //    return;
        // }

        // Patient newPatient = new Patient(id, name, dob, gender, address, contactInfo);
        // patients.put(details[0], newPatient);
        // System.out.println("Patient added: " + name);

        // If parser has been correctly implemented, delete comments and remove slashes from processes
    }

    public void deletePatient(String id) {
        if (patients.remove(id) != null) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
}
