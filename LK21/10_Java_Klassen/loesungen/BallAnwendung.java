import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class BallAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;
	private Ball b1, b2, b3, b4, b5;
	private Ball b6, b7, b8, b9, b10;
	
	public BallAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		b1 = new Ball(Color.RED);
		b2 = new Ball(Color.BLUE);
		b3 = new Ball(Color.RED);
		b4 = new Ball(Color.CYAN);
		b5 = new Ball(Color.RED);
		b6 = new Ball(Color.ORANGE);
		b7 = new Ball(Color.RED);
		b8 = new Ball(Color.YELLOW);
		Timer timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		b1.zeichnen(g);
		b2.zeichnen(g);
		b3.zeichnen(g);
		b4.zeichnen(g);
		b5.zeichnen(g);
		b6.zeichnen(g);
		b7.zeichnen(g);
		b8.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallAnwendung anwendung = new BallAnwendung("BallAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}