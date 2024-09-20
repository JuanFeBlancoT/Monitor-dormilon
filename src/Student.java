import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Student implements Runnable{
	
	private Semaphore monitorAvailable;
	private Semaphore chairs;
	private int id;
	
	public Student(int idS, Semaphore monitorAvailableS, Semaphore chairsS) {
        id = idS;
        monitorAvailable = monitorAvailableS;
        chairs = chairsS;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				
				// Student programing
                System.out.println("Student " + id + " is programming.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000)); //Simulating programing with a sleep thread
                
                // Student aks for help
                System.out.println("Student with id: " + id + ", is going to ask for help.");
                
                // Student tries to acquire a chair
                if (chairs.tryAcquire()) {
                    System.out.println("Student " + id + " sat in a chair.");
                    
                    // Wakes up the monitor if asleep
                    if (monitorAvailable.availablePermits() == 0) {
                        monitorAvailable.release();
                        System.out.println("Student " + id + " woke up the monitor.");
                    }

                    // Waits ti get help
                    //Takes the monitor for himslef/herslef
                    monitorAvailable.acquire();
                    //
                    System.out.println("Student with id:" + id + ", is being helped.");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // Simulation of wait
                    //Releases the Semaphore of the montior
                    monitorAvailable.release();
                    
                } else {
                    // No Chairs available
                    System.out.println("No chairs available. Student with id:" + id + ", goes back to programming depressed.");
                }
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
