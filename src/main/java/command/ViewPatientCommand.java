package command;

import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;

public class ViewPatientCommand extends Command {
    protected String nric;

    public ViewPatientCommand(String nric) {
        this.nric = nric;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Patient matchedPatient = manager.viewPatient(nric);
        ui.showPatientViewed(matchedPatient, nric);
    }
}
