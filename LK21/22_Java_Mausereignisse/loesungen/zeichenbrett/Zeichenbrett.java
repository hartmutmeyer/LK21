import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import hilfe.*;

public class Zeichenbrett extends HJFrame implements MouseListener, MouseMotionListener, KeyListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Color pixel[][] = new Color[500][500];
	private Color farbe = Color.BLACK;
	private int breite = 1;
	
	public Zeichenbrett(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		zeichenflaeche.addMouseListener(this);
		zeichenflaeche.addMouseMotionListener(this);
		addKeyListener(this);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < 500; x++) {
			for (int y = 0; y < 500; y++) {
				if (pixel[x][y] != null) {
					g.setColor(pixel[x][y]);
					g.drawLine(x, y, x, y);
				}
			}
		}
	}

	// Anfang Ereignisprozeduren
	public void mouseClicked(MouseEvent e) {
		for (int x = e.getX(); x < e.getX() + breite; x++) {
			for (int y = e.getY(); y < e.getY() + breite; y++) {
				pixel[x][y] = farbe;
			}
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		for (int x = e.getX(); x < e.getX() + breite; x++) {
			for (int y = e.getY(); y < e.getY() + breite; y++) {
				pixel[x][y] = farbe;
			}
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
			for (int x = 0; x < 500; x++) {
				for (int y = 0; y < 500; y++) {
					pixel[x][y] = null;
				}
			}
			repaint();
		}
		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
			farbe = Color.BLACK;
		}
		if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
			farbe = Color.RED;
		}
		if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
			farbe = Color.BLUE;
		}
		if (e.getKeyChar() == 'g' || e.getKeyChar() == 'G') {
			farbe = Color.GREEN;
		}
		if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
			breite = e.getKeyChar() - '0';
		}
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zeichenbrett anwendung = new Zeichenbrett("JZeichenbrett");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}