package lk18.vorschlag2.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class SensordatenSendeThread extends Thread {
	
	private SensorStation main;
	private OutputStreamWriter out;
	private double tBase;
	private double t;
	private String temp;
	private int pressureBase;
	private int pressure;
	private int checksum;
	private Random zufall;

	public SensordatenSendeThread(SensorStation main, OutputStreamWriter out) {
		this.main = main;
		this.out = out;
		anfangsMesswerteErzeugen();
	}
	
	@Override
	public void run() {
		String daten = "";
		String datenMitFehlern = "";
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			t = tBase -1.0 + 2.0 * zufall.nextDouble();
			temp = String.format("%.1f", t);
			pressure  = pressureBase - 3 + zufall.nextInt(6); 
			daten = "<" + main.id + "#" + temp + "#" + pressure + ">";
			checksum = pruefsummeBerechnen(daten);
			daten += checksum + "?";
			if (main.unzuverlaessigeVerbindung) {
				datenMitFehlern = "";
				for (int i = 0; i < daten.length(); i++) {
					if (zufall.nextDouble() > 0.05) {  // 5% der Zeichen gehen verloren
						datenMitFehlern += daten.charAt(i);
					}
				}
			}
			try {
				if (main.unzuverlaessigeVerbindung) {
					out.write(datenMitFehlern);
					out.flush();
					if (daten.equals(datenMitFehlern)) {
						main.add2log("Gesendet: " + daten + " **");
					} else {
						main.add2log("Gesendet: " + daten + " ** !! **");
					}
				} else {
					out.write(daten);
					out.flush();
					main.add2log("Gesendet: " + daten);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void anfangsMesswerteErzeugen() {
		zufall = new Random();
		tBase = 10.0 + 10.0 * zufall.nextDouble();
		pressureBase = 1000 + zufall.nextInt(20);
	}

	private int pruefsummeBerechnen(String daten) {
		int checksum = 0;
		for (int i = 0; i < daten.length(); i++) {
			checksum += daten.charAt(i);
		}
		return checksum;
	}

}
