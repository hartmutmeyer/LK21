public class PotatoTimer extends Thread {
	private Potato potato;

	public PotatoTimer(Potato p) {
		this.potato = p;
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// nichts
		}
		potato.timeout();
	}
}
