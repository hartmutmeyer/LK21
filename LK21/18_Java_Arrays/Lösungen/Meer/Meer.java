import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import hilfe.*;

public class Meer extends HJFrame {
	// globale Variablen
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 600;
	private static final Color BACKGROUND = Color.CYAN;
	private static final Color FOREGROUND = Color.BLACK;
	private final int MAX = 5;
	protected Image[] bild = new Image[3];
	protected Random zufall = new Random();
	private Fisch[] fisch = new Fisch[MAX];
	
	public Meer(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int i = 0; i < bild.length; i++) {
			bild[i] = getToolkit().getImage(getClass().getResource("fisch" + (i + 1) + ".gif"));
		}
		for (int i = 0; i < fisch.length; i++) {
			fisch[i] = new Fisch(this);
		}
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int i = 0; i < MAX; i++) {
			fisch[i].zeichnen(g);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Meer anwendung = new Meer("Meer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}