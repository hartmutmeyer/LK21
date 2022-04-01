// von Jens Altrock

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import hilfe.*;

public class StrichmaennchenAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.YELLOW;
	private static final Color FOREGROUND = Color.BLACK;
	int x = 100;
	int y = 500;
	int Breite = 500;
	int hoehe = 30;
	int zaehler = 0;
	Color farbe;
	int j = 255;
	Strichmaennchen heinz = new Strichmaennchen(20, 30, 3, Color.GREEN, "Heinz");
	Strichmaennchen peter = new Strichmaennchen(50, 300, 6, Color.BLUE, "Peter");
	Strichmaennchen michael = new Strichmaennchen(10, 518, 3, Color.GRAY, "Michael");

	public StrichmaennchenAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Timer timer = new Timer(500, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		if (zaehler % 2 == 0) {
			peter.stehen(g, false, false);
			heinz.stehen(g, false, false);
			michael.treppe(g, true);
		} else {
			peter.gehen(g, false, false);
			heinz.gehen(g, false, false);
			michael.stehen(g, true, false);
		}
		zaehler++;
		for (int i = 0; i < 18; i++) {
			farbe = new Color(0, j, 0);
			g.setColor(farbe);
			g.fillRect(x, y, Breite, hoehe);
			x += 15;
			y -= 30;
			j -= 12;
		}
		x = 100;
		y = 500;
		j = 255;

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StrichmaennchenAnwendung anwendung = new StrichmaennchenAnwendung("Strichmännchen-Anwendung von Jens Altrock");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}