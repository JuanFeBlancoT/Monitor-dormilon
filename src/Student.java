import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Student implements Runnable{
	
	private Semaphore monitorSleeping;
	private Semaphore chairs;
	private Semaphore monitorBusy;
	private int id;
	
	public Student(int idS, Semaphore monitorAvailableS, Semaphore chairsS, Semaphore MonitorBusyS) {
        id = idS;
        monitorSleeping = monitorAvailableS;
        chairs = chairsS;
        monitorBusy = MonitorBusyS;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				
				// Student programming
                System.out.println("Student " + id + " is programming.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000)); //Simulating programming with a sleep thread              
                
                // Student tries to acquire a chair
                if (chairs.tryAcquire()) {
                    System.out.println("Student with id: " + id + ", is waiting for help.");
                    monitorSleeping.release();

                 // Wait if busy
                    monitorBusy.acquire();
                    System.out.println("Student with id: " + id + ", is receiving help.");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000)); // help time
                    monitorBusy.release();
                    chairs.release();
                } else {
                    System.out.println("Student with id: " + id + ", sees no chairs available, going to the computer lab.");
                    Thread.sleep(2000);
                }   
                
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
