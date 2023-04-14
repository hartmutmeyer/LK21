import java.io.InputStreamReader;

public class LeseThread extends Thread {

	Client main;
	InputStreamReader in;

	public LeseThread(Client main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		int zeichen;
		try {
			while ((zeichen = in.read()) != -1) {
				// Hier werden die Daten vom Server gelesen und verarbeitet
				// (InputStream)
				// Was hier tatsächlich zu tun ist wird durch das Protokoll
				// definiert

				// Das ist eure eigentliche Arbeit!

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			main.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
