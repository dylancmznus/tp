package clinicease_test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import MainFunction.ManagementSystem;
import org.junit.jupiter.api.Test;

class Testing {
    @Test
    public void testAddPatient_Success() {
        ManagementSystem system = new ManagementSystem();
        String input = "add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St";

        system.addPatient(input);

        assertTrue(system.getPatient().containsKey("S1234567A"));
    }
}

