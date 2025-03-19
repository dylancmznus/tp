public class ClinicEase {
    public static void main(String[] args) {
        ManagementSystem manager = new ManagementSystem();
        MedicalHistoryManager mhManager = new MedicalHistoryManager();
        Ui ui = new Ui();
        ui.showWelcome();

        boolean running = true;
        while (running) {
            try {
                String input = ui.readCommand();

                if (Parser.isBye(input)) {
                    ui.showBye();
                    running = false;
                } else if (Parser.isViewHistory(input)) {
                    // 解析用户的 view-history 命令，获得 [type, value]
                    String[] typeAndValue = Parser.parseViewHistory(input);
                    String type = typeAndValue[0];
                    String value = typeAndValue[1];

                    // 根据 type 判断调用哪个方法
                    if ("ic".equals(type)) {
                        mhManager.viewMedicalHistoryByNric(value);
                    } else {
                        mhManager.viewMedicalHistoryByName(value);
                    }
                } else if (Parser.isStoreHistory(input)) {
                    // 解析 store-history 命令
                    String[] details = Parser.parseStoreHistory(input);
                    if (details == null) {
                        System.out.println("Invalid format. Use: store-history n/NAME ic/NRIC h/MEDICAL_HISTORY");
                    } else {
                        // details = [name, nric, medHistory]
                        mhManager.storeMedicalHistory(details[0], details[1], details[2]);
                    }
                } else if (Parser.isAddAppointment(input)) {
                    String[] details = Parser.parseAddAppointment(input);
                    manager.addAppointment(details);
                } else if (Parser.isDeleteAppointment(input)) {
                    String apptId = Parser.parseDeleteAppointment(input);
                    if (apptId == null || apptId.isEmpty()) {
                        System.out.println("Invalid format. Use: delete-appointment APPOINTMENT_ID");
                    } else {
                        manager.deleteAppointment(apptId);
                    }
                } else if (Parser.isListAppointments(input)) {
                    manager.listAppointments();
                } else {
                    System.out.println("Unknown command.");
                }
            } catch (ClinicEaseException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
