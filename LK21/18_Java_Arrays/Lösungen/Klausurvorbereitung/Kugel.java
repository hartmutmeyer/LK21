import java.awt.*;

public class Kugel {
	protected Color farbe;
	protected int xPos, yPos, breite = 20;
	protected int vX, vY; // Geschwindigkeit in x- und y-Richtung

	public Kugel(Color c, int x, int y, int vX, int vY) {
		farbe = c;
		xPos = x;
		yPos = y;
		this.vX = vX;
		this.vY = vY;
	}

	public boolean bewegen(int fensterBreite, int fensterHoehe) {
	// gibt bei Richtungsänderung true zurück, sonst false
		boolean richtungsaenderung = false;
		xPos += vX;
		yPos += vY;

		if (xPos <= 0 || xPos + breite >= fensterBreite) {
			vX  *= -1;
			richtungsaenderung = true;
		}

		if (yPos <= 0 || yPos + breite >= fensterHoehe) {
			vY *= -1;
			richtungsaenderung = true;
		}
		
		return richtungsaenderung;
	}

	public void zeichnen(Graphics g) {
		g.setColor(farbe);
		g.fillOval(xPos, yPos, breite, breite);
	}

}
