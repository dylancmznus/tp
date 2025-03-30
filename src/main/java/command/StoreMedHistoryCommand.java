package command;

import exception.UnloadedStorageException;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class StoreMedHistoryCommand extends Command {
    private String name;
    private String nric;
    private String medHistory;

    public StoreMedHistoryCommand(String[] details) {
        this.name = details[0];
        this.nric = details[1];
        this.medHistory = details[2];
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException {
        manager.storeMedicalHistory(name, nric, medHistory);
    }
}
