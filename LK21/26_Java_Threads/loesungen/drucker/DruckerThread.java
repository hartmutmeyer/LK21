import java.util.*;

public class DruckerThread extends Thread {

	private Drucker drucker;
	private String zuDruckenderText;
	private static Random zufall = new Random();
	private static Object monitor = new Object();

	public DruckerThread(Drucker main, int nr) {
		this.drucker = main;
		zuDruckenderText = "Thread " + nr + " druckt ...";
	}

	@Override
	public void run() {
		while (true) {
			synchronized (monitor) {
				// Drucken:
				for (int i = 0; i < zuDruckenderText.length(); i++) {
					drucker.tfDrucker.setText(drucker.tfDrucker.getText() + zuDruckenderText.charAt(i));
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// Fehlerfall ignorieren
					}
				}
				drucker.tfDrucker.setText("");
			}
			try {
				Thread.sleep((zufall.nextInt(10) + 1) * 150);
			} catch (Exception e) {
				// nothing
			}
		}
	}
}
