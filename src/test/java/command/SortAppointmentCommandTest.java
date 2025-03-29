package command;

import exception.DuplicatePatientIDException;
import exception.UnloadedStorageException;
import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SortAppointmentCommandTest {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    void execute_appointmentsWithDifferentDateTime_sortsByDateInAscendingOrder() throws DuplicatePatientIDException,
            UnloadedStorageException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>());
        Ui ui = new Ui();

        LocalDateTime dateTime1 = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        LocalDateTime dateTime2 = LocalDateTime.parse("2025-03-28 2000", DATE_TIME_FORMAT);
        LocalDateTime dateTime3 = LocalDateTime.parse("2025-03-23 1200", DATE_TIME_FORMAT);


        List<Appointment> appointments = List.of(
                new Appointment("S1234567D", dateTime1, "Checkup"),
                new Appointment("S2345678D", dateTime2, "CT scan"),
                new Appointment("S3456789D", dateTime3, "Consultation")
        );
        manager.addAppointment(appointments.get(0));
        manager.addAppointment(appointments.get(1));
        manager.addAppointment(appointments.get(2));

        new SortAppointmentCommand("date").execute(manager, ui);
        List<Appointment> sortedAppointments = manager.getAppointments();

        assertEquals(3, sortedAppointments.size(), "Size of appointment does not match");
        assertEquals("Consultation", sortedAppointments.get(0).getDescription());
        assertEquals("Checkup", sortedAppointments.get(1).getDescription());
        assertEquals("CT scan", sortedAppointments.get(2).getDescription());
    }

    @Test
    void execute_appointmentsFirstSortedByDateThenId_sortsByIdInAscendingOrder() throws DuplicatePatientIDException,
            UnloadedStorageException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>());
        Ui ui = new Ui();

        LocalDateTime dateTime1 = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        LocalDateTime dateTime2 = LocalDateTime.parse("2025-03-28 2000", DATE_TIME_FORMAT);
        LocalDateTime dateTime3 = LocalDateTime.parse("2025-03-23 1200", DATE_TIME_FORMAT);


        List<Appointment> appointments = List.of(
                new Appointment("S1234567D", dateTime1, "Checkup"),
                new Appointment("S2345678D", dateTime2, "CT scan"),
                new Appointment("S3456789D", dateTime3, "Consultation")
        );
        manager.addAppointment(appointments.get(0));
        manager.addAppointment(appointments.get(1));
        manager.addAppointment(appointments.get(2));

        new SortAppointmentCommand("date").execute(manager, ui);
        new SortAppointmentCommand("id").execute(manager, ui);
        List<Appointment> sortedAppointments = manager.getAppointments();

        assertEquals(3, sortedAppointments.size(), "Size of appointment does not match");
        assertEquals("Checkup", sortedAppointments.get(0).getDescription());
        assertEquals("CT scan", sortedAppointments.get(1).getDescription());
        assertEquals("Consultation", sortedAppointments.get(2).getDescription());
    }

    @Test
    void execute_emptyList_expectEmptyList() throws DuplicatePatientIDException, UnloadedStorageException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>());
        Ui ui = new Ui();

        new SortAppointmentCommand("date").execute(manager, ui);
        assertTrue(manager.getAppointments().isEmpty());
    }
}
