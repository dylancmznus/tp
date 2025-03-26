package command;

import exception.DuplicatePatientIDException;
import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;

public class AddPatientCommand extends Command {

    protected Patient patient;

    public AddPatientCommand(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws DuplicatePatientIDException {
        manager.addPatient(patient);
        ui.showPatientAdded(manager.getPatients());
    }
}
