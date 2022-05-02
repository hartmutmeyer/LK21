import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import hilfe.*;
import vererbung.drachen.unterpaket.GesichtDrachen;

public class DrachenAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Drache d1;
	private GesichtDrache d2;

	public DrachenAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		d1 = new Drache(100, 150, Color.RED);
		d2 = new GesichtDrache(300, 50, Color.DARK_GRAY);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		d1.zeichnen(g);
		d2.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrachenAnwendung anwendung = new DrachenAnwendung("DrachenAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}