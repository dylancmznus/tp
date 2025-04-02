package command;

import exception.PatientNotFoundException;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class ViewMedHistoryCommand extends Command {
    protected String type;
    protected String nameOrIc;

    public ViewMedHistoryCommand(String[] details) {
        this.type = details[0];
        this.nameOrIc = details[1];
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws PatientNotFoundException {
        if (type.equals("ic")) {
            manager.viewMedicalHistoryByNric(nameOrIc);
        } else {
            manager.viewMedicalHistoryByName(nameOrIc);
        }
    }
}
