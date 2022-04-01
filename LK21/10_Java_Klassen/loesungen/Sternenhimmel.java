import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Sternenhimmel extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;
	Stern s1, s2, s3, s4, s5;
	private int zeit, zeitschritt = 100;
	Timer timer = new Timer(zeitschritt, this);
	
	public Sternenhimmel(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		timer.start();
		s1 = new Stern(50, 100);
		s2 = new Stern(150, 200);
		s3 = new Stern(200, 120, true);
		s4 = new Stern(300, 150);
		s5 = new Stern(370, 130);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		if (Stern.wolke_da == false) {
			if ((zeit += zeitschritt) % 1200 == 0) {
				Stern.wolke_da = true;
			}
		} else {
			if ((zeit += zeitschritt) % 600 == 0) {
				Stern.wolke_da = false;
			}
		}
		s1.zeichnen(g, WIDTH);
		s2.zeichnen(g, WIDTH);
		s3.zeichnen(g, WIDTH);
		s4.zeichnen(g, WIDTH);
		s5.zeichnen(g, WIDTH);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sternenhimmel anwendung = new Sternenhimmel("Sternenhimmel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}