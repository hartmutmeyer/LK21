import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import hilfe.*;

public class Snake extends HJFrame implements MouseListener, KeyListener {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private static final int BREITE = 5;
	private static final int XMAX = WIDTH / BREITE;
	private static final int YMAX = HEIGHT / BREITE;
	private static final int WAND = -1;
	private static final int FREI = 0;
	private static final int ROT = 1;
	private static final int BLAU = 2;
	private static final int RECHTS = 1;
	private static final int LINKS = -1;
	private static final int OBEN = -1;
	private static final int UNTEN = 1;
	private int feld[][] = new int[XMAX][YMAX];
	private int xPosRot, yPosRot, vxRot, vyRot;
	private int xPosBlau, yPosBlau, vxBlau, vyBlau;
	private boolean beendet = false;
	private Timer timer;

	public Snake(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		// Grenzen des Spielfeldes "schwarz machen"
		for (int x = 0; x < XMAX; x++) {
			feld[x][0] = WAND;
			feld[x][1] = WAND;
			feld[x][YMAX - 2] = WAND;
			feld[x][YMAX - 1] = WAND;
		}
		for (int y = 0; y < YMAX; y++) {
			feld[0][y] = WAND;
			feld[1][y] = WAND;
			feld[XMAX - 2][y] = WAND;
			feld[XMAX - 1][y] = WAND;
		}
		// Kopf der roten Schlange in die Spielfeldmitte setzen
		xPosRot = XMAX / 2;
		yPosRot = YMAX / 2;
		feld[xPosRot][yPosRot] = ROT;
		// Rote Schlange läuft anfangs nach rechts
		vxRot = RECHTS;
		// Kopf der blauen Schlange in die Spielfeldmitte setzen
		xPosBlau = XMAX / 2 - 1;
		yPosBlau = YMAX / 2;
		feld[xPosBlau][yPosBlau] = BLAU;
		// Blaue Schlange läuft anfangs nach links
		vxBlau = LINKS;

		addKeyListener(this);
		addMouseListener(this);
		timer = new Timer(50, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		// Aktuelle Position des roten Schlangenkopfes berechnen und in
		// feld[][] eintragen
		xPosRot += vxRot;
		yPosRot += vyRot;
		if (feld[xPosRot][yPosRot] == FREI) {
			feld[xPosRot][yPosRot] = ROT;
		} else {
			if (!beendet) {
				beendet = true;
				gameOver("Rot hat verloren!");
			}
		}
		// Aktuelle Position des blauen Schlangenkopfes berechnen und in
		// feld[][] eintragen
		xPosBlau += vxBlau;
		yPosBlau += vyBlau;
		if (feld[xPosBlau][yPosBlau] == FREI) {
			feld[xPosBlau][yPosBlau] = BLAU;
		} else {
			if (!beendet) {
				beendet = true;
				gameOver("Blau hat verloren!");
			}
		}

		// Feld mit aktuellen Werten neu zeichnen
		for (int x = 0; x < XMAX; x++) {
			for (int y = 0; y < YMAX; y++) {
				switch (feld[x][y]) {
				case ROT:
					g.setColor(Color.RED);
					g.fillRect(x * BREITE, y * BREITE, BREITE, BREITE);
					break;
				case BLAU:
					g.setColor(Color.BLUE);
					g.fillRect(x * BREITE, y * BREITE, BREITE, BREITE);
					break;
				case WAND:
					g.setColor(Color.BLACK);
					g.fillRect(x * BREITE, y * BREITE, BREITE, BREITE);
					break;
				}
			}
		}
	}

	public void gameOver(final String nachricht) {
		vxBlau = 0;
		vyBlau = 0;
		vxRot = 0;
		vyRot = 0;
		timer.stop();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JOptionPane.showMessageDialog(null, nachricht, "Game Over",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		});
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_UP) {
			if (vyRot == 0) {
				vyRot = OBEN;
				vxRot = 0;
			}
		}
		if (c == KeyEvent.VK_DOWN) {
			if (vyRot == 0) {
				vyRot = UNTEN;
				vxRot = 0;
			}
		}
		if (c == KeyEvent.VK_RIGHT) {
			if (vxRot == 0) {
				vxRot = RECHTS;
				vyRot = 0;
			}
		}
		if (c == KeyEvent.VK_LEFT) {
			if (vxRot == 0) {
				vxRot = LINKS;
				vyRot = 0;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// nach links drehen wenn der linke Mausknopf gedrückt wurde
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (vxBlau == RECHTS) {
				vxBlau = 0;
				vyBlau = OBEN;
			} else {
				if (vxBlau == LINKS) {
					vxBlau = 0;
					vyBlau = UNTEN;
				} else {
					if (vyBlau == UNTEN) {
						vyBlau = 0;
						vxBlau = RECHTS;
					} else {
						vyBlau = 0;
						vxBlau = LINKS;
					}
				}
			}
		}
		// nach rechts drehen wenn der rechte Mausknopf gedrückt wurde
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (vxBlau == RECHTS) {
				vxBlau = 0;
				vyBlau = UNTEN;
			} else {
				if (vxBlau == LINKS) {
					vxBlau = 0;
					vyBlau = OBEN;
				} else {
					if (vyBlau == UNTEN) {
						vyBlau = 0;
						vxBlau = LINKS;
					} else {
						vyBlau = 0;
						vxBlau = RECHTS;
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Snake anwendung = new Snake("Snake");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}