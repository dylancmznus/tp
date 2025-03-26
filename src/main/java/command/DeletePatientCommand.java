package command;

import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;

public class DeletePatientCommand extends Command {
    protected String nric;

    public DeletePatientCommand(String nric) {
        this.nric = nric;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        Patient removedPatient = manager.deletePatient(nric);
        ui.showPatientDeleted(removedPatient, nric);
    }
}
