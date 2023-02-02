package java_27_Threads.doubleBuffering;
import javax.swing.JFrame;

public class MyTimer extends Thread {
	private int repaintMillisec;
	private JFrame frame;
	private boolean threadBeendet = false;

	MyTimer(int millisec, JFrame app) {
		repaintMillisec = millisec;
		frame = app;
	}

	public void beenden() {
		threadBeendet = true;
	}
	
	@Override
	public void run() {
		while (!threadBeendet) {
			try {
				frame.repaint();
				Thread.sleep(repaintMillisec);
			}
			catch (Exception e) {
			}
		}
	}
}