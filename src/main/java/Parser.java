import exception.InvalidInputFormatException;

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

    public static String[] parseAddAppointment(String input) throws InvalidInputFormatException {
        String temp = input.replaceFirst("(?i)add-appointment\\s+", "");
        String nric = extractValue(temp, "ic/");
        String date = extractValue(temp, "dt/");
        String time = extractValue(temp, "t/");
        String desc = extractValue(temp, "dsc/");
        if (nric == null || date == null || time == null || desc == null) {
            throw new InvalidInputFormatException("Missing details or wrong format for add-appointment!" + System.lineSeparator() +
                    "Please follow this format: add-appointment ic/NRIC dt/MM-DD t/HHmm dsc/DESCRIPTION");
        }
        return new String[]{nric.trim(), date.trim(), time.trim(), desc.trim()};
    }

    public static String parseDeleteAppointment(String input) throws InvalidInputFormatException {
        if (!input.matches("(?i)delete-appointment\\s+A\\d+")) {
            throw new InvalidInputFormatException("Invalid format. Please follow this format: " +
                    "delete-appointment APPOINTMENT_ID");
        }
        return input.replaceFirst("(?i)delete-appointment\\s*", "").trim();
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
        String lowerInput = input.toLowerCase();
        String lowerPrefix = prefix.toLowerCase();
        int start = -1;

        // Find the first occurrence of the prefix that is either at the start or come before blank space
        // Ensure checks are not done at where the prefix can’t fully fit
        for (int i = 0; i <= lowerInput.length() - lowerPrefix.length(); i++) {
            boolean isParamPrefixMatch = lowerInput.startsWith(lowerPrefix, i);
            // Check if the character before the prefix is blank space in input to have a valid input format
            boolean isParamAtValidPosition = (i == 0) || Character.isWhitespace(input.charAt(i - 1));
            if (isParamPrefixMatch && isParamAtValidPosition) {
                start = i;
                break;
            }
        }

        if (start < 0) {
            return null;
        }

        start += prefix.length();
        String[] possible = {"ic/", "dt/", "t/", "dsc/"};
        int end = input.length();

        // Determine where the current parameter's detail ends by finding the start of the next parameter
        for (String p : possible) {
            if (p.equalsIgnoreCase(prefix)) {
                continue;
            }
            String lowerP = p.toLowerCase();
            // Find the next occurrence of p that is either at the start or come before blank space
            for (int i = start; i <= lowerInput.length() - lowerP.length(); i++) {
                boolean isNextParamPrefixMatch = lowerInput.startsWith(lowerP, i);
                // Check if the character before the prefix is blank space in input to have a valid input format
                boolean isNextParamAtValidPosition = (i == 0) || Character.isWhitespace(input.charAt(i - 1));
                if (isNextParamPrefixMatch && isNextParamAtValidPosition) {
                    if (i < end) {
                        end = i;
                    }
                    break;
                }
            }
        }

        String detail = input.substring(start, end).trim();
        return detail.isEmpty() ? null : detail;
    }
}
