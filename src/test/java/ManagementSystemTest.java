import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagementSystemTest {

    @Test
    // methodBeingTested_conditionBeingTested_expectedOutcome
    // happy path
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
        assertEquals("medical check-up", addedAppointment.getDescription(), "Description does not match");
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