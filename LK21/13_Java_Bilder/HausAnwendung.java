import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;

public class HausAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = new Color(199, 231, 203);
	private static final Color FOREGROUND = Color.BLACK;
	private Haus h1, h2, h3, h4;
	
	public HausAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		h1 = new Haus(100, 50, this);
		h2 = new Haus(300, 300, this);
		h3 = new Haus(50, 250, this);
		h4 = new Haus(200, 100, this);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		h1.zeichnen(g);
		h2.zeichnen(g);
		h3.zeichnen(g);
		h4.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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