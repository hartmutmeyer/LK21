import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Aufg7 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private static final int BREITE = 30;
	private static final int ANZAHL_ZEILEN = 6;
	private static final int START_X = 200;
	private static final int START_Y = 150;
	int x = START_X; 
	int y = START_Y;

	public Aufg7(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		
	}

	@Override
	public void myPaint(Graphics g) {
		for (int zeile = 1; zeile <= ANZAHL_ZEILEN; zeile++) {
			y = START_Y + zeile * BREITE;
			x = START_X - zeile * BREITE/2;
			for (int kreis = 1; kreis <= zeile; kreis++) {
				x = x + BREITE;
				g.drawOval(x, y, BREITE, BREITE);
			}
		}

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufg7 anwendung = new Aufg7("Aufgabe 7");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}