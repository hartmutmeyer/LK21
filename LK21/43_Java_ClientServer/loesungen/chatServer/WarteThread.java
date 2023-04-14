import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class WarteThread extends Thread {
	ChatServer main;
	List<OutputStreamWriter> outArray = new ArrayList<OutputStreamWriter>();

	public WarteThread(ChatServer main) {
		this.main = main;
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(13131)) {
			while (true) {
				Socket socket = serverSocket.accept();
				InputStreamReader in = new InputStreamReader(socket.getInputStream(), "UTF-8");
				OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				outArray.add(out);
				System.out.println("Neuer Client" + out);
				System.out.println("Size" + outArray.size());
				ClientThread client = new ClientThread(main, in);
				client.start();
				main.tfAnzahlClients.setText("" + outArray.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
