import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class RaupeAnw extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	Raupe nimmersatt, immersatt, satt, gestopft;
	Timer timer = new Timer(200, this);
	
	public RaupeAnw(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		timer.start();
		nimmersatt = new Raupe(100, 100, Color.GREEN, 10);
		immersatt = new Raupe(100, 200, Color.BLUE, 5);
		satt = new Raupe(100, 300, Color.RED, 2);
		gestopft = new Raupe(100, 400, Color.GRAY, 0);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		nimmersatt.zeichnen(g);
		nimmersatt.bewegen();
		immersatt.zeichnen(g);
		immersatt.bewegen();
		satt.zeichnen(g);
		satt.bewegen();
		gestopft.zeichnen(g);
		gestopft.bewegen();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaupeAnw anwendung = new RaupeAnw("RaupeAnw");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}