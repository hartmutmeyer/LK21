import java.io.*;

public class ClientThread extends Thread {
	ChatServer main;
	InputStreamReader in;
	OutputStreamWriter out;

	public ClientThread(ChatServer main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		System.out.println("Thread gestartet" + this);
		try {
			int zeichen;
			String text = "";
			while ((zeichen = in.read()) != -1) {
				System.out.println("Gelesen:" + (char) zeichen);
				if (zeichen == '\n') {
					text = text + (char) zeichen;
					System.out.print("Sende Text:" + text);
					for (int i = 0; i < main.thread.outArray.size(); i++) {
						out = main.thread.outArray.get(i);
						out.write(text);
						out.flush();
						System.out.println("Gesendet an:" + out);

					}
					text = "";
				} else {
					text = text + (char) zeichen;
				}
			}
		} catch (Exception e) {
		}
		try {
			boolean b = main.thread.outArray.remove(this.out);
			System.out.println("Remove:" + this.out + "Erfolg" + b);
			main.tfAnzahlClients.setText("" + main.thread.outArray.size());
			System.out.println("Thread beendet" + this);
		} catch (Exception e) {
			System.out.println("ABC" + e.getMessage());
		}
	}
}
