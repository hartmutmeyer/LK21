public class PferdTimer extends Thread {
	Pferd main;

	public PferdTimer(Pferd main) {
		this.main = main;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// nichts
			}
			main.repaint();
		}
	}
}
