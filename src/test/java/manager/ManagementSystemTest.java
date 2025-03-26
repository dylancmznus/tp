package manager;

import exception.DuplicatePatientIDException;
import exception.UnloadedStorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ManagementSystemTest {

    private static final String TEST_DIR = "test-data";
    private static final Path TEST_FILE_PATH = Paths.get(TEST_DIR, "patient_data.txt");

    @BeforeEach
    void setUp() throws IOException {
        new Storage(TEST_DIR);
        Files.deleteIfExists(TEST_FILE_PATH);
    }

    @Test
    void addPatient_validInput_expectPatientAdded() throws DuplicatePatientIDException, UnloadedStorageException {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());

        manager.addPatient(patient);

        assertEquals(1, manager.getPatients().size());
        assertEquals("John Doe", manager.getPatients().get(0).getName());
    }

    @Test
    void addPatient_duplicateId_expectExceptionThrown() throws DuplicatePatientIDException, UnloadedStorageException {
        List<Patient> existing = new ArrayList<>();
        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        existing.add(patient);

        ManagementSystem manager = new ManagementSystem(existing);

        Patient duplicate = new Patient("S1234567A", "Jane Smith", "1992-02-02",
                "F", "456 Sample Rd", "90000000", new ArrayList<>());

        assertThrows(DuplicatePatientIDException.class, () -> manager.addPatient(duplicate));
    }

    @Test
    void addPatient_validInput_expectPatientAddedAndSaved() throws DuplicatePatientIDException, UnloadedStorageException, IOException {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        manager.addPatient(patient);

        assertTrue(Files.exists(TEST_FILE_PATH), "Patient file was not created.");

        List<String> lines = Files.readAllLines(TEST_FILE_PATH);
        assertFalse(lines.isEmpty(), "Patient file is empty.");

        String line = lines.get(0);
        assertTrue(line.contains("John Doe"), "Saved patient name not found in file.");
        assertTrue(line.contains("S1234567A"), "Saved patient NRIC not found in file.");
    }
}
