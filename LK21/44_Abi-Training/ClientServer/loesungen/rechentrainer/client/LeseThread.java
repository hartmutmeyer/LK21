import javax.swing.*;
import java.net.*;
import java.io.*;

public class LeseThread extends Thread {
	Socket socket;
	InputStreamReader in;
	RechentrainerClient main;

	public LeseThread(RechentrainerClient main, Socket socket) {
		this.main = main;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			in = new InputStreamReader(socket.getInputStream(), "UTF-8");
			int zeichen;
			char c;
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case '?':
					frage();
					break;
				case '%':
					meldung();
					break;
				default:
					JOptionPane.showMessageDialog(main, "Protokoll-Fehler - empfangen: " + c);
				}
			}
		} catch (Exception e) {
			System.out.println("Exeption im LeseThread: " + e.getMessage());
			e.printStackTrace();
		}
		main.btnStarten.setEnabled(true);
		main.btnLoesungSenden.setEnabled(false);

	}

	void frage() throws IOException {
		int zeichen;
		String text = "";
		while ((zeichen = in.read()) != '$') {
			text += (char) zeichen;
		}
		main.tfAufgabe.setText(text);
		main.tfLoesung.setText("");
	}

	void meldung() throws IOException {
		int zeichen;
		String text = "";
		while ((zeichen = in.read()) != '$') {
			text += (char) zeichen;
		}
		JOptionPane.showMessageDialog(main, text);
	}
}
