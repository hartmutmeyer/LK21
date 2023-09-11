package lk18.vorschlag2.musterloesung;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Manages the communication with the Client.
 * 
 * @author hartmut meyer
 */

class ClientThread extends Thread {
	private SensordatenServer main;
	private Socket socket;
	private InputStreamReader in;
	private OutputStreamWriter out;
	private String stationskennung = "";
	private boolean stationErkannt = false;

	public ClientThread(SensordatenServer main, Socket socket) {
		this.main = main;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			in = new InputStreamReader(socket.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			String text = "";
			int zeichen;
			char c;
			String berechnetePruefsumme = "";
			int checksumErrorCounter = 0;
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				text += c;
				if (!stationErkannt && c == '#') {
					stationskennung = text.substring(1, text.length() - 1);
					stationErkannt = true;
					main.add2log("Neue Sensorstation erkannt: " + stationskennung);
				}
				if (c == '>') {
					berechnetePruefsumme = pruefsummeBerechnen(text);
				}
				if (c == '?') {
					if (istPruefsummeKorrekt(text, berechnetePruefsumme)) {
						main.add2log("EMPFANGEN: " + text);
						checksumErrorCounter = 0;
					} else {
						checksumErrorCounter++;
						main.add2log("Prüfsummen-Fehler für Sensorstation " + stationskennung + ": " + checksumErrorCounter);
						if (checksumErrorCounter >= 2) {
							out.write("R!");
							out.flush();
							main.add2log("RESET Befehl an Sensorstation " + stationskennung);
							checksumErrorCounter = 0;
						}
					}
					text = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fehler im ClientThread: " + e.getMessage());
		}
		try {
			socket.close();
			main.add2log("Client für Socket " + socket + " geschlossen");
		} catch (Exception e) {
			System.out.println("Fehler beim Schließen des Sockets: " + e.getMessage());
		}
	}
	
	private String pruefsummeBerechnen(String daten) {
		int checksum = 0;
		for (int i = 0; i < daten.length(); i++) {
			checksum += daten.charAt(i);
		}
		return "" + checksum;
	}

	private boolean istPruefsummeKorrekt(String text, String berechnetePruefsumme) {
		String empfangenePruefsumme = text.substring(text.indexOf('>') + 1, text.indexOf('?'));
		if (empfangenePruefsumme.equals(berechnetePruefsumme)) {
			return true;
		} else {
			return false;
		}
	}

}
