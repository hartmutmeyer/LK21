import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.Timer;

import hilfe.*;

public class Himmel extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final Color BACKGROUND = new Color(90, 180, 255);
	private static final Color FOREGROUND = Color.BLACK;
	protected Image vogel1, vogel2;
	protected Image[] gans = new Image[4];
	private Wolke w1 = new Wolke(100, 350);
	private Wolke w2 = new Wolke(200, 200);
	private Wolke w3 = new Wolke(500, 300);
	private Gans g1 = new Gans(200);
	private Gans g2 = new Gans(100, 100);
	private Gans g3 = new Gans(400, 200, 3);
	private Vogel v = new Vogel();
	
	public Himmel(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		gans[0] = getToolkit().getImage(getClass().getResource("Gans1.gif"));
		gans[1] = getToolkit().getImage(getClass().getResource("Gans2.gif"));
		gans[2] = getToolkit().getImage(getClass().getResource("Gans3.gif"));
		gans[3] = getToolkit().getImage(getClass().getResource("Gans4.gif"));
		vogel1 = getToolkit().getImage(getClass().getResource("bird1.gif"));
		vogel2 = getToolkit().getImage(getClass().getResource("bird2.gif"));
		MediaTracker mt = new MediaTracker(this); // frame ist das Frame-Objekt
		mt.addImage(gans[0], 1);
		mt.addImage(gans[1], 1);
		mt.addImage(gans[2], 1);
		mt.addImage(gans[3], 1);
		mt.addImage(vogel1, 1);
		mt.addImage(vogel2, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
		Timer timer = new Timer(150, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		w1.zeichnen(g);
		w2.zeichnen(g);
		w3.zeichnen(g);
		g1.zeichnen(g);
		g2.zeichnen(g);
		g3.zeichnen(g);
		g1.bewegen();
		g2.bewegen();
		g3.bewegen();
		v.zeichnen(g);
		v.bewegen();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Himmel anwendung = new Himmel("Himmel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}