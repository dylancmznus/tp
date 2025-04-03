package command;

import java.util.List;
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
        List<Appointment> foundAppointments = manager.findAppointmentsByNric(nric);
        ui.showAppointmentsFound(foundAppointments, nric);
    }
}
