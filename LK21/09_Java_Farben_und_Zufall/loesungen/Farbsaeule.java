package farbenUndZufall;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Farbsaeule extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;

	public Farbsaeule(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		int rot = 50;
		for (int y = 0; y < 8; y++) {
			Color c = new Color(rot, 0, 0);
			rot += 25;
			g.setColor(c);
			g.fillRect(200, y * 50 + 70, 100, 50);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Farbsaeule anwendung = new Farbsaeule("Farbsaeule");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
