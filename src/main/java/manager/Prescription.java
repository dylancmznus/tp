package manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@@author Basudeb2005
public class Prescription {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    private final String patientId;
    private final LocalDateTime timestamp;
    private final String prescriptionId;
    private final List<String> symptoms;
    private final List<String> medicines;
    private final String notes;
    
    public Prescription(String patientId, List<String> symptoms, List<String> medicines, String notes) {
        this.patientId = patientId;
        this.timestamp = LocalDateTime.now();
        this.prescriptionId = patientId + "-" + "1"; // Will be updated to handle numbering
        this.symptoms = new ArrayList<>(symptoms);
        this.medicines = new ArrayList<>(medicines);
        this.notes = notes;
    }
    
    public Prescription(String patientId, String prescriptionId, LocalDateTime timestamp, 
                       List<String> symptoms, List<String> medicines, String notes) {
        this.patientId = patientId;
        this.timestamp = timestamp;
        this.prescriptionId = prescriptionId;
        this.symptoms = new ArrayList<>(symptoms);
        this.medicines = new ArrayList<>(medicines);
        this.notes = notes;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getPrescriptionId() {
        return prescriptionId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public List<String> getSymptoms() {
        return symptoms;
    }
    
    public List<String> getMedicines() {
        return medicines;
    }
    
    public String getNotes() {
        return notes;
    }
    
    @Override
    public String toString() {
        StringBuilder symptomsStr = new StringBuilder();
        for (String symptom : symptoms) {
            symptomsStr.append("- ").append(symptom).append("\n");
        }
        
        StringBuilder medicinesStr = new StringBuilder();
        for (String medicine : medicines) {
            medicinesStr.append("- ").append(medicine).append("\n");
        }
        
        return String.format(
            "Prescription [%s] (%s)\n"
            + "Patient ID: %s\n"
            + "Symptoms: \n%s"
            + "Medicines: \n%s"
            + "Notes: %s",
            prescriptionId, timestamp.format(DATE_TIME_FORMATTER),
            patientId, symptomsStr.toString(), medicinesStr.toString(), notes);
    }
    
    public String toFileFormat() {
        return String.join("|", 
            prescriptionId,
            patientId,
            timestamp.format(DATE_TIME_FORMATTER),
            String.join(",", symptoms),
            String.join(",", medicines),
            notes);
    }
    
    public static Prescription fromFileFormat(String fileEntry) {
        String[] parts = fileEntry.split("\\|");
        String prescriptionId = parts[0];
        String patientId = parts[1];
        LocalDateTime timestamp = LocalDateTime.parse(parts[2], DATE_TIME_FORMATTER);
        List<String> symptoms = Arrays.asList(parts[3].split(","));
        List<String> medicines = Arrays.asList(parts[4].split(","));
        String notes = parts[5];
        
        return new Prescription(patientId, prescriptionId, timestamp, symptoms, medicines, notes);
    }
    
    public String generateHtml(Patient patient) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n")
            .append("<html lang=\"en\">\n")
            .append("<head>\n")
            .append("  <meta charset=\"UTF-8\">\n")
            .append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
            .append("  <title>Prescription ").append(prescriptionId).append("</title>\n")
            .append("  <style>\n")
            .append("    body { font-family: Arial, sans-serif; margin: 40px; }\n")
            .append("    .prescription { border: 1px solid #333; padding: 20px; max-width: 800px; margin: 0 auto; }\n")
            .append("    .header { text-align: center; border-bottom: 2px solid #333; padding-bottom: 10px;\n")
            .append("             margin-bottom: 20px; }\n")
            .append("    .section { margin-bottom: 15px; }\n")
            .append("    h1 { color: #333; }\n")
            .append("    h2 { color: #555; margin-bottom: 5px; }\n")
            .append("    .footer { margin-top: 50px; border-top: 1px solid #ccc; padding-top: 10px;\n")
            .append("             text-align: center; }\n")
            .append("    @media print { .no-print { display: none; } }\n")
            .append("    table { width: 100%; border-collapse: collapse; }\n")
            .append("    td { padding: 5px; }\n")
            .append("    .patient-info td:first-child { font-weight: bold; width: 150px; }\n")
            .append("  </style>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("  <div class=\"prescription\">\n")
            .append("    <div class=\"header\">\n")
            .append("      <h1>ClinicEase Medical Prescription</h1>\n")
            .append("      <p>Prescription ID: ").append(prescriptionId).append("</p>\n")
            .append("      <p>Date: ").append(timestamp.format(DATE_TIME_FORMATTER)).append("</p>\n")
            .append("    </div>\n");
        
        if (patient != null) {
            html.append("    <div class=\"section\">\n")
                .append("      <h2>Patient Information</h2>\n")
                .append("      <table class=\"patient-info\">\n")
                .append("        <tr><td>Patient ID:</td><td>").append(patient.getId()).append("</td></tr>\n")
                .append("        <tr><td>Name:</td><td>").append(patient.getName()).append("</td></tr>\n")
                .append("        <tr><td>Gender:</td><td>").append(patient.getGender()).append("</td></tr>\n")
                .append("        <tr><td>Date of Birth:</td><td>").append(patient.getDob()).append("</td></tr>\n")
                .append("        <tr><td>Contact:</td><td>").append(patient.getContactInfo()).append("</td></tr>\n")
                .append("      </table>\n")
                .append("    </div>\n");
        } else {
            html.append("    <div class=\"section\">\n")
                .append("      <h2>Patient Information</h2>\n")
                .append("      <table class=\"patient-info\">\n")
                .append("        <tr><td>Patient ID:</td><td>").append(patientId).append("</td></tr>\n")
                .append("      </table>\n")
                .append("    </div>\n");
        }
        
        html.append("    <div class=\"section\">\n")
            .append("      <h2>Symptoms</h2>\n")
            .append("      <ul>\n");
        
        for (String symptom : symptoms) {
            html.append("        <li>").append(symptom).append("</li>\n");
        }
        
        html.append("      </ul>\n")
            .append("    </div>\n")
            .append("    <div class=\"section\">\n")
            .append("      <h2>Prescribed Medications</h2>\n")
            .append("      <ul>\n");
        
        for (String medicine : medicines) {
            html.append("        <li>").append(medicine).append("</li>\n");
        }
        
        html.append("      </ul>\n")
            .append("    </div>\n");
        
        if (notes != null && !notes.isEmpty()) {
            html.append("    <div class=\"section\">\n")
                .append("      <h2>Special Instructions</h2>\n")
                .append("      <p>").append(notes).append("</p>\n")
                .append("    </div>\n");
        }
        
        html.append("    <div class=\"footer\">\n")
            .append("      <p>This prescription was generated by ClinicEase System</p>\n")
            .append("      <button class=\"no-print\" onclick=\"window.print();\">Print Prescription</button>\n")
            .append("    </div>\n")
            .append("  </div>\n")
            .append("</body>\n")
            .append("</html>\n");
        
        return html.toString();
    }
} 

