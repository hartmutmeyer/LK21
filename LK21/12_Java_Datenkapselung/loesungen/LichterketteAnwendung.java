import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class LichterketteAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 600;
	private static final int HEIGHT = 350;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	Lichterkette vonREAL = new Lichterkette(50, 20);
	Lichterkette vonPenny = new Lichterkette(20, 200);
	Timer Zeit = new Timer(1000, this);

	public LichterketteAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Zeit.start();
		vonREAL.zustand(2);

	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		vonREAL.zeichnen(g);
		vonPenny.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LichterketteAnwendung anwendung = new LichterketteAnwendung(
							"LichterketteAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}