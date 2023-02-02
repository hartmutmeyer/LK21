public class EselTimer extends Thread {
	Esel main;

	public EselTimer(Esel main) {
		this.main = main;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// nichts
			}
			main.repaint();
		}
	}
}
