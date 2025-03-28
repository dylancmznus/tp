package manager;

import exception.DuplicatePatientIDException;
import exception.UnloadedStorageException;
import miscellaneous.Ui;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private final List<Appointment> appointments;
    private final List<Patient> patients;

    public ManagementSystem(List<Patient> list) {
        appointments = new ArrayList<>();
        patients = list;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addPatient(Patient patient) throws DuplicatePatientIDException, UnloadedStorageException {
        for (Patient existingPatient : patients) {
            if (existingPatient.getId().equals(patient.getId())) {
                throw new DuplicatePatientIDException("Patient ID already exists!");
            }
        }
        patients.add(patient);
        Storage.savePatients(patients);
    }

    public Patient deletePatient(String nric) {
        for (Patient patient : patients) {
            if (patient.getId().equals(nric)) {
                patients.remove(patient);
                // Return the removed patient
                return patient;
            }
        }
        return null;
    }

    //@@author dylancmznus
    public Patient viewPatient(String nric) {
        Patient matchedPatient = null;
        for (Patient patient : patients) {
            if (patient.getId().equals(nric)) {
                matchedPatient = patient;
                break;
            }
        }
        return matchedPatient;
    }

    //@@author jyukuan
    public void storeMedicalHistory(String name, String nric, String medHistory) {
        Patient existingPatient = findPatientByNric(nric);

        if (existingPatient == null) {
            existingPatient = new Patient(nric, name, "", "", "", "", null);
            patients.add(existingPatient);
            Ui.showLine();
            System.out.println("New patient " + name + " (NRIC: " + nric + ") created.");
        } else {
            Ui.showLine();
        }

        String[] historyEntries = medHistory.split(",\s*");
        for (String entry : historyEntries) {
            if (!existingPatient.getMedicalHistory().contains(entry.trim())) {
                existingPatient.getMedicalHistory().add(entry.trim());
            }
        }
        System.out.println("Medical history added for " + name + " (NRIC: " + nric + ").");

        Ui.showLine();
    }

    public void viewMedicalHistoryByNric(String nric) {
        Patient foundPatients = findPatientByNric(nric.trim());

        if (foundPatients == null) {
            Ui.showLine();
            System.out.println("No patient/patients found with NRIC " + nric + ".");
        } else {
            Ui.showLine();
            Ui.showPatientHistory(foundPatients);
        }
    }

    public void viewMedicalHistoryByName(String name) {
        List<Patient> foundPatients = findPatientsByName(name.trim());

        Ui.showLine();

        if (foundPatients.isEmpty()) {
            System.out.println("No patients found with name '" + name + "'.");
            Ui.showLine();
        } else {
            System.out.println("Found " + foundPatients.size() + " patient(s) with name '" + name + "'");
            for (Patient p : foundPatients) {
                Ui.showPatientHistory(p);
            }
        }
    }

    private Patient findPatientByNric(String nric) {
        String object = nric.trim().toUpperCase();
        for (Patient p : patients) {
            String patientId = p.getId().trim().toUpperCase();
            if (patientId.equals(object)) {
                return p;
            }
        }
        return null;
    }


    private List<Patient> findPatientsByName(String name) {
        List<Patient> result = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getName().trim().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    //@@author chwenyee
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Appointment deleteAppointment(String apptId) {
        for (Appointment appointment : appointments) {
            if (appointment.getId().equalsIgnoreCase(apptId)) {
                appointments.remove(appointment);
                return appointment;
            }
        }
        return null;
    }
}
