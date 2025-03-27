package command;

import exception.DuplicatePatientIDException;
import exception.UnloadedStorageException;
import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.util.List;

public class SortAppointmentCommand extends Command {
    @Override
    public void execute (ManagementSystem manager, Ui ui) throws DuplicatePatientIDException,
            UnloadedStorageException {
        List<Appointment> sortedAppointment = manager.sortAppointments(manager.getAppointments());
        ui.showAppointmentList(sortedAppointment);
    }
}
