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
		
	}

}
