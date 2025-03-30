package manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        this.id = "A" + runningId++;
        this.nric = nric;
        this.dateTime = dateTime;
        this.description = description;
        this.isDone = false;
    }

    public String getId() {
        return id;
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
                + nric + " - " + dateTime.format(OUTPUT_FORMAT)  + " - " + description;
    }
}
