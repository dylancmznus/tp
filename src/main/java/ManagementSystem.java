import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private final List<Appointment> appointments;

    public ManagementSystem() {
        appointments = new ArrayList<>();
    }

    public void addAppointment(String[] details) {
//        String nric = details[0];
//        String date = details[1];
//        String time = details[2];
//        String desc = details[3];
//        Appointment appt = new Appointment(nric, date, time, desc);
//        appointments.add(appt);
//        System.out.println("Appointment added for NRIC " + nric + " on " + date + " at " + time + ".");
    }

    public void deleteAppointment(String apptId) {
//        for (int i = 0; i < appointments.size(); i++) {
//            if (appointments.get(i).getId().equalsIgnoreCase(apptId)) {
//                appointments.remove(i);
//                System.out.println("Appointment " + apptId + " is deleted successfully.");
//                return;
//            }
//        }
//        System.out.println("No appointment found with ID " + apptId + ".");
    }

    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("Appointments:");
        int count = 1;
        for (Appointment a : appointments) {
            System.out.println(count + ". " + a);
            count++;
        }
    }
}
