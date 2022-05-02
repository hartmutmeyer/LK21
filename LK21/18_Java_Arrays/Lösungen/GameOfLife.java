import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.Timer;

import hilfe.*;

public class GameOfLife extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final Color BACKGROUND = Color.LIGHT_GRAY;
	private static final Color FOREGROUND = Color.BLUE;
	private static final int CELLWIDTH = 8;
	private static final int GROESSE = WIDTH/CELLWIDTH;
	private static final boolean LEBENDIG = true;
	private static final boolean TOT = false;
	private int runde;
	private boolean[][] spielfeldA = new boolean[GROESSE + 2][GROESSE + 2];
	private boolean[][] spielfeldB = new boolean[GROESSE + 2][GROESSE + 2];
	private Random zufall = new Random();
	
	public GameOfLife(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		for (int x = 1; x <= GROESSE; x++) {
			for (int y = 1; y <= GROESSE; y++) {
				spielfeldA[x][y] = zufall.nextBoolean();
			}
		}
		Timer timer = new Timer(100, this);
		timer.start();		
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		if ((runde++ % 2) == 0) {
			for (int x = 1; x <= GROESSE; x++) {
				for (int y = 1; y <= GROESSE; y++) {
					if (spielfeldA[x][y] == LEBENDIG) {
						g.fillRect((x-1) * CELLWIDTH, (y-1) * CELLWIDTH,
								CELLWIDTH, CELLWIDTH);
					} else {
						g.drawRect((x-1) * CELLWIDTH, (y-1) * CELLWIDTH,
								CELLWIDTH, CELLWIDTH);
					}
					spielfeldB[x][y] = totOderLebendig(x, y, spielfeldA);
				}
			}
		} else {
			for (int x = 1; x <= GROESSE; x++) {
				for (int y = 1; y <= GROESSE; y++) {
					if (spielfeldB[x][y] == LEBENDIG) {
						g.fillRect((x-1) * CELLWIDTH, (y-1) * CELLWIDTH,
								CELLWIDTH, CELLWIDTH);
					} else {
						g.drawRect((x-1) * CELLWIDTH, (y-1) * CELLWIDTH,
								CELLWIDTH, CELLWIDTH);
					}
					spielfeldA[x][y] = totOderLebendig(x, y, spielfeldB);
				}
			}
		}
	}

	public int anzahlNachbarn(int x, int y, boolean[][] spielfeld) {
		int anzahl = 0;
		if (spielfeld[x - 1][y]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y]) {
			anzahl++;
		}
		if (spielfeld[x - 1][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y - 1]) {
			anzahl++;
		}
		if (spielfeld[x - 1][y + 1]) {
			anzahl++;
		}
		if (spielfeld[x][y + 1]) {
			anzahl++;
		}
		if (spielfeld[x + 1][y + 1]) {
			anzahl++;
		}
		return anzahl;
	}

	public boolean totOderLebendig(int x, int y, boolean[][] spielfeld) {
		int nachbarn = anzahlNachbarn(x, y, spielfeld);
		if (spielfeld[x][y] == LEBENDIG) {
			if (nachbarn == 2 || nachbarn == 3) {
				return LEBENDIG;
			} else {
				return TOT;
			}
		} else {
			if (nachbarn == 3) {
				return LEBENDIG;
			} else {
				return TOT;
			}
		}
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLife anwendung = new GameOfLife("Game of Life");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}