import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Flotte extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	private static final Color BACKGROUND = Color.BLUE;
	private static final Color FOREGROUND = Color.BLACK;
	private Schiff f1, f2, f3;
	
	public Flotte(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		f1 = new Schiff(100, 100, this);
		f2 = new Schiff(250, 300, this);
		f3 = new Schiff(150, 500, this);
		Timer timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		f1.zeichnen(g);
		f2.zeichnen(g);
		f3.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flotte anwendung = new Flotte("Flotte");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}