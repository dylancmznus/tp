package command;

import manager.ManagementSystem;
import miscellaneous.Ui;

public class ListPatientCommand extends Command {
    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        ui.showPatientList(manager.getPatients());
    }
}
