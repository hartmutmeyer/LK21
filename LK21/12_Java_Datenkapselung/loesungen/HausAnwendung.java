import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class HausAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Haus haus;
	private Timer timer;

	public HausAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		haus = new Haus(30, 470, 10);
		timer = new Timer(25, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		haus.zeichnen(g);
		haus.setHoehe(haus.getHoehe() + 1);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HausAnwendung anwendung = new HausAnwendung("HausAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}