import java.awt.Color;

import javax.swing.JTextField;

public class ZaehlThread extends Thread {
	JTextField tfCountdown;
	int zaehler = 10;

	public ZaehlThread(JTextField tf) {
		tfCountdown = tf;
	}

	public void run() {
		tfCountdown.setText("" + zaehler);
		while (zaehler > 0) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			zaehler--;
			tfCountdown.setText("" + zaehler);
		}
		CountDown.contentPane.setBackground(Color.RED);
	}
}