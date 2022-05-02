import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.Timer;
import hilfe.*;

public class SchiffAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.BLUE;
	private static final Color FOREGROUND = Color.BLACK;
	
	private Schiff s1, s2;
	private FensterSchiff f1;
	private AnlegeSchiff a1;
	
	Timer timer = new Timer(20, this);

	public SchiffAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		timer.start();
		
		s1 = new Schiff (Color.ORANGE, 220, 2);
		s2 = new Schiff (Color.PINK, 50, 3);
		f1 = new FensterSchiff (Color.LIGHT_GRAY,150, 4);
		a1 = new AnlegeSchiff (Color.PINK, 340, 2, 200);
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		s1.zeichnen(g);
		s1.bewegen();
		s2.zeichnen(g);
		s2.bewegen();
		
		f1.zeichnen(g);
		f1.bewegen();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(200, 400, 110, 100);
		
		a1.zeichnen(g);
		a1.bewegen();	
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchiffAnwendung anwendung = new SchiffAnwendung("Schiff-Anwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}