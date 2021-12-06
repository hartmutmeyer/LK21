import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Aufg4 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	int zaehler;
	final int BREITE = 30;
	final int ABSTAND = 10;
	final int STARTX = 20;
	final int STARTY = 40;

	public Aufg4(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		g.drawString("myPaint() wurde " + ++zaehler + " mal aufgerufen", STARTX, 20);
		g.setColor(Color.GREEN);
		int x = STARTX;
		for(int i=0; i<zaehler; i++) {
			g.fillRect(x, STARTY, BREITE, BREITE);
			x += BREITE + ABSTAND;
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufg4 anwendung = new Aufg4("Aufgabe 4");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}