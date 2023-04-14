import java.net.*;

public class HauptThread extends Thread {
	// Die Aufgabe des Hauptthreads ist es, auf eingehende Verbindungswünsche
	// von Clients zu warten und diese dann mit einem Client-Socket zu
	// "versorgen".
	// Sobald dies geschehen ist wird der Client-Thread gestartet (dieser
	// bekommt Client-Socket als Parameter übergeben).
	Server main;

	public HauptThread(Server main) {
		this.main = main;
		// Der Zugriff auf die Klasse des Anwendungsfensters über "main"
		// ist wichtig, wenn im Hauptthread oder im Clientthread auf Elemente
		// Des Benutzer-Interfaces (Textfelder etc.) des Anwendungsfensters
		// zugegriffen werden soll.
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(main, socket);
				client.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
