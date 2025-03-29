package command;

import exception.DuplicatePatientIDException;
import exception.UnloadedStorageException;
import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.util.List;

public class SortAppointmentCommand extends Command {
    protected String type;

    public SortAppointmentCommand(String type) {
        this.type = type;
    }

    @Override
    public void execute (ManagementSystem manager, Ui ui) throws DuplicatePatientIDException,
            UnloadedStorageException {
        if (type.equals("date")) {
            List<Appointment> sortedApptByDateTime = manager.sortAppointmentsByDateTime(manager.getAppointments());
            ui.showAppointmentList(sortedApptByDateTime);
        } else {
            List<Appointment> sortedApptById = manager.sortAppointmentsById(manager.getAppointments());
            ui.showAppointmentList(sortedApptById);
        }
    }
}
