import java.util.Scanner;

public class ClinicEase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        ManagementSystem manager = new ManagementSystem();

        if(line.startsWith("add")){
            manager.addPatient(line);
        }
        if(line.startsWith("delete")) {
            manager.deletePatient(line);
        }
    }
}
