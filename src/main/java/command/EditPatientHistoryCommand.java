package command;

import manager.ManagementSystem;
import miscellaneous.Ui;

public class EditPatientHistoryCommand extends Command {

    private final String[] details;

    public EditPatientHistoryCommand(String[] details) {
        this.details = details;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) {
        String nric = details[0];
        String oldHistory = details[1];
        String newHistory = details[2];

        manager.editPatientHistory(nric, oldHistory, newHistory);

        Ui.showLine();
        System.out.println("Edit-history command executed.");
        Ui.showLine();
    }
}
