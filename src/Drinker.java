/**
 * Represents the Drinker Thread
 * @author pragyabajoria
 *
 */
public class Drinker implements Runnable {
	
	/* Unique resource number to identify type of resource (0 is water, 1 is ice, and 2 is cup) */
	protected int res;
	
	/* The time for the thread to sleep. */
	private static final int WAIT_TIME = 50;
	
	/**
	 * Constructor to initialize the Drinker thread.
	 * @param rNum the resource needed by the thread (0 is water, 1 is ice, and 2 is cup)
	 */
	public Drinker(int rNum) {
		res = rNum;
	}
	
	/**
	 * Run method for the drinker thread
	 */
	public void run() {
		// Sleeps initially until all threads are created
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				System.out.println("Drinker " + res + " TRYING to enter Critical Section");
				// Acquires the needed resource
				ThirstyPersons.semaphores[res].acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Drinker " + res + " ENTERED the Critical Section");
			// The drinkers drinks
			drink(); // Critical section
			
			System.out.println("Signalling the Server from Drinker " + res);
			// Release the server
			ThirstyPersons.server.release();
		}
	}

	/**
	 * Represents the drinker with a particular resource drinking.
	 */
	public void drink() {
		System.out.println("Drinker with " + res + " is drinking");
	}
}