package miscellaneous;

import exception.InvalidInputFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {
    @Test
    void parseAddAppointment_correctFormat_expectSuccessfulParsing() throws InvalidInputFormatException {
        String input = "add-appointment ic/S1234567D dt/03-19 t/1200 dsc/medical check-up";

        String[] result = Parser.parseAddAppointment(input);

        assertNotNull(result, "Result should not be null");
        assertEquals(4, result.length, "Result array should have 4 elements");
        assertEquals("S1234567D", result[0], "NRIC does not match");
        assertEquals("03-19", result[1], "Date does not match");
        assertEquals("1200", result[2], "Time does not match");
        assertEquals("medical check-up", result[3], "Description does not match");
    }


    @Test
    void parseAddAppointment_invalidFormat_expectException() {
        String input = "add-appointment ic/S1234567D dt/03-19 t/1200";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input));
    }

    @Test
    void parseAddAppointment_missingDetail_expectedException() {
        String input = "add-appointment ic/ dt/03-19 t/1200 dsc/medical check-up";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseAddAppointment(input));
    }

    @Test
    void parseDeleteAppointment_validInput_expectedSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment A100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("A100", result, "Appointment ID does not match");
    }

    @Test
    void parseDeleteAppointment_extraSpaces_expectSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment    A100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("A100", result, "Appointment ID does not match");
    }

    @Test
    void parseDeleteAppointment_invalidAppointmentId_expectedException() {
        String input = "delete-appointment 100";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseDeleteAppointment(input));
    }

    @Test
    void parseDeleteAppointment_lowercaseInput_expectSuccess() throws InvalidInputFormatException {
        String input = "delete-appointment a100";
        String result = Parser.parseDeleteAppointment(input);

        assertEquals("a100", result, "Appointment ID does not match");
    }

    @Test
    void parseAddPatient_invalidInputFormat_expectException() {
        String input = "add-patient n/John Doe ic/ dob/12-12-1999 g/M " +
                "p/98765432 a/123 Main Street h/Diabetes, Hypertension";

        assertThrows(InvalidInputFormatException.class, () -> Parser.parseDeleteAppointment(input));
    }
}
