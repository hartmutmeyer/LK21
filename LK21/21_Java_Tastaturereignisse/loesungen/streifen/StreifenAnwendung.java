import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import hilfe.*;

public class StreifenAnwendung extends HJFrame implements KeyListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private boolean istWeiss = true;
	private Streifen[] s = new Streifen[10];
	private Random zufall = new Random();
	
	public StreifenAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		addKeyListener(this);
		for (int i = 0; i < s.length; i++) {
			s[i] = new Streifen(this, 40 + i * 40, (char) ('A' + i), (char) ('a' + i));
		}
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int i = 0; i < s.length; i++) {
			s[i].zeichnen(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == 'x' || c == 'X')
			istWeiss = !istWeiss;
		if (istWeiss)
			zeichenflaeche.setBackground(Color.WHITE);
		else
			zeichenflaeche.setBackground(Color.BLACK);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StreifenAnwendung anwendung = new StreifenAnwendung("Streifen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}