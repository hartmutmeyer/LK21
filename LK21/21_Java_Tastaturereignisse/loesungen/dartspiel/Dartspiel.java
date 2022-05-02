import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import hilfe.*;

public class Dartspiel extends HJFrame implements KeyListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Pfeil p;

	public Dartspiel(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		p = new Pfeil(50, 50);
		Timer timer = new Timer(10, this);
		timer.start();
		addKeyListener(this);
	}

	private void dartscheibe(Graphics g) {
		g.drawOval (400,200,30,30);
		g.drawOval (385,185,60,60);
		g.drawOval(370, 170, 90, 90);
		g.drawOval(355, 155, 120,120);
	}
	
	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		dartscheibe(g);
		p.zeichnen(g);
		p.bewegen();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dartspiel anwendung = new Dartspiel("Dartspiel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
	//	System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'n':
		case 'N':
			p.fallenLassen();
			break;
		case 's':
		case 'S':
			p.schiessen();
			break;
		case 'r':
		case 'R':
			p.reset();
		}
		System.out.println("Taste wurde gedrückt: " + e.getKeyChar());
	}
}