import java.net.*;

class MainThread extends Thread {

	@Override
	public void run() {
		try (ServerSocket main = new ServerSocket(33333)) {
			System.out.println("Mainsocket angelegt");
			while (true) {
				Socket sock = main.accept();
				ClientThread thread = new ClientThread(sock);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
