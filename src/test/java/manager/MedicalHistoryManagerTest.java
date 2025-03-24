package manager;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicalHistoryManagerTest {

    @Test
    void storeMedicalHistory_storeMedHistoryOnNewPatient_expectOneNewPatientWithMedHistory() {
        MedicalHistoryManager mhm = new MedicalHistoryManager();

        String name = "John Doe";
        String nric = "S1234567A";
        String medHistory = "Diabetes, Hypertension";

        mhm.storeMedicalHistory(name, nric, medHistory);

        List<Patient> patients = mhm.getPatients();
        assertEquals(1, patients.size(), "There should be one patient stored");

        Patient storedPatient = patients.get(0);
        assertEquals(name, storedPatient.getName(), "Patient name should match");
        assertEquals(nric, storedPatient.getId(), "Patient NRIC should match");

        List<String> history = storedPatient.getMedicalHistory();
        assertEquals(2, history.size(), "Medical history should contain 2 entries");
        assertTrue(history.contains("Diabetes"), "Medical history should contain 'Diabetes'");
        assertTrue(history.contains("Hypertension"), "Medical history should contain 'Hypertension'");
    }

}
