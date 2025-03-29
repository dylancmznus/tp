package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteAppointmentCommandTest {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    void execute_validNric_expectAppointmentDeleted() {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>());
        Ui ui = new Ui();

        LocalDateTime dateTime = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S1234567D", dateTime, "Checkup");

        new AddAppointmentCommand(appointment).execute(manager, ui);
        new DeleteAppointmentCommand(appointment.getId()).execute(manager, ui);

        assertEquals(0, manager.getAppointments().size(), "Size of appointment does not match");
    }
}
