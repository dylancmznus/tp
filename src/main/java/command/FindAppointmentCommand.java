package command;

import manager.Appointment;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class FindAppointmentCommand extends Command {
    protected String nric;

    public FindAppointmentCommand(String nric) {
        this.nric = nric;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Appointment foundAppointment = manager.findAppointmentByNric(nric);
        ui.showAppointmentFound(foundAppointment, nric);
    }
}
