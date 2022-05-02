import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Zahlenliste2 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 450;
	private static final int HEIGHT = 140;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int[] z = new int[10];
	private Random zufall = new Random();
	
	public Zahlenliste2(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int i = 0; i < z.length; i++) {
			z[i] = zufall.nextInt(100) + 1;
		}
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < z.length; x++) {
			g.drawString("" + z[x], x * 40 + 30, 30);
		}

		// Summe
		int sum = 0;
		for (int x = 0; x < z.length; x++) {
			sum = sum + z[x];
		}
		g.drawString("Summe:  " + sum, 30, 60);

		// kleinste Zahl
		int min = z[0];
		for (int x = 1; x < z.length; x++) {
			if (z[x] < min) {
				min = z[x];
			}
		}
		g.drawString("Kleinste Zahl: " + min, 30, 90);

		// größte Zahl
		int max = z[0];
		for (int x = 1; x < z.length; x++) {
			if (z[x] > max) {
				max = z[x];
			}
		}
		g.drawString("Größte Zahl: " + max, 30, 120);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Zahlenliste2 anwendung = new Zahlenliste2("Zahlenliste2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}