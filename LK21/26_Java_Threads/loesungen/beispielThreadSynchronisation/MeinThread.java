public class MeinThread extends Thread {
	int index;
	static Object monitor = new Object();

	public MeinThread(int index) {
		this.index = index;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			synchronized (monitor) {
				Synchronisation.zaehler++;
				int help = Synchronisation.zaehler;
				// Simulation einer aufwändigen Berechnung:
				for (long x = 0; x < 1000000; x++) {
					Math.sin(x);
				}
				System.out.println("Thread" + index + ": " + help);
			}
		}
	}
}
