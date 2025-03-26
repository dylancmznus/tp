package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class DeleteAppointmentCommand extends Command {
    protected String apptId;

    public DeleteAppointmentCommand(String apptId) {
        this.apptId = apptId;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Appointment removedAppointment = manager.deleteAppointment(apptId);
        ui.showAppointmentDeleted(manager.getAppointments(), removedAppointment, apptId);
    }
}
