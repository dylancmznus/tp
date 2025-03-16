import java.util.ArrayList;
import java.util.List;

class Patient {
    private String id;
    private String name;
    private String dob;
    private String contactInfo;
    private String gender;
    private String address;
    private List<String> medicalHistory;

    public Patient(String id, String name, String dob, String gender, String address, String contactInfo) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getContactInfo() { return contactInfo; }
    public List<String> getMedicalHistory() { return medicalHistory; }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Date of Birth: " + dob + ", Contact: " + contactInfo;
    }
}
