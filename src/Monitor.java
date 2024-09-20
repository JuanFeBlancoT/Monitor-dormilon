import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Monitor implements Runnable{

	private Semaphore monitorAvailable;
    private Semaphore chairs;
    private boolean isSleeping = true;

    public Monitor(Semaphore monitorAvailableM, Semaphore chairsM) {
        monitorAvailable = monitorAvailableM;
        chairs = chairsM;
    }
    
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            while (true) {
            	
                // If there aren´t any students asking for help: He sleeps
                if (chairs.availablePermits() == 3) {
                    System.out.println("The monitor is sleeping");
                    isSleeping = true;
                    monitorAvailable.acquire();
                }

                // Monitor wakes up and helps someone
                isSleeping = false;
                System.out.println("Monitor is helping a student");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000)); // Wait simulation

                // Release a chair when the monitor is done with the student
                chairs.release();
                System.out.println("El monitor ha terminado de ayudar a un estudiante.");
                
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public boolean isSleeping() {
        return isSleeping;
    }
	
}
