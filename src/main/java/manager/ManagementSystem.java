package manager;

import miscellaneous.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementSystem {
    private final List<Appointment> appointments;
    private final Map<String, Patient> patients;

    public ManagementSystem() {
        appointments = new ArrayList<>();
        patients = new HashMap<>();
    }

    public Map<String, Patient> getPatient() {
        return patients;
    }

    public void addPatient(String line) {
        String[] details = Parser.parseAddPatient(line);

        if (details == null) {
            System.out.println("Patient details are incomplete!");
            return;
        } else if (patients.containsKey(details[0])) {
            System.out.println("Error: Patient ID already exists!");
            return;
        }

        Patient newPatient = new Patient(details[0], details[1], details[2],
                details[3], details[4], details[5]);
        patients.put(details[0], newPatient);
        System.out.println("Patient added: " + details[1]);
    }

    public void deletePatient(String line) {
        String nric = line.substring(15);
        if (nric.isBlank()) {
            System.out.println("Invalid NRIC provided.");
            return;
        }

        if (patients.containsKey(nric)) {
            Patient removedPatient = patients.remove(nric);
            System.out.println("Patient removed successfully: " + removedPatient.getName()
                    + " (NRIC: " + nric + ")");
        } else {
            System.out.println("Error: Patient with NRIC " + nric + " not found.");
        }
    }

    public void viewPatient(String line) {
        String nric = line.substring(13);
        if (nric.isBlank()) {
            System.out.println("Invalid NRIC provided.");
            return;
        }

        System.out.println("===== Patient Details =====");
        System.out.println(patients.values());
        System.out.println("-----------------------");
    }

    public void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients have been added.");
            return;
        }
        System.out.println("===== Patient List =====");
        int count = 1;
        for (Patient p : patients.values()) {
            System.out.println(count + ". " + p);
            System.out.println("-----------------------");
            count++;
        }
    }

    public void addAppointment(String[] details) {
        String nric = details[0];
        String date = details[1];
        String time = details[2];
        String desc = details[3];

        Appointment appt = new Appointment(nric, date, time, desc);
        appointments.add(appt);
        System.out.println("Appointment added for NRIC " + nric
                + " on " + date + " at " + time + ".");
    }

    public void deleteAppointment(String apptId) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equalsIgnoreCase(apptId)) {
                appointments.remove(i);
                System.out.println("Appointment " + apptId + " is deleted successfully.");
                return;
            }
        }
        System.out.println("No appointment found with ID " + apptId + ".");
    }

    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("Appointments:");
        int count = 1;
        for (Appointment a : appointments) {
            System.out.println(count + ". " + a);
            count++;
        }
    }
}
