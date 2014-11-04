/**
 * Represents the Server thread
 * @author pragyabajoria
 *
 */
public class Server extends ThirstyPersons implements Runnable {
	
	/* The time for the thread to sleep. */
	private static final int WAIT_TIME = 50;
	
	/**
	 * Run method for the Server.
	 */
	public void run() {
		// Sleeps initially until all threads are created.
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		int i, j, k;
		while (true) {
			
			i = (int) (Math.floor(Math.random() * 3)); // returns a random integer 0, 1 or 2
			j = (int) (Math.floor(Math.random() * 3)); // returns a random integer 0, 1 or 2
			
			if (i != j) { // If i and j are different, they can be served
				try {	
					// Acquire the server, and wait till all the threads finish drinking
					System.out.println("Server TRYING to enter Critical Section"); 
					ThirstyPersons.server.acquire();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Server ENTERED the Critical Section");
				// The drinker with the k-th ingredient is identified
				k = 3 - (i + j); 
				System.out.println("Drinker " + k + " selected and signaled by the server");
				// Release the drinker waiting on the resources that have been served.
				ThirstyPersons.semaphores[k].release();
			}
		} // end of while
	} // end of Server
}