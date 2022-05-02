import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.Timer;

import hilfe.*;

public class Main extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;
	private Kette k1, k2, k3;
    private Random zufall = new Random();

	public Main(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		k1= new Kette (Color.RED, 180, 100, zufall);
        k2= new Kette (Color.BLUE, 50, 250, zufall);
        k3= new Kette (Color.GREEN, 300, 250, zufall);
        Timer timer = new Timer(500, this);
        timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		k1.zeichnen(g);
        k2.zeichnen(g);
        k3.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main anwendung = new Main("Main");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
