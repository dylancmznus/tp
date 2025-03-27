package command;

import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;

//@@author dylancmznus
public class ViewPatientCommand extends Command {
    protected String nric;

    public ViewPatientCommand(String nric) {
        this.nric = nric;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        assert manager != null : "ManagementSystem instance can't be empty";
        assert ui != null : "Ui instance can't be empty";

        Patient matchedPatient = manager.viewPatient(nric);
        assert matchedPatient != null : "No patient found for NRIC: " + nric;

        ui.showPatientViewed(matchedPatient, nric);
    }
}
