import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Aufg6 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.WHITE;
	private static final int ANZAHL_KREISE = 9;
	private static final int VERKLEINERN = 10;
	private static final int KLEINSTE_BREITE = 10;
	private static final int GROESSTE_BREITE = KLEINSTE_BREITE + ANZAHL_KREISE * VERKLEINERN;
	int breite;

	public Aufg6(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		
	}

	@Override
	public void myPaint(Graphics g) { // Aufgabe 6b
		kreise(g, 250, 200, Color.PINK);
		kreise(g, 180, 230, Color.GREEN);
		kreise2(g, 70, 90, Color.YELLOW);
		kreise2(g, 100, 70, Color.RED);
		kreise3(g, 380, 180, Color.BLUE);
		kreise3(g, 90, 300, Color.WHITE);
	}

	// Aufgabe 6a
	private void kreise(Graphics g, int x, int y, Color color) {
		g.setColor(color);
		breite = GROESSTE_BREITE;
		for (int i = 0; i < ANZAHL_KREISE; i++) {
			breite = breite - VERKLEINERN;      // breite -= VERKLEINERN;
			x = x + VERKLEINERN/2;              // x += VERKLEINERN/2;
			y = y + VERKLEINERN/5;              // y += VERKLEINERN/5;
			g.drawOval(x, y, breite, breite);
		}
	}
	
	// Aufgabe 6c
	private void kreise2(Graphics g, int x, int y, Color color) {
		g.setColor(color);
		breite = GROESSTE_BREITE;
		for (int i = 0; i < ANZAHL_KREISE; i++) {
			breite = breite - VERKLEINERN;      // breite -= VERKLEINERN;
			x = x + VERKLEINERN/3;              // x += VERKLEINERN/3;
			y = y + VERKLEINERN/3;              // y += VERKLEINERN/3;
			g.drawOval(x, y, breite, breite);
		}
	}
	
	// Aufgabe 6d
	private void kreise3(Graphics g, int x, int y, Color color) {
		g.setColor(color);
		breite = GROESSTE_BREITE;
		for (int i = 0; i < ANZAHL_KREISE; i++) {
			if (i % 2 == 0) {
				g.setColor(color);              // Füllen mit angegebener Farbe bei geraden i
			} else {
				g.setColor(BACKGROUND);         // Füllen mit Hintergrundfarbe bei ungeraden i
			}
			breite = breite - VERKLEINERN;      // breite -= VERKLEINERN;
			x = x + VERKLEINERN/2;              // x += VERKLEINERN/2;
			y = y + VERKLEINERN/5;              // y += VERKLEINERN/5;
			g.fillOval(x, y, breite, breite);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufg6 anwendung = new Aufg6("Aufgabe 6");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}