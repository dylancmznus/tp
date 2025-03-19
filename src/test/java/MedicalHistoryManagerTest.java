import manager.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MedicalHistoryManagerTest {

    private MedicalHistoryManager medicalHistoryManager;

    @BeforeEach
    void setUp() {
        medicalHistoryManager = new MedicalHistoryManager();
    }

    @Test
    void storeMedicalHistory_storeMedHistoryOnNewPatient_expectOneNewPatientWithMedHistory() {
        // Arrange
        String name = "John Doe";
        String nric = "S1234567A";
        String medHistory = "Diabetes, Hypertension";

        // Act
        medicalHistoryManager.storeMedicalHistory(name, nric, medHistory);

        // Assert
        List<Patient> patients = medicalHistoryManager.getPatients();
        assertEquals(1, patients.size(), "There should be one patient stored");

        Patient storedPatient = patients.get(0);
        assertEquals(name, storedPatient.getName(), "Patient name should match");
        assertEquals(nric, storedPatient.getId(), "Patient NRIC should match");

        List<String> history = storedPatient.getMedicalHistory();
        assertEquals(2, history.size(), "Medical history should contain 2 entries");
        assertTrue(history.contains("Diabetes"), "Medical history should contain 'Diabetes'");
        assertTrue(history.contains("Hypertension"), "Medical history should contain 'Hypertension'");
    }

    @Test
    void viewMedicalHistoryByNric() {
    }

    @Test
    void viewMedicalHistoryByName() {
    }
}