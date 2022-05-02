import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Klausur2D extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Kugel[][] kugel2D = new Kugel[10][10];

	public Klausur2D(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Random zufall = new Random();
		for (int x = 0; x < kugel2D.length; x++) {
			for (int y = 0; y < kugel2d[0].length; y++) {
				int xPos = zufall.nextInt(WIDTH);
				int yPos = zufall.nextInt(HEIGHT);
				kugel2D[x][y] = new Kugel(Color.RED, xPos, yPos, 0, 0);
			}
		}

	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < kugel2D.length; x++) {
			for (int y = 0; y < kugel2D[0].length; y++) {
				kugel2D[x][y].zeichnen(g);
			}
		}

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Klausur2D anwendung = new Klausur2D("Arrays");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}