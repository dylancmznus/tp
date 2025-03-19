package exception;

public class DuplicatePatientIDException extends Exception {
    public DuplicatePatientIDException(String message) {
        super(message);
    }
}