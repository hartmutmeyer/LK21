package farbenUndZufall;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Wald2 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Random rnd = new Random();
	
	public Wald2(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int i = 0; i < 50; i++) {
			baum(g, rnd.nextInt(450), rnd.nextInt(370) + 30);
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
					Wald2 anwendung = new Wald2("Wald2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}