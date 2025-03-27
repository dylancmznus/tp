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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ManagementSystemTest {

    private static final String TEST_DIR = "test-data";
    private static final Path TEST_FILE_PATH = Paths.get(TEST_DIR, "patient_data.txt");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


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
    void addPatient_validInput_expectPatientAddedAndSaved()
            throws DuplicatePatientIDException, UnloadedStorageException, IOException {
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

    @Test
    void addAppointment_validInput_expectAppointmentAdded() {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);

        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        String expectedNric = appointment.getNric();
        manager.addAppointment(appointment);

        assertEquals(1, manager.getAppointments().size(), "Size does not match");
        assertEquals(expectedNric, manager.getAppointments().get(0).getNric(), "NRIC does not match");
        assertEquals(LocalDate.of(2025, 3, 20), manager.getAppointments().get(0).getDate());
        assertEquals(LocalTime.of(19, 0), manager.getAppointments().get(0).getTime());
    }

    @Test
    void deleteAppointment_validInput_expectAppointmentDeleted() {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);

        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        String expectedId = appointment.getId();
        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment(expectedId);

        assertNotNull(removedAppointment, "Deleted appointment should be returned");
        assertEquals(expectedId, appointment.getId(), "Appointment ID does not match");
        assertEquals(0, manager.getAppointments().size(), "Size does not match");
    }

    @Test
    void deleteAppointment_lowerCaseInput_expectAppointmentDeleted() {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-30 2200", DATE_TIME_FORMAT);

        Appointment appointment = new Appointment("S3456789D", appointmentTime, "Checkup");

        String expectedId = appointment.getId();
        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment(expectedId);

        assertNotNull(removedAppointment, "Deleted appointment should be returned");
        assertEquals(expectedId, appointment.getId(), "Appointment ID does not match");
        assertEquals(0, manager.getAppointments().size(), "Size does not match");
    }

    @Test
    void deleteAppointment_nonExistentId_expectNullReturned() {
        List<Patient> emptyList = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyList);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-25 2100", DATE_TIME_FORMAT);

        Appointment appointment = new Appointment("S2345678D", appointmentTime, "Checkup");

        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment("A999");

        assertEquals(1, manager.getAppointments().size(), "Size does not match");
        assertNull(removedAppointment, "Should return null");
    }

}
