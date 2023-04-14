import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WarteThread extends Thread {
	EchoServer main;

	public WarteThread(EchoServer main) {
		this.main = main;
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			main.tfStatus.setText("Warte auf Verbindung");
			while (true) {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(main, socket);
				clientThread.start();
				synchronized (main) {
					main.anzahlVerbindungen++;
					main.tfStatus.setText("AnzahlVerbindungen: " + main.anzahlVerbindungen);
				}
			}
		} catch (Exception e) {
			main.tfStatus.setText("Server Fehler: " + e.getMessage());
		}
	}
}
