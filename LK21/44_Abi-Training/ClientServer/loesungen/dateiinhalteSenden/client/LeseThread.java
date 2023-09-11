import java.io.*;

public class LeseThread extends Thread {

	private DateiinhalteSendenClient main;
	private InputStreamReader in;

	public LeseThread(DateiinhalteSendenClient main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		int zeichen = 0;
		try {
			while ((zeichen = in.read()) != -1) {
				if (zeichen != -1) {
					main.taAusgabe.append("" + (char) zeichen);
				}
			}
		} catch (Exception e) {
			// System.out.println("LeseThread Fehler: " + e.getMessage());
		}
		System.out.println("LeseThread beendet");
		main.tfVerbindungsstatus.setText("getrennt");
		main.taAusgabe.setText("");
		main.tfEingabe.setText("");
		main.btnVerbinden.setEnabled(true);
		main.btnTrennen.setEnabled(false);
		main.btnSenden.setEnabled(false);
		main.tfEingabe.setEditable(false);
		main.verbunden = false;
	}
}
