package tmpQ1;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class LeseThread extends Thread {
	
	private InputStreamReader in;
	private JTextArea textArea;

	public LeseThread(InputStreamReader in, JTextArea textArea) {
		this.in = in;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		int zeichen;
		char c;
		try {
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case '#':
					ergebnisAnzeigen();
					break;
				case '%':
					meldung();
					break;
				default:
					System.out.println("DAS SOLLTE NICHT PASSIEREN: " + c);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ergebnisAnzeigen() throws IOException {
		int zeichen;
		char c;
		while ((zeichen = in.read()) != '$') {
			c = (char) zeichen;
			textArea.append("" + c);
		}
	}
	
	private void meldung() throws IOException {
		int zeichen;
		char c;
		String meldung = "";
		while ((zeichen = in.read()) != '$') {
			c = (char) zeichen;
			meldung += c;
		}	
		JOptionPane.showMessageDialog(null, meldung);
	}
}
