import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class HundAnw extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	Hund h1, h2, h3, h4;
	Timer timer = new Timer(100, this);
	int zaehler;

	public HundAnw(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		timer.start();
		h1 = new Hund(50, 100, Color.GRAY);
		h2 = new Hund(300, 200, Color.BLACK);
		h3 = new Hund(150, 400, true);
		h4 = new Hund(250, 300, Color.RED);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		h1.stehen(g);
		h2.x = zaehler % 550;
		zaehler += 10;
		if (zaehler % 20 == 0) {
			h2.springen(g);
		} else {
			h2.stehen(g);
		}
		h3.stehen(g);
		h3.wedeln = !h3.wedeln;
		h4.x = zaehler / 2 % 550;
		if (zaehler % 20 == 0) {
			h4.springen(g);
		} else {
			h4.stehen(g);
		}
		g.drawString("Es gibt " + Hund.getAnzahlHunde() + " Hunde.", 350, 50);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HundAnw anwendung = new HundAnw("HundAnw");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}