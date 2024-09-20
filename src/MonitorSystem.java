import java.util.concurrent.Semaphore;

public class MonitorSystem {
	public static void main(String[] args) {
		
        final int NUM_STUDENTS = 5; //Number of students
        Semaphore monitorAvailable = new Semaphore(0); // Sempahore for monitor
        Semaphore chairs = new Semaphore(3); // Semaphore for the 3 chairs

        // Create and run monitor thread
        Monitor monitor = new Monitor(monitorAvailable, chairs);
        new Thread(monitor).start();

        // Create and run student threads
        for (int i = 1; i <= NUM_STUDENTS; i++) {
            new Thread(new Student(i, monitorAvailable, chairs)).start();
        }
    }
}
