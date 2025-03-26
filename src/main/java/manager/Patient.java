package manager;

import java.util.ArrayList;
import java.util.List;

public class Patient {
  
    private final String id;
    private final String name;
    private final String dob;
    private final String contactInfo;
    private final String gender;
    private final String address;
    private final List<String> medicalHistory;

    public Patient(String id, String name, String dob, String gender, String address, String contactInfo, List<String> medicalHistory) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>(medicalHistory);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    @Override
    public String toString() {
        return String.format(
                "Patient ID: %s\n" +
                "Name: %s\n" +
                "Date of Birth: %s\n" +
                "Gender: %s\n" +
                "Address: %s\n" +
                "Contact: %s",
                id, name, dob, gender, address, contactInfo
        );
    }

    public String toStringForListView() {
        String history = medicalHistory.isEmpty()
                ? "None"
                : String.join(", ", medicalHistory);

        return String.format(
                "Patient ID: %s\n   " +
                        "Name: %s\n   " +
                        "Date of Birth: %s\n   " +
                        "Gender: %s\n   " +
                        "Address: %s\n   " +
                        "Contact: %s\n   " +
                        "Medical History: %s",
                id, name, dob, gender, address, contactInfo, history
        );
    }

    public String toFileFormat() {
        return this.id + "|" + this.name + "|" + this.dob + "|" + this.gender + "|" + this.address + "|" + this.contactInfo + "|" + this.medicalHistory;
    }
}
