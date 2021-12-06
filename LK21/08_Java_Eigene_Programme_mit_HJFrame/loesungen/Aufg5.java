import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import hilfe.*;

public class Aufg5 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 70;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.YELLOW;
	private static int anzahl;
	private int STARTX = 10;
	private int STARTY = 10;

	public Aufg5(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung	
		anzahl = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Gespenster sollen gezeichnet werden?"));
		repaint();
	}

	public void gespenst(Graphics g, int x, int y) {
		g.setColor(FOREGROUND);
		// Kopf:
		g.fillOval(x, y, 50, 50);
		g.setColor(BACKGROUND);
		// rechtes Auge:
		g.fillOval(x+10, y+10, 10, 10);
		// linkes Auge:
		g.fillOval(x+30, y+10, 10, 10);
		// Mund:
		g.fillOval(x+10, y+30, 30, 10);
	}
	
	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for(int i=0; i<anzahl; i++) {
			gespenst(g, STARTX + i*60, STARTY);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Aufg5 anwendung = new Aufg5("Aufgabe 5");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}