package miscellaneous;

import manager.Appointment;
import manager.Patient;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "-".repeat(100);
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Welcome to ClinicEase!");
        System.out.println("Type a command, or 'bye' to exit.");
        showLine();
    }

    public static void showBye() {
        showLine();
        System.out.println("Goodbye!");
        showLine();
    }

    public String readCommand() {
        System.out.print("> ");
        return sc.nextLine().trim();
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    //@@author judHoka
    public void showPatientAdded(List<Patient> patients) {
        showLine();
        System.out.println("Patient added successfully: " + patients.get(patients.size() - 1).getName());
        showLine();
    }

    public void showPatientDeleted(Patient removedPatient, String nric) {
        if (removedPatient == null) {
            showLine();
            System.out.println("Patient with NRIC " + nric + " not found.");
            showLine();
            return;
        }
        showLine();
        System.out.println("Patient deleted successfully: " + removedPatient.getName());
        showLine();
    }

    //@@author dylancmznus
    public void showPatientViewed(Patient matchedPatient, String nric) {
        if (matchedPatient == null) {
            showLine();
            System.out.println("Patient with NRIC " + nric + " not found.");
            showLine();
            return;
        }
        System.out.println("-".repeat(42) + "Patient Details" + "-".repeat(42));
        System.out.println(matchedPatient);
        showLine();
    }

    //@@author judHoka
    public void showPatientList(List<Patient> patients) {
        if (patients.isEmpty()) {
            showLine();
            System.out.println("No patients have been added.");
            showLine();
            return;
        }

        System.out.println("-".repeat(42)+ "Patient Details" + "-".repeat(42));

        int count = 1;
        for (Patient p : patients) {
            System.out.println(count + ". " + p.toStringForListView());
            showLine();
            count++;
        }
    }

    //@@author jyukuan
    public static void showPatientHistory(Patient patient) {
        System.out.println("Medical History for " + patient.getName() + " (NRIC: " + patient.getId() + "):");
        List<String> histories = patient.getMedicalHistory();
        if (histories.isEmpty()) {
            System.out.println("No medical history recorded.");
        } else {
            for (String h : histories) {
                System.out.println("- " + h);
            }
            showLine();
        }
    }

    //@@author chwenyee
    public void showAppointmentAdded(List<Appointment> appointments) {
        Appointment currentAppointment = appointments.get(appointments.size() - 1);

        showLine();
        System.out.println("Appointment added for NRIC: " + currentAppointment.getNric() + " on "
                + currentAppointment.getDate() + " at " + currentAppointment.getTime().format(OUTPUT_TIME_FORMAT)
                + ".");
        System.out.println("Now you have " + appointments.size() + " appointment(s) in the list.");
        showLine();
    }

    public void showAppointmentDeleted(List<Appointment> appointments, Appointment removedAppointment, String apptId) {
        if (removedAppointment == null) {
            showLine();
            System.out.println("No appointment found with ID: " + apptId + ".");
            showLine();
            return;
        }

        showLine();
        System.out.println("Appointment " + apptId + " is deleted successfully.");
        System.out.println("Now you have " + appointments.size() + " appointment(s) in the list.");
        showLine();
    }

    //@@author dylancmznus
    public void showAppointmentMarked(List<Appointment> appointments, Appointment markedAppointment, String apptId) {
        if (markedAppointment == null) {
            showLine();
            System.out.println("No appointment found with ID: " + apptId + ".");
            showLine();
            return;
        }

        showLine();
        System.out.println("Appointment " + apptId + " is marked successfully.");
        showLine();
    }

    public void showAppointmentUnmarked(List<Appointment> appointments, Appointment markedAppointment, String apptId) {
        if (markedAppointment == null) {
            showLine();
            System.out.println("No appointment found with ID: " + apptId + ".");
            showLine();
            return;
        }

        showLine();
        System.out.println("Appointment " + apptId + " is unmarked successfully.");
        showLine();
    }

    //@@author Basudeb2005
    public void showAppointmentList(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            showLine();
            System.out.println("No appointments found.");
            showLine();
            return;
        }

        System.out.println("-".repeat(43)+ "Appointments" + "-".repeat(45));
        int count = 1;
        for (Appointment a : appointments) {
            System.out.println(count + ". " + a);
            count++;
        }
        showLine();
    }

    //@@author dylancmznus
    public void showAppointmentFound(Appointment appointment, String nric) {
        if (appointment != null) {
            showLine();
            System.out.println("Appointment found for NRIC " + nric + ": " + appointment);
            showLine();
        } else {
            showLine();
            System.out.println("No appointment found for NRIC " + nric);
            showLine();
        }
    }
}
