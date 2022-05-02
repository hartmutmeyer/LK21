import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class Wiederholung1 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 300;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;
	private Karo3[] karo = new Karo3[9];
	
	public Wiederholung1(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int i = 0; i < karo.length; i++) {
			karo[i] = new Karo3(i * 80 + 40, 100);
		}
		Timer timer = new Timer(250, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int i = 0; i < karo.length; i++) {
			karo[i].zeichnen(g);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wiederholung1 anwendung = new Wiederholung1("Wiederholung1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}