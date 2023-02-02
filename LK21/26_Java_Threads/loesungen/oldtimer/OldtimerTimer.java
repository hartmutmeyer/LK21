import javax.swing.JFrame;

public class OldtimerTimer extends Thread {

	int millis;
	JFrame app;
	boolean anhalten = false;
	
	public OldtimerTimer(int millis, JFrame app) {
		this.millis = millis;
		this.app = app;
	}

	@Override
	public void run() {
		while (!anhalten) {
			try {
				Thread.sleep(millis);
				app.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void beenden() {
		anhalten = true;
	}

}
