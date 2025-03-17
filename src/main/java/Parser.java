public class Parser {
    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static boolean isAddPatient(String input) {
        return input.toLowerCase().startsWith("add-patient");
    }

    public static boolean isDeletePatient(String input) {
        return input.toLowerCase().startsWith("delete-patient");
    }

    public static boolean isViewPatient(String input) {
        return input.toLowerCase().startsWith("view-patient");
    }

    public static boolean isViewHistory(String input) {
        return input.toLowerCase().startsWith("view-history");
    }

    public static boolean isAddAppointment(String input) {
        return input.toLowerCase().startsWith("add-appointment");
    }

    public static boolean isDeleteAppointment(String input) {
        return input.toLowerCase().startsWith("delete-appointment");
    }

    public static boolean isListAppointments(String input) {
        return input.equalsIgnoreCase("list-appointments");
    }

    public static String[] parseAddAppointment(String input) {
        String temp = input.replaceFirst("(?i)add-appointment\\s*", "");
        String nric = extractValue(temp, "ic/");
        String date = extractValue(temp, "dt/");
        String time = extractValue(temp, "t/");
        String desc = extractValue(temp, "dsc/");
        if (nric == null || date == null || time == null || desc == null) {
            return null;
        }
        return new String[]{nric.trim(), date.trim(), time.trim(), desc.trim()};
    }

    public static String parseDeleteAppointment(String input) {
        String temp = input.replaceFirst("(?i)delete-appointment\\s*", "");
        return temp.isBlank() ? null : temp.trim();
    }

    public static String parseDeletePatient(String input) {
        return input.replaceFirst("(?i)delete-patient\\s*", "").trim();
    }

    public static String parseViewPatient(String input) {
        return input.replaceFirst("(?i)view-patient\\s*", "").trim();
    }

    public static String parseViewHistory(String input) {
        return input.replaceFirst("(?i)view-history\\s*", "").trim();
    }

    private static String extractValue(String input, String prefix) {
        int start = input.toLowerCase().indexOf(prefix.toLowerCase());
        if (start < 0) {
            return null;
        }
        start += prefix.length();
        String[] possible = {"ic/", "dt/", "t/", "dsc/"};
        int end = input.length();
        for (String p : possible) {
            if (p.equalsIgnoreCase(prefix)) {
                continue;
            }
            int idx = input.toLowerCase().indexOf(p.toLowerCase(), start);
            if (idx >= 0 && idx < end) {
                end = idx;
            }
        }
        return input.substring(start, end).trim();
    }
}
