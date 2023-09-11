import java.net.*;
import java.io.*;
import java.util.*;

class ClientThread extends Thread {
	private Socket sock;
	private String name = "";
	private Random rand = new Random();
	private String aufgabe;
	private int antwort;
	private int anzahlRichtigerAntworten = 0;
	private long anfangszeit;
	private static final int START = 0;
	private static final int NEUE_AUFGABE_BESTIMMEN = 1;
	private static final int AUFGABE_STELLEN = 2;
	private static final int AUF_ANTWORT_WARTEN = 3;
	private static final int LEISTUNG_BEWERTEN = 4;
	private static final int ENDE = 5;
	private int zustand;
	private static Object monitor = new Object();

	public ClientThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		zustand = START;
		try (InputStreamReader inNet = new InputStreamReader(sock.getInputStream(), "UTF-8");
				OutputStreamWriter outNet = new OutputStreamWriter(sock.getOutputStream(), "UTF-8")) {

			while (zustand != ENDE) {
				switch (zustand) {
				case START:
					starten(inNet);
					break;
				case NEUE_AUFGABE_BESTIMMEN:
					neueAufgabeBestimmen();
					break;
				case AUFGABE_STELLEN:
					aufgabeStellen(outNet);
					break;
				case AUF_ANTWORT_WARTEN:
					antwortLesen(inNet, outNet);
					break;
				case LEISTUNG_BEWERTEN:
					leistungBewerten(outNet);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception im Client-Thread des Servers: " + e.getMessage());
			e.printStackTrace();
		}
	}

	void starten(InputStreamReader inNet) throws IOException {
		System.out.println("Client-Thread gestartet");
		int zeichen;
		while ((zeichen = inNet.read()) != '$') {
			name += (char) zeichen;
		}
		System.out.println("ClientThread: Name empfangen: " + name);
		anfangszeit = System.currentTimeMillis();
		zustand = NEUE_AUFGABE_BESTIMMEN;
	}

	void neueAufgabeBestimmen() {
		int zahl1 = rand.nextInt(8) + 2;
		int zahl2 = rand.nextInt(90) + 10;
		antwort = zahl1 * zahl2;
		aufgabe = "?" + zahl1 + " * " + zahl2 + "$";
		zustand = AUFGABE_STELLEN;
	}

	void aufgabeStellen(OutputStreamWriter outNet) throws IOException {
		outNet.write(aufgabe);
		outNet.flush();
		System.out.println("ClientThread: Aufgabe gestellt: " + aufgabe);
		zustand = AUF_ANTWORT_WARTEN;
	}

	void antwortLesen(InputStreamReader inNet, OutputStreamWriter outNet) throws IOException {
		int gelesen = -1;
		int zeichen;
		String text = "";
		while ((zeichen = inNet.read()) != '$') {
			text += (char) zeichen;
		}
		try {
			gelesen = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (gelesen == antwort) {
			anzahlRichtigerAntworten++;
			if (anzahlRichtigerAntworten == 5) {
				zustand = LEISTUNG_BEWERTEN;
			} else {
				zustand = NEUE_AUFGABE_BESTIMMEN;
			}
		} else {
			System.out.println("gelesen: " + gelesen);
			System.out.println("antwort: " + antwort);
			String zurueck = "%Falsche Antwort. Probier es noch einmal.$";
			outNet.write(zurueck);
			outNet.flush();
			zustand = AUFGABE_STELLEN;
		}
	}

	void leistungBewerten(OutputStreamWriter outNet) throws IOException {
		long endzeit = System.currentTimeMillis();
		double zeit = endzeit - anfangszeit;
		zeit = zeit / 1000;
		double bisherigeBestzeit = 0.0;
		String halterDerBisherigenBestzeit = "";
		String text = "";
		int zeichen;
		boolean highscoreFileExists = false;
				
		synchronized (monitor) {
			URL url = getClass().getResource("rekord.txt");
			if (url == null) {
				System.out.println("Fehler beim Schreiben: Datei rekord.txt existiert nicht.");
				return;
			}
			try (InputStream is = new FileInputStream(url.getFile());
					InputStreamReader inFile = new InputStreamReader(is, "UTF-8")) {
				while ((zeichen = inFile.read()) != '$') {
					text += (char) zeichen;
				}
				bisherigeBestzeit = Double.parseDouble(text);
				while ((zeichen = inFile.read()) != -1) {
					halterDerBisherigenBestzeit += (char) zeichen;
				}
				if (!text.isEmpty() && !halterDerBisherigenBestzeit.isEmpty()) {
					highscoreFileExists = true;
				}
			} catch (IOException e) {
				System.out.println("Fehler beim Lesen der Highscore-Datei:" + e.getMessage());
				e.printStackTrace();
			}

			if (zeit < bisherigeBestzeit) {
				bisherigeBestzeit = zeit;
				halterDerBisherigenBestzeit = name;
				text = "%Gratuliere " + name + "! Du hast nur " + zeit + " Sekunden gebraucht.\n";
				text += "Das ist die neue Bestzeit!$";
				try (OutputStream os = new FileOutputStream(url.getFile());
						OutputStreamWriter outFile = new OutputStreamWriter(os, "UTF-8")) {
					outFile.write(zeit + "$" + name);
					outFile.flush();
				} catch (IOException e) {
					System.out.println("Fehler beim Schreiben der Highscore-Datei:" + e.getMessage());
					e.printStackTrace();
				}
			} else {
				text = "%Du hast die Aufgaben in " + zeit + " Sekunden gelöst.\n";
				if (highscoreFileExists) {

					text += "Die aktuelle Bestzeit von " + halterDerBisherigenBestzeit + " beträgt " + bisherigeBestzeit
							+ " Sekunden.$";
				}
			}
		}
		System.out.println(text);
		outNet.write(text);
		outNet.flush();
		zustand = ENDE;
	}
}
