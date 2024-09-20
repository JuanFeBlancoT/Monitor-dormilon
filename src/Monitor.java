import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Monitor implements Runnable{

	private Semaphore monitorSleeping;  
    private Semaphore monitorBusy;       
    private Semaphore chairs;             

    public Monitor(Semaphore monitorSleepingM, Semaphore monitorBusyM, Semaphore chairsM) {
        monitorSleeping = monitorSleepingM;
        monitorBusy = monitorBusyM;
        chairs = chairsM;
    }
    
	@Override
    public void run() {
        try {
            while (true) {
                // Espera a ser despertado
            	System.out.println("Monitor is sleeping");
                monitorSleeping.acquire();

                // Ahora que est� despierto, revisa si est� ocupado
                while (true) {
                    if (monitorBusy.tryAcquire()) {
                        // Ayudar a un estudiante
                        System.out.println("Monitor is helping a student");
                        Thread.sleep(2000); // Simulaci�n de tiempo de ayuda
                        System.out.println("The monitor has finished helping a student.");
                        monitorBusy.release(); // El monitor ya no est� ocupado
                        chairs.release(); // Libera una silla
                        break; // Termina el ciclo de ayuda
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
