public class MeinThread extends Thread {
	int threadNummer;

	public MeinThread(int threadNummer) {
		this.threadNummer = threadNummer;
	}

	@Overrride
	public void run() {
		for (int i = 0; i < 100; i++) {
			Synchronisation.zaehler++;
			int help = Synchronisation.zaehler;
			// Simulation einer aufwändigen Berechnung:
			for (long x = 0; x < 1000000; x++) {
				Math.sin(x);
			}
			System.out.println("Thread" + threadNummer + ": " + help);
		}
	}
}
