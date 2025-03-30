package manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class Appointment {

    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");

    private static int runningId = 100;
    private final String id;
    private final String nric;
    private final LocalDateTime dateTime;
    private final String description;
    private boolean isDone;

    public Appointment(String nric, LocalDateTime dateTime, String description) {
        assert nric != null && !nric.isBlank() : "NRIC cannot be null or blank";
        assert dateTime != null : "DateTime cannot be null";
        assert description != null && !description.isBlank() : "Description cannot be null or blank";
        
        this.id = "A" + runningId++;
        this.nric = nric;
        this.dateTime = dateTime;
        this.description = description;
        this.isDone = false;
    }

    public Appointment(String id, String nric, LocalDateTime dateTime, String description) {
        this.id = id;
        this.nric = nric;
        this.dateTime = dateTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public static void setRunningId(int newId) {
        runningId = newId;
    }

    public String getNric() {
        return nric;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static int getRunningId() {
        return runningId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + id + "]" + "[" + this.getStatusIcon() + "]" + " - "
                + nric + " - " + dateTime.format(OUTPUT_FORMAT) + " - " + description;
    }

    public String toFileFormat() {
        return id.substring(1) + "|" + this.nric + "|" + dateTime.format(OUTPUT_FORMAT) + "|" + this.description;
    }

    //THIS IS FOR DEBUGGING GRADLE
}
