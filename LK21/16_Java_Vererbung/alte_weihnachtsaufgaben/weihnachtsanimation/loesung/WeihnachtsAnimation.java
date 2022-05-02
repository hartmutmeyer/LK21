import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Random;

import javax.swing.Timer;

import hilfe.*;

public class WeihnachtsAnimation extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private static final Color BACKGROUND = new Color(200,255,228);
	private static final Color FOREGROUND = Color.BLACK;
	protected Image haus1, haus2, weihnachtsmann, weihnachtsmann2, frau;
    protected Random zufall = new Random();
    protected Haus h1, h2, h3, h4, h5;
    private Mann m1;
    protected Frau f1, f2, f3;
    
	private WeihnachtsAnimation(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		haus1 = getToolkit().getImage(getClass().getResource("haus1.gif"));
        haus2 = getToolkit().getImage(getClass().getResource("haus2.gif"));
        weihnachtsmann = getToolkit().getImage(getClass().getResource("weihnachtsmann.gif"));
        weihnachtsmann2 = getToolkit().getImage(getClass().getResource("weihnachtsmann2.gif"));
        frau = getToolkit().getImage(getClass().getResource("frau.gif"));
        MediaTracker mt = new MediaTracker(this);  // frame ist das Frame-Objekt
        mt.addImage(haus1, 1);
        mt.addImage(haus2, 2);
        mt.addImage(weihnachtsmann, 3);
        mt.addImage(weihnachtsmann2, 4);
        mt.addImage(frau, 5);
        try {
            mt.waitForAll();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        m1 = new VerliebterMann (this);
        h1 = new Haus (this, 200, 50);
        h2 = new Haus (this, 250, 150);
        h3 = new Haus (this, 300, 250);
        h4 = new Haus (this, 350, 350);
        h5 = new Haus (this, 400, 450);
        f1 = new Frau(this);
        f2 = new Frau(this);
        f3 = new Frau(this);
        Timer timer = new Timer(100, this);
        timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		m1.zeichnen(g);
        f1.zeichnen(g);
        f2.zeichnen(g);
        f3.zeichnen(g);
        h1.zeichnen(g);
        h2.zeichnen(g);
        h3.zeichnen(g);
        h4.zeichnen(g);
        h5.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					WeihnachtsAnimation anwendung = new WeihnachtsAnimation("WeihnachtsAnimation");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}