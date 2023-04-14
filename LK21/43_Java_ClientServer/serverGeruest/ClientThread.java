// Thread für eine Client-Verbindung
import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
	Server main;
	Socket socket;

	public ClientThread(Server main, Socket sock) {
		this.main = main;
		this.socket = sock;
	}

	@Override
	public void run() {
		try {
			int zeichen;
			InputStreamReader in = new InputStreamReader(socket.getInputStream(), "UTF-8");
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			while ((zeichen = in.read()) != -1) {
				// Hier werden die Daten vom Client gelesen und verarbeitet
				// (InputStream)
				// und Daten zum Client geschickt (OutputStream)
				// Was hier tatsächlich zu tun ist wird durch das Protokoll
				// definiert

				// Das ist eure eigentliche Arbeit!

			}
			// Die Verbindung (InputStream des Servers bzw. OutputStream des
			// Clients wurde vom Client getrennt --> in.read() == -1
			// und damit die while-Schleife oben verlassen.
			// Jetzt muss nur noch aufgeräumt werden (der Socket - und indirekt
			// damit auch die Streams - werden geschlossen).
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
