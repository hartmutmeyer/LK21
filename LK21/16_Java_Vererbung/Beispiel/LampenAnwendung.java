import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.Timer;

import hilfe.*;

public class LampenAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Random zufall = new Random();
	private Lampe lampe1, lampe4;
	private QuadratLampe lampe2;
	private RahmenLampe lampe3;
	
	public LampenAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		lampe1 = new Lampe(Color.BLUE, 100, 100);
		lampe2 = new QuadratLampe(Color.GREEN, 200, 100);
		lampe3 = new RahmenLampe(Color.YELLOW, 300, 100);
		switch (zufall.nextInt(3)) {
		case 0:
			lampe4 = new Lampe(Color.RED, 200, 200);
			break;
		case 1:
			lampe4 = new QuadratLampe(Color.RED, 200, 200);
			break;
		case 2:
			lampe4 = new RahmenLampe(Color.RED, 200, 200);
		}
		Timer timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		lampe1.schalten(!lampe1.getAn());
		lampe1.zeichnen(g);
		lampe2.schalten(!lampe2.getAn());
		lampe2.zeichnen(g);
		lampe3.schalten(!lampe3.getAn());
		lampe3.zeichnen(g);
		lampe4.schalten(!lampe4.getAn());
		lampe4.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LampenAnwendung anwendung = new LampenAnwendung("LampenAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}