import java.awt.*;

public class Uhr {
	private int xPos, yPos;
	protected Ziffer z1, z2, z3, z4; // z1: hintere Ziffer

	Uhr(int x, int y) {
		xPos = x;
		yPos = y;
		z4 = new Ziffer(xPos + 5, yPos + 19, 5);
		z3 = new Ziffer(xPos + 17, yPos + 19, 9);
		z2 = new Ziffer(xPos + 36, yPos + 19, 5);
		z1 = new Ziffer(xPos + 48, yPos + 19, 9);
	}

	void zeichnen(Graphics g) {
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// Rahmen
		g.drawRect(xPos + 2, yPos + 2, 59, 20);
		g.drawRect(xPos + 1, yPos + 1, 61, 22);
		g.drawRect(xPos, yPos, 63, 24);

		// Ziffern
		z1.zeichnen(g);
		z2.zeichnen(g);
		g.drawString(":", xPos + 28, yPos + 19);
		z3.zeichnen(g);
		z4.zeichnen(g);
	}

	void sekunde() {
		if (z1.hochzaehlen()) {
			if (z2.hochzaehlen()) {
				if (z3.hochzaehlen()) {
					z4.hochzaehlen();
				}
			}
		}
	}
}
