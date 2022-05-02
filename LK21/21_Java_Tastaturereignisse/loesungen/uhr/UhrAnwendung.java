import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class UhrAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private StoppUhr uhr; 
	private Timer timer;
	
	public UhrAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		uhr = new StoppUhr(100, 100);
		addKeyListener(uhr);
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		uhr.sekunde();
		uhr.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UhrAnwendung anwendung = new UhrAnwendung("Stoppuhr");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}