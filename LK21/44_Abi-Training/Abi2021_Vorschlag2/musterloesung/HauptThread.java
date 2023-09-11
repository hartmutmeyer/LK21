package lk18.vorschlag2.musterloesung;

import java.net.ServerSocket;
import java.net.Socket;

public class HauptThread extends Thread {

	private SensordatenServer main;
	
	public HauptThread(SensordatenServer main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(11111)) {
			while (true)  {
				Socket clientSocket = serverSocket.accept();
				ClientThread cl = new ClientThread(main, clientSocket);
				cl.start();
			}
		} catch (Exception e) {
			System.out.println("Fehler im Hauptthread: " + e.getMessage());
		}
	}
}
