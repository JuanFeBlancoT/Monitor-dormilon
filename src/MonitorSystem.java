import java.util.concurrent.Semaphore;

public class MonitorSystem {
	public static void main(String[] args) {
		
        final int NUM_STUDENTS = 5; //Number of students
        
        Semaphore monitorSleeping = new Semaphore(0); 
        Semaphore monitorBusy = new Semaphore(1);      
        Semaphore chairs = new Semaphore(3);            

        // Create and run Monitor
        Monitor monitor = new Monitor(monitorSleeping, monitorBusy, chairs);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        // Create and run Students
        for (int i = 0; i < NUM_STUDENTS; i++) { // Cambia el número según la cantidad de estudiantes deseada
            Student student = new Student(i, monitorSleeping, monitorBusy, chairs);
            Thread studentThread = new Thread(student, "Student " + (i + 1));
            studentThread.start();
        }
    } 
}
