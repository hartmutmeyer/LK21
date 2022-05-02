import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import hilfe.*;

public class Zahlenliste1mit2DArray extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 450;
	private static final int HEIGHT = 450;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int[][] z = new int[10][10];
	
	public Zahlenliste1mit2DArray(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				z[x][y] = 10 * y + x + 100;
			}
		}
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < z.length; x++) {
			for (int y = 0; y < z[0].length; y++) {
				g.drawString("" + z[x][y], x * 40 + 30, y * 40 + 50);
			}
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Zahlenliste1mit2DArray anwendung = new Zahlenliste1mit2DArray("Zahlenliste1 (2D)");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}