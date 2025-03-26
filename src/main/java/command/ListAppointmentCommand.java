package command;

import manager.ManagementSystem;
import miscellaneous.Ui;

public class ListAppointmentCommand extends Command {
    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        ui.showAppointmentList(manager.getAppointments());
    }
}
