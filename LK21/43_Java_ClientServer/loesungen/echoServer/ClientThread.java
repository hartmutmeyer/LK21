import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

// Thread für eine Client-Verbindung


public class ClientThread extends Thread {
	EchoServer main;
	Socket sock;

	public ClientThread(EchoServer main, Socket sock) {
		this.main = main;
		this.sock = sock;
	}

	public void run() {
		try {
			int zeichen;
			InputStreamReader in = new InputStreamReader(sock.getInputStream(), "UTF-8");
			OutputStreamWriter out = new OutputStreamWriter(sock.getOutputStream(), "UTF-8");
			while ((zeichen = in.read()) != -1) {
				out.write(zeichen);
				out.flush();
				System.out.println("echo: " + (char) zeichen);
			}
			in.close();
			out.close();
			sock.close();
			synchronized (main) {
				main.anzahlVerbindungen--;
				main.tfStatus.setText("AnzahlVerbindungen: " + main.anzahlVerbindungen);
			}
		} catch (Exception e) {
			synchronized (main) {
				main.anzahlVerbindungen--;
				main.tfStatus.setText("AnzahlVerbindungen: " + main.anzahlVerbindungen);
			}
		}
	}
}
