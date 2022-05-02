package vererbung;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import vererbung.billard.*;
import hilfe.*;

public class Billard extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.GREEN;
	private static final Color FOREGROUND = Color.BLACK;
	private Kugel k1, k2, k3, k4;

	public Billard(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		k1 = new Kugel(Color.RED, 1, 1, 3, 1);
		k2 = new Nummernkugel(Color.YELLOW, 100, 100, 1, 2, 5);
		k3 = new Pulsierkugel(Color.BLUE, 200, 200, 4, 1);
		k4 = new Reibungskugel(Color.MAGENTA, 300, 100, 3, 2);
		Timer timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		k1.bewegen(WIDTH, HEIGHT);
		k1.zeichnen(g);
		k2.bewegen(WIDTH, HEIGHT);
		k2.zeichnen(g);
		k3.bewegen(WIDTH, HEIGHT);
		k3.zeichnen(g);
		k4.bewegen(WIDTH, HEIGHT);
		k4.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billard anwendung = new Billard("Billard");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}