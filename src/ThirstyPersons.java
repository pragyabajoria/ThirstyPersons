import java.util.concurrent.Semaphore;

/**
 * Represents the ThirstyPersons problem with three thirsty threads and one server thread.
 * 
 * @author Pragya Bajoria
 */
public class ThirstyPersons {
	
	/* Stores the three semaphores with 0 for water, 1 for ice, 2 for cup. */ 
	protected static Semaphore semaphores[] = new Semaphore[3];

	/* Static semaphore to allow multiple threads have to only one semaphore to control CS. */
	protected static Semaphore server = new Semaphore(1);
	
	/** 
	 * Allow threads to sleep to wait for all threads to be created.
	 */
	public static void sleep() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}
	
	/**
	 * Main method for the ThirstyPersons Problem.
	 */
	public static void main(String args[]) {
		// Initialize the semaphores
		for(int i = 0; i < 3; i++) {
	        semaphores[i] = new Semaphore(0);
	    }
		
		// Create the server thread and three drinker threads and start them
		Thread waterThread = new Thread(new Drinker(0));
		waterThread.start();
		sleep();
		
		Thread iceThread = new Thread(new Drinker(1));
		iceThread.start();
		sleep();
		
		Thread cupThread = new Thread(new Drinker(2));
		cupThread.start();
		sleep();
		
		Thread serverThread = new Thread(new Server());
		serverThread.start();
		sleep();
	}
}