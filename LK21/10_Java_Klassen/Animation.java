import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Animation extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int zaehler = 0;

	public Animation(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Timer timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		zaehler++;
		g.drawLine(0, 0, zaehler, zaehler);
		g.drawLine(WIDTH, HEIGHT, WIDTH-zaehler, HEIGHT-zaehler);
		g.drawLine(0, HEIGHT, zaehler, HEIGHT-zaehler);
		g.drawLine(WIDTH, 0, WIDTH-zaehler, zaehler);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animation anwendung = new Animation("Animation");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}