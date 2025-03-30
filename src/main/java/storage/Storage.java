package storage;

import exception.UnloadedStorageException;
import manager.Appointment;
import manager.Patient;
import miscellaneous.Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String directoryPath;
    private static String patientFilePath;
    private static String appointmentFilePath;

    public Storage(String directory) {
        directoryPath = directory;
        patientFilePath = directory + File.separator + "patient_data.txt";
        appointmentFilePath = directory + File.separator + "appointment_data.txt";
    }

    public static void savePatients(List<Patient> patientList) throws UnloadedStorageException {
        if (directoryPath == null || patientFilePath == null) {
            throw new UnloadedStorageException("Storage not initialized with a directory!");
        }

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientFilePath))) {
            for (Patient patient : patientList) {
                writer.write(patient.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UnloadedStorageException("Unable to save the patient!");
        }
    }

    public static List<Patient> loadPatients() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        File file = new File(patientFilePath);
        if (!file.exists()) {
            return patients;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient patient = Parser.parseLoadPatient(line);
                if (patient != null) {
                    patients.add(patient);
                }
            }
        } catch (Exception e) {
            throw new UnloadedStorageException("Unable to load patient data!");
        }

        return patients;
    }

    public static void saveAppointments(List<Appointment> appointmentList) throws UnloadedStorageException {
        if (directoryPath == null || appointmentFilePath == null) {
            throw new UnloadedStorageException("Storage not initialized with a directory!");
        }

        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(appointmentFilePath))) {
            writer.write("countId:" + Appointment.getRunningId());
            writer.newLine();

            for (Appointment appointment : appointmentList) {
                writer.write(appointment.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UnloadedStorageException("Unable to save the appointment!");
        }
    }


    public static List<Appointment> loadAppointments() throws UnloadedStorageException {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(appointmentFilePath);

        if (!file.exists()) {
            return appointments;
        }

        try (Scanner scanner = new Scanner(file)) {
            int countId = 100;

            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                if (firstLine.startsWith("countId:")) {
                    String[] parts = firstLine.split(":");
                    if (parts.length == 2) {
                        countId = Integer.parseInt(parts[1].trim());
                    }
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Appointment appointment = Parser.parseLoadAppointment(line);
                    if (appointment != null) {
                        appointments.add(appointment);
                    }
                }
            }

            Appointment.setRunningId(countId);

        } catch (Exception e) {
            throw new UnloadedStorageException("Unable to load appointment data!");
        }

        return appointments;
    }
} 
