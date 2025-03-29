package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddAppointmentCommandTest {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    void execute_validAppointment_expectAppointmentAdded() {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>());
        Ui ui = new Ui();

        LocalDateTime dateTime = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S1234567D", dateTime, "Checkup");

        new AddAppointmentCommand(appointment).execute(manager, ui);

        List<Appointment> appointments = manager.getAppointments();
        assertEquals(1, appointments.size(), "Size of appointments should be 1");
        assertEquals(appointment, appointments.get(0));
    }
}
