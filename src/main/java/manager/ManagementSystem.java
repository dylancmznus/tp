package manager;

import exception.DuplicatePatientIDException;
import exception.InvalidInputFormatException;
import miscellaneous.Parser;
import miscellaneous.Ui;

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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addPatient(String line) throws DuplicatePatientIDException, InvalidInputFormatException {
        String[] details = Parser.parseAddPatient(line);

        if (patients.containsKey(details[0])) {
            throw new DuplicatePatientIDException("Patient ID already exists!");
        }

        Patient newPatient = new Patient(details[0], details[1], details[2], details[3], details[4], details[5]);
        patients.put(details[0], newPatient);
        Ui.showLine();
        System.out.println("Patient added: " + details[1]);
        Ui.showLine();
    }

    public void deletePatient(String line) {
        String nric = line.substring(15);
        if (nric.isBlank()) {
            Ui.showLine();
            System.out.println("Invalid NRIC provided.");
            Ui.showLine();
            return;
        }

        if (patients.containsKey(nric)) {
            Patient removedPatient = patients.remove(nric);
            Ui.showLine();
            System.out.println("Patient removed successfully: " + removedPatient.getName()
                    + " (NRIC: " + nric + ")");
            Ui.showLine();
        } else {
            Ui.showLine();
            System.out.println("Error: Patient with NRIC " + nric + " not found.");
            Ui.showLine();
        }
    }

    public void viewPatient(String line) {
        String nric = line.substring(13);
        if (nric.isBlank()) {
            System.out.println("Invalid NRIC provided.");
            return;
        }

        System.out.println("-".repeat(43)+ "Patient Details" + "-".repeat(43));
        System.out.println(patients.get(nric).toString());
        Ui.showLine();
    }

    public void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients have been added.");
            return;
        }
        System.out.println("-".repeat(43)+ "Patient Details" + "-".repeat(43));
        int count = 1;
        for (Patient p : patients.values()) {
            System.out.println(count + ". " + p.toStringForListView());
            Ui.showLine();
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

        Ui.showLine();
        System.out.println("Appointment added for NRIC: " + nric + " on " + date + " at " + time + ".");
        System.out.println("Now you have " + appointments.size() + " appointment(s) in the list.");
        Ui.showLine();
    }

    public void deleteAppointment(String apptId) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId().equalsIgnoreCase(apptId)) {
                appointments.remove(i);

                Ui.showLine();
                System.out.println("Appointment " + apptId + " is deleted successfully.");
                System.out.println("Now you have " + appointments.size() + " appointment(s) in the list.");
                Ui.showLine();
                return;
            }
        }
        Ui.showLine();
        System.out.println("No appointment found with ID: " + apptId + ".");
        Ui.showLine();
    }

    public void listAppointments() {
        if (appointments.isEmpty()) {
            Ui.showLine();
            System.out.println("No appointments found.");
            Ui.showLine();
            return;
        }

        System.out.println("-".repeat(43)+ "Appointments" + "-".repeat(43));
        int count = 1;
        for (Appointment a : appointments) {
            System.out.println(count + ". " + a);
            count++;
        }
        Ui.showLine();
    }

}
