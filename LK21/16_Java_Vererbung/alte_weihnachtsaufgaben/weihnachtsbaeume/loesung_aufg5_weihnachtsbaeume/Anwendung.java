import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Anwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	private static final Color BACKGROUND = new Color(255, 215, 180);
	private static final Color FOREGROUND = Color.BLACK;
	private Baum b1, b2, b3, b4, b5;
	
	public Anwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		b1 = new KugelBaum(30, 130);
		b2 = new Baum(200, 110);
		b3 = new SternBaum(300, 150);
		b4 = new KugelBaum(450, 130);
		b5 = new Baum(580, 90);
		Timer timer = new Timer(300, this);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		b2.zeichnen(g);
		b1.zeichnen(g);
		b5.zeichnen(g);
		b4.zeichnen(g);
		b3.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anwendung anwendung = new Anwendung("Anwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}