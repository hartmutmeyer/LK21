package lk18.vorschlag2.client;

import java.io.InputStreamReader;

public class LeseThread extends Thread {

	private SensorStation main;
	private InputStreamReader in;

	public LeseThread(SensorStation main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		boolean protokollfehlerErkannt = false;
		char c;
		int zeichen = 0;
		String text = "";
		try {
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case 'R':
					if ((zeichen = in.read()) != -1) {
						c = (char) zeichen;
						if (c == '!') {
							main.cbFunkverbindungSimulieren.setSelected(false);
							main.unzuverlaessigeVerbindung = false;
							main.add2log("RESET Befehl vom Server empfangen!");
							text = "";
						} else {
							text += c;
							protokollfehlerErkannt = true;
						}
					}
					break;
				default:
					text += c;
					protokollfehlerErkannt = true;
				}
				if (protokollfehlerErkannt) {
					main.add2log("Empfangen: " + text + "    --> PROTOKOLLFEHLER");
				}
			}
		} catch (Exception e) {
			// System.out.println("ClientLeseThread Fehler: " + e.getMessage());
		}
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					main.tfVerbindungsStatus.setText("getrennt");
//					main.tfZahlRaten.setEditable(false);
//					main.btnRaten.setEnabled(false);
//					main.btnVerbindenTrennen.setText("verbinden");		
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}
