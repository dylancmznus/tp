package storage;

import exception.UnloadedStorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import manager.Patient;
import miscellaneous.Parser;

public class Storage {
    private static String directoryPath;
    private static String patientFilePath;

    public Storage(String directory) {
        directoryPath = directory;
        patientFilePath = directory + File.separator + "patient_data.txt";
    }

    public static void savePatients(List<Patient> patientList) throws UnloadedStorageException {
        try {
            if (directoryPath == null || patientFilePath == null) {
                throw new UnloadedStorageException("Storage not initialized with a directory!");
            }

            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(patientFilePath));

            for (Patient patient : patientList) {
                writer.write(patient.toFileFormat());
                writer.newLine();
            }

            writer.close();
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
                Patient patient = Parser.parsePatient(line);
                if (patient != null) {
                    patients.add(patient);
                }
            }
        } catch (Exception e) {
            throw new UnloadedStorageException("Unable to load patient data!");
        }

        return patients;
    }
}
