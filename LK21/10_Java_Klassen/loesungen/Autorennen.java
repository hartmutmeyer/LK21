import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Autorennen extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Auto a1 = new Auto(250, Color.GREEN);
	private Auto a2 = new Auto(50, Color.RED);
	private Auto a3 = new Auto(150, Color.CYAN);
	private Auto a4 = new Auto(350, Color.MAGENTA);
	
	public Autorennen(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Timer timer = new Timer(100, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		a1.zeichnen(g);
		a2.zeichnen(g);
		a3.zeichnen(g);
		a4.zeichnen(g);
		if (Auto.sieger != 0) {
			g.setColor(Color.BLACK);
			g.drawString("Der Sieger ist Auto " + Auto.sieger, 300, 480);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autorennen anwendung = new Autorennen("Autorennen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}