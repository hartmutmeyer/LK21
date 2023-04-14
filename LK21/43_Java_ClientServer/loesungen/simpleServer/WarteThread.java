import java.net.*;
import java.io.*;

public class WarteThread extends Thread {
	SimpleServer main;
	int anzahlVerbindungen = 0;

	public WarteThread(SimpleServer main) {
		this.main = main;
	}

	public void run() {
		try {
			ServerSocket serversocket = new ServerSocket(11111);
			main.tfStatus.setText("Warte auf Verbindung");
			while (true) {
				Socket socket = serversocket.accept();
				main.tfStatus.setText("Verbindung hergestellt");
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				anzahlVerbindungen++;
				String text = "Guten Tag! Dies ist meine " + anzahlVerbindungen + ". Verbindung.";
				out.write(text);
				out.flush();
				socket.close();
				main.tfStatus.setText("" + anzahlVerbindungen + ". Verbindung beendet. Warte erneut.");
			}
		} catch (Exception e) {
			main.tfStatus.setText("Fehler: " + e.getMessage());
		}
	}
}
