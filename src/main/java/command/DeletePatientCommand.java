package command;

import exception.UnloadedStorageException;
import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;

public class DeletePatientCommand extends Command {
    protected String nric;

    public DeletePatientCommand(String nric) {
        this.nric = nric;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException {
        Patient removedPatient = manager.deletePatient(nric);
        ui.showPatientDeleted(removedPatient, nric);
    }
}
