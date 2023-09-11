import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClientLeseThread extends Thread {

	BuchladenClient main;
	InputStream in;
	private String[] buecher;

	public ClientLeseThread(BuchladenClient main, InputStream in) {
		this.main = main;
		this.in = in;
	}

	public void run() {
		try {
			int zeichen;
			System.out.println("LeseThread gestartet");
			while ((zeichen = in.read()) != -1) {
				char c = (char) zeichen;
				System.out.println("Empfangen: " + c);
				switch (c) {
				case 'L':
					listbox();
					break;
				case 'J':
					JOptionPane.showMessageDialog(main,
							"Ihre bestellten Bücher werden in Kürze versandt.");
					break;
				case 'N':
					int anzahl = anzahlLesen();
					JOptionPane.showMessageDialog(main,
							"Ihre Bestellung kann leider nicht durchgeführt werden.\n"
									+ "Es sind nur noch " + anzahl
									+ " Bücher vorhanden.");
					break;
				case 'S':
					JOptionPane.showMessageDialog(main,
							"Stornierung erfolgreich durchgeführt.");
					break;
				case 'F':
					JOptionPane
							.showMessageDialog(main,
									"Es ist keine Bestellung des markierten Buches vorhanden.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Fehler im LeseThread: " + e.getMessage());
		}
		System.out.println("LeseThread beendet");
		main.btnVerbinden.setEnabled(true);
		main.btnBestellen.setEnabled(false);
		main.btnStornieren.setEnabled(false);

	}

	int anzahlLesen() {
		int anzahl = 0;
		try {
			int zeichen;
			String text = "";
			while ((zeichen = in.read()) != '$') {
				text += (char) zeichen;
			}
			anzahl = Integer.parseInt(text);
		} catch (Exception e) {
			System.out.println("Fehler beim Lesen der anzahl: "
					+ e.getMessage());
		}
		return anzahl;
	}

	void listbox() throws Exception {
		int zeichen;
		int i = 0;
		String buch = "";
		buecher = new String[100];
		main.buecher.clear();
		while ((zeichen = in.read()) != '$') {
			char c = (char) zeichen;
			if (c == '§') {
				buecher[i++] = buch;
				buch = "";
			} else {
				buch += c;
			}
		}
		if (buch.length() > 0) {
			buecher[i] = buch;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (String book: buecher) {
						main.buecher.addElement(book);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
