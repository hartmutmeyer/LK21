package tmpQ1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HauptThread extends Thread {

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				Socket s = serverSocket.accept();
				ClientThread ct = new ClientThread(s);
				ct.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
