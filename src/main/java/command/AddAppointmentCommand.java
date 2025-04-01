package command;

import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class AddAppointmentCommand extends Command {

    protected Appointment appointment;

    public AddAppointmentCommand(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException, PatientNotFoundException {
        manager.addAppointment(appointment);
        ui.showAppointmentAdded(manager.getAppointments());
    }

}
