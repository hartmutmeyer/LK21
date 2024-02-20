import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Maennchen implements KeyListener {

	private int zustand = STEHEN;
	private static final int STEHEN = 1;
	private static final int LAUFEN = 2; 
	private static final int SITZEN = 3; 
	private static final int LIEGEN = 4; 
	private Color farbe = Color.CYAN;
	private int geschwindigkeit = 10;
	private int xPos = 5, yPos = 105;
	private int laufPosition = 0;
	private static int anzahl;
	private Color gesichtsfarbe = Color.YELLOW;
	private Frame f;

	public Maennchen(Frame f) {
		anzahl++;
		this.f = f;
	}

	public Maennchen(Frame f, Color farbe, int geschw, int yPos) {
		this.f = f;
		this.farbe = farbe;
		geschwindigkeit = geschw;
		this.yPos = yPos;
		anzahl++;
	}

	public void koerper(Graphics g, int x, int y) {
		// Kopf
		g.setColor(gesichtsfarbe);
		g.fillOval(x, y, 20, 20);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 20, 20);

		// Koerper
		g.setColor(farbe);
		g.fillOval(x, y + 20, 20, 40);
		g.setColor(Color.BLACK);
	}

	private void laufen1(Graphics g, int x, int y) {
		koerper(g, x, y);

		// Arm
		g.drawLine(x + 10, y + 30, x + 20, y + 40);
		g.drawLine(x + 20, y + 40, x + 30, y + 30);

		// Bein
		g.drawLine(x + 10, y + 60, x + 10, y + 90);
		g.drawLine(x + 10, y + 60, x + 20, y + 88);

		g.drawOval(x, y + 20, 20, 40);
	}

	private void laufen2(Graphics g, int x, int y) {
		koerper(g, x, y);

		// Arm
		g.drawLine(x + 10, y + 30, x + 10, y + 42);
		g.drawLine(x + 10, y + 42, x + 22, y + 42);

		// Bein
		g.drawLine(x + 10, y + 60, x + 10, y + 90);
		g.drawLine(x + 10, y + 60, x + 15, y + 75);
		g.drawLine(x + 15, y + 75, x + 5, y + 85);

		g.drawOval(x, y + 20, 20, 40);
	}

	private void laufen3(Graphics g, int x, int y) {
		koerper(g, x, y);

		// Arm
		g.drawLine(x + 10, y + 30, x, y + 40);
		g.drawLine(x, y + 40, x + 10, y + 50);

		// Bein
		g.drawLine(x + 10, y + 60, x + 20, y + 80);
		g.drawLine(x + 20, y + 80, x + 10, y + 90);
		g.drawLine(x + 10, y + 60, x + 10, y + 80);
		g.drawLine(x + 10, y + 80, x, y + 80);

		g.drawOval(x, y + 20, 20, 40);
	}

	public void laufen(Graphics g) {
		switch (laufPosition) {
		case 0:
			laufen1(g, xPos, yPos);
			break;
		case 1:
			laufen2(g, xPos, yPos);
			break;
		case 2:
			laufen3(g, xPos, yPos);
			break;
		case 3:
			laufen2(g, xPos, yPos);
			break;
		}
		laufPosition = (laufPosition + 1) % 4;
	}

	private void stehen(Graphics g, int x, int y) {
		koerper(g, x, y);

		// Arm
		g.drawLine(x + 10, y + 30, x + 10, y + 50);

		// Bein
		g.drawLine(x + 10, y + 60, x + 10, y + 90);

		g.drawOval(x, y + 20, 20, 40);
	}

	private void sitzen(Graphics g, int x, int y) {
		y += 30;
		koerper(g, x, y);

		// Arm
		g.drawLine(x + 10, y + 30, x + 25, y + 48);

		// Bein
		g.drawLine(x + 10, y + 60, x + 40, y + 60);

		g.drawOval(x, y + 20, 20, 40);
	}

	private void liegen(Graphics g, int x, int y) {
		y += 70;
		// Kopf
		g.setColor(gesichtsfarbe);
		g.fillOval(x + 70, y, 20, 20);
		g.setColor(Color.BLACK);
		g.drawOval(x + 70, y, 20, 20);

		// Koerper
		g.setColor(farbe);
		g.fillOval(x + 30, y, 40, 20);
		g.setColor(Color.BLACK);

		// Arm
		g.drawLine(x + 60, y + 10, x + 90, y + 20);

		// Bein
		g.drawLine(x, y + 20, x + 30, y + 10);

		g.drawOval(x + 30, y, 40, 20);
	}

	public void zeichnen(Graphics g) {
		switch (zustand) {
		case STEHEN:
			stehen(g, xPos, yPos);
			break;
		case LAUFEN:
			bewegen();
			laufen(g);
			break;
		case SITZEN:
			sitzen(g, xPos, yPos);
			break;
		case LIEGEN:
			liegen(g, xPos, yPos);
			break;
		}
	}

	private void bewegen() {
		xPos += geschwindigkeit;
		if (xPos >= f.getWidth())
			xPos = 0;
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

		if (c == 'x' || c == 'X') {
			switch (zustand) {
			case STEHEN:
				zustand = LAUFEN;
				break;
			case LAUFEN:
				zustand = LIEGEN;
				break;
			case SITZEN:
				zustand = LAUFEN;
				break;
			}
		}

		if (c == 'y' || c == 'Y') {
			switch (zustand) {
			case STEHEN:
				zustand = SITZEN;
				break;
			case LAUFEN:
				zustand = STEHEN;
				break;
			case SITZEN:
				zustand = STEHEN;
				break;
			}
		}
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
}
