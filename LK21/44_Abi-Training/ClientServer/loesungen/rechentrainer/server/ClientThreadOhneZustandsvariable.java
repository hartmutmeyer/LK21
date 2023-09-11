import java.net.*;
import java.io.*;
import java.util.*;

class ClientThreadOhneZustandsvariable extends Thread {
	private Socket sock;
	private String name = "";
	private String halterDerBisherigenBestzeit;
	private double bisherigeBestzeit;
	private Random rand = new Random();
	private int antwort = 0;
	private static Object monitor = new Object();

	public ClientThreadOhneZustandsvariable(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try (InputStreamReader inNet = new InputStreamReader(sock.getInputStream(), "UTF-8");
			OutputStreamWriter outNet = new OutputStreamWriter(sock.getOutputStream(), "UTF-8")) {
			System.out.println("Client-Thread gestartet");
			int zeichen;
			while ((zeichen = inNet.read()) != '$') {
				name += (char) zeichen;
			}
			URL url = getClass().getResource("rekord.txt");
			if (url == null) {
				System.out.println("Fehler beim Lesen: Datei rekord.txt existiert nicht.");
				return;
			}
			try (InputStream is = new FileInputStream(url.getFile());
					InputStreamReader inFile = new InputStreamReader(is, "UTF-8")) {
				String text = "";
				while ((zeichen = inFile.read()) != '$') {
					text += (char) zeichen;
				}
				bisherigeBestzeit = Double.parseDouble(text);
				halterDerBisherigenBestzeit = "";
				while ((zeichen = inFile.read()) != -1) {
					halterDerBisherigenBestzeit += (char) zeichen;
				}
				System.out.println("Rekordhalter: " + halterDerBisherigenBestzeit + " Zeit: " + bisherigeBestzeit);
			} catch (Exception e) {
				System.out.println("Fehler beim Lesen der Datei:" + e.getMessage());
				e.printStackTrace();
			}
			long anfangszeit = System.currentTimeMillis();
			for (int i = 0; i < 5; i++) {
				aufgabeSenden(outNet);
				antwortLesen(inNet, outNet);
			}
			long endzeit = System.currentTimeMillis();
			double zeit = endzeit - anfangszeit;
			zeit = zeit / 1000;
			String text;
			if (zeit < bisherigeBestzeit) {
				bisherigeBestzeit = zeit;
				halterDerBisherigenBestzeit = name;
				text = "%Gratuliere " + name + "! Du hast nur " + zeit + " Sekunden gebraucht.\n";
				text += "Das ist die neue Bestzeit!$";

				synchronized (monitor) {
					try (OutputStream is = new FileOutputStream(url.getFile());
							OutputStreamWriter outFile = new OutputStreamWriter(is, "UTF-8")) {
						outFile.write("" + zeit + "$" + name);
						outFile.flush();
					} catch (IOException e) {
						System.out.println("Fehler beim Schreiben in die Datei:" + e.getMessage());
						e.printStackTrace();
					}
				}
			} else {
				text = "%Du hast die Aufgaben in " + zeit + " Sekunden gelöst.\n";
				text += "Die aktuelle Bestzeit von " + halterDerBisherigenBestzeit + " beträgt " + bisherigeBestzeit
						+ " Sekunden.$";
			}
			System.out.println(text);
			outNet.write(text);
			outNet.flush();
			sock.close();
		} catch (Exception e) {
			System.out.println("Exception vom Client erhalten: " + e.getMessage());
			e.printStackTrace();
		}
	}

	void aufgabeSenden(OutputStreamWriter outNet) throws IOException {
		int zahl1 = rand.nextInt(8) + 2;
		int zahl2 = rand.nextInt(90) + 10;
		antwort = zahl1 * zahl2;
		String text = "?" + zahl1 + " * " + zahl2 + "$";
		outNet.write(text);
		outNet.flush();
	}

	void antwortLesen(InputStreamReader inNet, OutputStreamWriter outNet) throws IOException {
		int gelesen = -1;
		int zeichen;
		while (gelesen != antwort) {
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
			if (gelesen != antwort) {
				System.out.println("gelesen: " + gelesen);
				System.out.println("antwort: " + antwort);
				String zurueck = "%Falsche Antwort. Probier es noch einmal.$";
				outNet.write(zurueck);
				outNet.flush();
			}
		}
	}
}
