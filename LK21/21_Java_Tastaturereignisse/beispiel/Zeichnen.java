import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import hilfe.*;

public class Zeichnen extends HJFrame implements KeyListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
    private boolean bKreisZeichnen = false;

	public Zeichnen(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
        addKeyListener (this);		
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
        if (bKreisZeichnen) {
            g.drawString("Wenn du 'L' drückst, lösche ich den Kreis.", 20,50);
            g.drawOval(50,100, 100,100);
        } else {
            g.drawString("Wenn du 'M' drückst, male ich einen Kreis.", 20,50);
        }
	}

    public void keyPressed(KeyEvent e) { 
        int c = e.getKeyCode();
        switch (c) {
            case KeyEvent.VK_M:    // Taste 'M' (egal ob groß oder klein)
                bKreisZeichnen = true;
                repaint(); // Bildschirm neu malen
                break;
            case KeyEvent.VK_L:    // Taste 'L'
                bKreisZeichnen = false;
                repaint(); // Bildschirm neu malen
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zeichnen anwendung = new Zeichnen("Zeichnen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}