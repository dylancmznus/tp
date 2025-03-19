package miscellaneous;

import exception.ClinicEaseException;

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


    public static boolean isListPatient(String input) {
        return input.toLowerCase().startsWith("list-patient");
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


    public static String[] parseAddPatient(String input) {
        String temp = input.replaceFirst("(?i)add-patient\\s*", "");
        String name = extractValue(temp, "n/");
        String nric = extractValue(temp, "ic/");
        String birthdate = extractValue(temp, "dob/");
        String gender = extractValue(temp, "g/");
        String phone = extractValue(temp, "p/");
        String address = extractValue(temp, "a/");
        if (name == null || nric == null || birthdate == null || gender == null || phone == null || address == null) {
            return null;
        }
        return new String[]{nric.trim(), name.trim(), birthdate.trim(), gender.trim(), address.trim(), phone.trim()};
    }

    public static String[] parseViewHistory(String input) throws ClinicEaseException {
        // Remove the command prefix "view-history" (case-insensitive) and get the remaining string.
        String temp = input.replaceFirst("(?i)view-history\\s*", "");
        String type;
        String nameOrIc;

        // Check if the remaining string starts with "ic/" or "n/" (case-insensitive).
        if (temp.toLowerCase().startsWith("ic/")) {
            type = "ic";
            // Extract the real content after "ic/" using extractValue(...)
            nameOrIc = extractValue(temp, "ic/");
        } else {
            // If there's no explicit prefix, try to detect NRIC vs. name.
            // Uses a simple regex matching a 9-character format: e.g., S1234567A
            if (temp.matches("^[A-Za-z]\\d{7}[A-Za-z]$")) {
                type = "ic";
                nameOrIc = temp.trim();
            } else {
                // Otherwise, assume it's a name
                type = "n";
                nameOrIc = temp.trim();
            }
        }

        // Return null if the parsed value is null or empty
        if (nameOrIc == null || nameOrIc.isEmpty()) {
            throw new ClinicEaseException("Invalid format. Use: view-history NRIC or view-history NAME");
        }

        // Return the result as [type, value]
        return new String[]{type, nameOrIc};
    }

    public static String[] parseStoreHistory(String input) throws ClinicEaseException{
        // Remove the command prefix "store-history" (case-insensitive)
        // and get the remaining string.
        String temp = input.replaceFirst("(?i)store-history\\s*", "");

        // Extract n/NAME, ic/NRIC, and h/MEDICAL_HISTORY from the remaining string
        String name = extractValue(temp, "n/");
        String nric = extractValue(temp, "ic/");
        String medHistory = extractValue(temp, "h/");

        // If any part is missing, return null to indicate a parse failure
        if (name == null || nric == null || medHistory == null) {
            throw new ClinicEaseException("Invalid format. Use: store-history n/NAME ic/NRIC h/MEDICAL_HISTORY");
        }

        // Return the trimmed values as an array
        return new String[]{name.trim(), nric.trim(), medHistory.trim()};
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
        String[] possible = {"n/", "ic/", "dob/", "g/", "p/", "a/", "dt/", "t/", "dsc/", "h/"};
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

    public static boolean isStoreHistory(String input) {
        return input.toLowerCase().startsWith("store-history");
    }


}
