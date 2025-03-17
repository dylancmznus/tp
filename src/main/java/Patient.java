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

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Date of Birth: " + dob + ", Contact: " + contactInfo;
    }
}
