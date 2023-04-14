import java.io.InputStreamReader;
import java.net.SocketException;

public class LeseThread extends Thread {

	EchoClient main;
	InputStreamReader in;

	public LeseThread(EchoClient main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		int zeichen;
		try {
			while ((zeichen = in.read()) != -1) {
				System.out.println("Received: " + (char) zeichen);
				main.textAreaAusgabe.append("" + (char) zeichen);
			}

		} catch (SocketException e) {
			System.out.println("Client Lesethread: Verbindung wurde getrennt ...");
		} catch (Exception e) {
			e.printStackTrace();		
		}
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("Client LeseThread: Fehler beim Close: "
					+ e.getMessage());
		}
		System.out.println("Client LeseThread beendet.");
		main.btnTrennen.setEnabled(false);
		main.btnSenden.setEnabled(false);
		main.btnVerbinden.setEnabled(true);
	}
}
