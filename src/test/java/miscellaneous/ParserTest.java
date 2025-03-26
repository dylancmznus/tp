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

    @Test
    void parseViewHistory_validNric() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history S1234567D");
        assertEquals("ic", result[0]);
        assertEquals("S1234567D", result[1]);
    }

    @Test
    void parseViewHistory_validName() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history John Doe");
        assertEquals("n", result[0]);
        assertEquals("John Doe", result[1]);
    }

    @Test
    void parseViewHistory_explicitNricPrefix() throws InvalidInputFormatException {
        String[] result = Parser.parseViewHistory("view-history ic/S1234567A");
        assertEquals("ic", result[0]);
        assertEquals("S1234567A", result[1]);
    }

    @Test
    void parseViewHistory_invalidInput_expectException() {
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseViewHistory("view-history"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseViewHistory("view-history  "));
    }

    @Test
    void parseStoreHistory_validInput_expectSuccess() throws InvalidInputFormatException {
        String[] result = Parser.parseStoreHistory("store-history n/John Doe ic/S1234567D h/Allergic to nuts");
        assertEquals("John Doe", result[0]);
        assertEquals("S1234567D", result[1]);
        assertEquals("Allergic to nuts", result[2]);
    }

    @Test
    void parseStoreHistory_missingFields_expectException() {
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history ic/S1234567D h/Allergic to nuts"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history n/John Doe h/Allergic to nuts"));
        assertThrows(InvalidInputFormatException.class, () ->
                Parser.parseStoreHistory("store-history n/John Doe ic/S1234567D"));
    }
}

