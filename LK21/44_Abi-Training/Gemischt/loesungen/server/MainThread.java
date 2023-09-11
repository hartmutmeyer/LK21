import java.net.*;

class MainThread extends Thread {

	public MainThread() {
	}

	@Override
	public void run() {
		try (ServerSocket main = new ServerSocket(44444)) {
			System.out.println("Mainsocket angelegt");
			while (true) {
				Socket sock = main.accept();
				ClientThread thread = new ClientThread(sock);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
