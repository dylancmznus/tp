package manager;

import exception.DuplicatePatientIDException;
import exception.InvalidInputFormatException;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagementSystemTest {

    @Test
    void addPatient_correctInput_expectOneNewPatient() throws DuplicatePatientIDException,
            InvalidInputFormatException {
        ManagementSystem system = new ManagementSystem();
        String input = "add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St";

        system.addPatient(input);

        assertTrue(system.getPatient().containsKey("S1234567A"));
    }

    @Test
    void addPatient_duplicatePatientID_expectException() throws DuplicatePatientIDException,
            InvalidInputFormatException {
        ManagementSystem system = new ManagementSystem();
        String input1 = "add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St";
        String input2 = "add-patient n/John Smith ic/S1234567A dob/01-10-1999 g/M p/91207878 a/123 High St";

        system.addPatient(input1);

        assertThrows(DuplicatePatientIDException.class, () -> system.addPatient(input2));
    }

    @Test
    void deletePatient_correctInput_expectPatientDeleted() throws DuplicatePatientIDException,
            InvalidInputFormatException {
        ManagementSystem system = new ManagementSystem();

        system.addPatient("add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St");
        system.deletePatient("delete-patient S1234567A");

        assertFalse(system.getPatient().containsKey("S1234567A"));
    }

    @Test
    void viewPatient_correctInput_expectSpecifiedPatientViewed() throws InvalidInputFormatException,
            DuplicatePatientIDException {
        ManagementSystem system = new ManagementSystem();

        system.addPatient("add-patient n/John Doe ic/S1234567A dob/01-01-1990 g/M p/98765432 a/123 Main St");

        system.viewPatient("view-patient S1234567A");

        assertTrue(system.getPatient().containsKey("S1234567A"));
    }

    @Test
    void addAppointment_correctInput_expectOneNewAppointment() {
        ManagementSystem ms = new ManagementSystem();
        String[] details = {"S1234567D", "03-10", "1200", "medical check-up"};

        List<Appointment> appointments = ms.getAppointments();
        ms.addAppointment(details);

        assertEquals(1, appointments.size(), "Expected one appointment to be added");

        Appointment addedAppointment = appointments.get(0);
        assertEquals("S1234567D", addedAppointment.getNric(), "NRIC does not match");
        assertEquals("03-10", addedAppointment.getDate(), "Date does not match");
        assertEquals("1200", addedAppointment.getTime(), "Time does not match");
        assertEquals("medical check-up", addedAppointment.getDescription(),
                "Description does not match");
    }


    @Test
    void deleteAppointment_existingId_expectAppointmentDeleted()  {
        ManagementSystem ms = new ManagementSystem();
        String[] details = {"S1234567D", "03-10", "1200", "medical check-up"};

        List<Appointment> appointments = ms.getAppointments();
        ms.addAppointment(details);

        String apptId = ms.getAppointments().get(0).getId();
        ms.deleteAppointment(apptId);

        assertEquals(0, appointments.size(), "Expected one appointment to be deleted");
    }

    @Test
    void deleteAppointment_nonExistingId_expectNoAppointmentDeleted()  {
        ManagementSystem ms = new ManagementSystem();
        String[] details = {"S1234567D", "03-10", "1200", "medical check-up"};

        List<Appointment> appointments = ms.getAppointments();
        ms.addAppointment(details);

        String nonExistingId = "A999";
        ms.deleteAppointment(nonExistingId);

        assertEquals(1, appointments.size(), "Expected previous appointment to be in the list.");
    }

}
