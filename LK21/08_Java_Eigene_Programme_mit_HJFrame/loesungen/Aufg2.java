import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class Aufg2 extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FOREGROUND = Color.BLACK;

	public Aufg2(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		System.out.println("Im Konstruktor");
	}
	
	private void gespenst(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);  
		g.fillOval(x,  y,  50, 50);  // Kopf
		g.setColor(Color.BLACK);  
		g.fillOval(x + 10, y + 10, 10, 10); // linkes Auge
		g.fillOval(x + 30, y + 10, 10, 10); // rechtes Auge
		g.fillOval(x + 10, y + 30, 30, 10); // Mund
	}
	

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		gespenst(g, 10, 20);
		gespenst(g, 300, 150);
		gespenst(g, 70, 130);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufg2 anwendung = new Aufg2("Aufg2");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}