import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import hilfe.*;

public class Zahlenliste1 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 450;
	private static final int HEIGHT = 450;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int[] z = new int[100];
	
	public Zahlenliste1(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int i = 0; i < z.length; i++) {
			z[i] = 100 + i;
		}
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		int x, y;
		for (int i = 0; i < z.length; i++) {
			x = i % 10;
			y = i / 10;
			g.drawString("" + z[i], x * 40 + 30, y * 40 + 50);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Zahlenliste1 anwendung = new Zahlenliste1("Zahlenliste1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}