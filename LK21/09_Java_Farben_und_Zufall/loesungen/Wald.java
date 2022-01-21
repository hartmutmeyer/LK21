package farbenUndZufall;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Wald extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 700;
	private static Color hellbraun = new Color(255, 220, 185);   // a)
	private static final Color BACKGROUND = hellbraun;
	private static final Color FOREGROUND = Color.BLACK;

	public Wald(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);

	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		baum(g, 100, 100);
		baum(g, 200, 50);
		baum(g, 20, 80);
		for (int x = 0; x < 5; x++) {
			baum(g, x * 60 + 400, 50);
		}
		int z = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 15; x++) {
				z++;
				if (z % 6 != 0) {
					baum(g, x * 40 + 100, 220 + y * 70);
				}
			}
		}
	}

	public void baum(Graphics g, int x, int y) {
		Color gruen = new Color(0, 128, 0);
		g.setColor(gruen);
		g.fillOval(x, y, 50, 50);
		Color braun = new Color(128, 64, 0);
		g.setColor(braun);
		g.fillRect(x + 20, y + 50, 10, 50);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wald anwendung = new Wald("Wald");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}