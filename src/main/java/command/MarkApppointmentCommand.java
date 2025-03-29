package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class MarkApppointmentCommand extends Command {
    protected String apptId;

    public MarkApppointmentCommand(String apptId) {
        this.apptId = apptId;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Appointment appointment = manager.markAppointment(apptId);
        ui.showAppointmentMarked(manager.getAppointments(), appointment, apptId);
    }
}
