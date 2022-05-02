import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Wiederholung2 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Stern2[][] s = new Stern2[10][12];
	
	public Wiederholung2(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int y = 0; y < s[0].length; y++) {
			for (int x = 0; x < s.length; x++) {
				s[x][y] = new Stern2(x * 50 + 5, y * 40 + 25);
			}
		}
		Timer timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < s.length; x++) {
			for (int y = 0; y < s[0].length; y++) {
				s[x][y].zeichnen(g);
			}
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Wiederholung2 anwendung = new Wiederholung2("Wiederholung2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}