package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class UnmarkAppointmentCommand extends Command {
    protected String apptId;

    public UnmarkAppointmentCommand(String apptId) {
        this.apptId = apptId;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Appointment appointment = manager.unmarkAppointment(apptId);
        ui.showAppointmentUnmarked(manager.getAppointments(), appointment, apptId);
    }
}
