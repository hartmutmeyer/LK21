import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class BlumenAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	public Blume blume1;
	public Blume blume2;
	public Blume blume3;
	public Blume blume4;
	public Blume blume5;
	public Blume blume6;


	public BlumenAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		blume1 = new Blume(50, 300);
		blume2 = new Blume(150, 200);
		blume3 = new Blume(250, 100);
		blume4 = new Blume(350, 230);
		blume5 = new Blume(450, 300);
		blume6 = new Blume(550, 400);
		Timer timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		blume1.zeichnen(g);
		blume2.zeichnen(g);
		blume3.zeichnen(g);
		blume4.zeichnen(g);
		blume5.zeichnen(g);
		blume6.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlumenAnwendung anwendung = new BlumenAnwendung("BlumenAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}