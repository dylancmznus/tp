package manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static int runningId = 100;
    private final String id;
    private final String nric;
    private final LocalDateTime dateTime;
    private final String description;

    public Appointment(String nric, LocalDateTime dateTime, String description) {
        this.id = "A" + runningId++;
        this.nric = nric;
        this.dateTime = dateTime;
        this.description = description;
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

    @Override
    public String toString() {
        return "[" + id + "] - " + nric + " - " + dateTime.format(DATE_TIME_FORMAT)  + " - " + description;
    }
}
