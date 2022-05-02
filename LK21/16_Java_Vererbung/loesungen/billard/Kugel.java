package vererbung.billard;

import java.awt.*;

public class Kugel {
	protected Color farbe;
	protected int xPos, yPos, breite = 20;
	protected int diffX, diffY; // Richtungsänderung

	public Kugel(Color c, int x, int y, int dX, int dY) {
		farbe = c;
		xPos = x;
		yPos = y;
		diffX = dX;
		diffY = dY;
	}

	public boolean bewegen(int fensterBreite, int fensterHoehe)
	// gibt bei Richtungsänderung true zurück, sonst false
	{
		boolean change = false;
		xPos += diffX;
		yPos += diffY;

		if (xPos <= 0 || xPos + breite >= fensterBreite) {
			diffX = 0 - diffX;
			change = true;
		}

		if (yPos <= 0 || yPos + breite >= fensterHoehe) {
			diffY = 0 - diffY;
			change = true;
		}
		return change;
	}

	public void zeichnen(Graphics g) {
		g.setColor(farbe);
		g.fillOval(xPos, yPos, breite, breite);
		// g.setColor(Color.BLACK);
		// g.drawOval(xPos, yPos, breite, breite);
	}

}
