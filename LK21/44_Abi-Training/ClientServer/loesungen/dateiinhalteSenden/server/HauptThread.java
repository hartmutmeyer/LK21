import java.net.*;

public class HauptThread extends Thread {

	@Override
	public void run() {
		try (ServerSocket sock = new ServerSocket(6666)) {
			while (true) {
				Socket clientSock = sock.accept();
				ClientThread thread = new ClientThread(clientSock);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
