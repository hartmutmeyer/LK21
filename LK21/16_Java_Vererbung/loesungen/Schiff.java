package vererbung;

import hilfe.HZeichnen;

import java.awt.Color;
import java.awt.Graphics;

public class Schiff {
	protected int xPos;
	protected int yPos;
	protected Color farbe;
	protected int speed;

	public Schiff(Color f, int y, int speed) {
		this.farbe = f;
		this.yPos = y;
		this.speed = speed;
		xPos = 0;
	}

	public void bewegen() {
		if (xPos < 500) {
			xPos = xPos + speed;
		} else {
			xPos = -100;
		}
	}

	public void zeichnen(Graphics g) {
		g.setColor(farbe);
		g.fillRect(xPos + 30, yPos, 50, 60);
		HZeichnen.fillDreieck(g, xPos, yPos + 30, xPos + 30, yPos + 30, xPos + 30, yPos + 60);
		HZeichnen.fillDreieck(g, xPos + 80, yPos + 30, xPos + 110, yPos + 30, xPos + 80, yPos + 60);
	}
}
