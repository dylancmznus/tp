package cetest;

import MainFunction.ManagementSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    @Test
    public void testAddPatientSuccess() {
        ManagementSystem system = new ManagementSystem();
        String input = "add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St";

        system.addPatient(input);

        assertTrue(system.getPatient().containsKey("S1234567A"));
    }

    @Test
    public void testDeletePatient() {
        ManagementSystem system = new ManagementSystem();

        system.addPatient("add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St");

        system.deletePatient("delete-patient S1234567A");

        assertFalse(system.getPatient().containsKey("S1234567A"));
    }

    @Test
    public void testViewPatientExpectedDetails() {
        ManagementSystem system = new ManagementSystem();

        system.addPatient("add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St");

        system.viewPatient("view-patient S1234567A");

        assertTrue(system.getPatient().containsKey("S1234567A"));
    }
}

