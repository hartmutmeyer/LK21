import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.Timer;

import hilfe.*;

public class Autorennen extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	static final int HEIGHT = 800;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Timer timer;
	Auto a1, a2;

	public Autorennen(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		a1 = new Auto(1, Color.RED, this);
		a2 = new Auto(2, Color.BLUE, this);
		timer = new Timer(50, this);
		timer.start();
	}
	
	private void rennstrecke(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(5, 5, (WIDTH - 20) / 2, HEIGHT - 10);
		g.fillRect((WIDTH + 10) / 2, 5, (WIDTH - 20) / 2, HEIGHT - 10);
	}

	@Override
	public void myPaint(Graphics g) {
		rennstrecke(g);
		a1.zeichnen(g);
		a2.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Autorennen anwendung = new Autorennen("Autorennen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}