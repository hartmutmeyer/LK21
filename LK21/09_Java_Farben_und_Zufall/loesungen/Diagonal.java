package farbenUndZufall;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Diagonal extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 520;
	private static final int HEIGHT = 520;  // a)
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;
	private Random rnd = new Random();
	
	public Diagonal(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		// b) grüne Diagonale c) Zufallsfarben
		for (int i = 0; i < 10; i++) {
			switch (rnd.nextInt(3)) {
			case 0:
				g.setColor(Color.GREEN);
				break;
			case 1:
				g.setColor(Color.YELLOW);
				break;
			case 2:
				g.setColor(Color.RED);
				break;
			}
			g.fillRect(i * 50 + 10, (9 - i) * 50 + 10, 50, 50);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Diagonal anwendung = new Diagonal("Diagonal");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
