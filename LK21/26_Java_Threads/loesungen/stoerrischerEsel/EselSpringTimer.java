public class EselSpringTimer extends Thread {
	Esel main;

	public EselSpringTimer(Esel main) {
		this.main = main;
	}

	public void run() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			// nichts
		}
		main.y = 190;
	}
}
