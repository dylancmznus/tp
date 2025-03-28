package manager;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String id;
    private String name;
    private String dob;
    private String contactInfo;
    private String gender;
    private String address;
    private final List<String> medicalHistory;

    public Patient(String id, String name, String dob, String gender, String address, String contactInfo) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "Patient ID: %s\n" +
                "Name: %s\n" +
                "Date of Birth: %s\n" +
                "Gender: %s\n" +
                "Address: %s\n" +
                "Contact: %s\n" +
                "Medical History: %s",
                id, name, dob, gender, address, contactInfo, medicalHistory
        );
    }

    public String toStringForListView() {
        String result = String.format(
                "Patient ID: %s\n   " +
                        "Name: %s\n   " +
                        "Date of Birth: %s\n   " +
                        "Gender: %s\n   " +
                        "Address: %s\n   " +
                        "Contact: %s",
                id, name, dob, gender, address, contactInfo
        );

        if (medicalHistory.isEmpty()) {
            result += "\n   Medical History: None";
        } else {
            result += "\n   Medical History:";
            for (String h : medicalHistory) {
                result += "\n   - " + h;
            }
        }
        return result;
    }

}
