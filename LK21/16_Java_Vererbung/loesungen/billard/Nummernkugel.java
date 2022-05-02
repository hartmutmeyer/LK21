package vererbung.billard;

import java.awt.*;

public class Nummernkugel extends Kugel {
	protected int nummer;

	public Nummernkugel(Color c, int x, int y, int dX, int dY, int nr) {
		super(c, x, y, dX, dY);
		nummer = nr;
	}

	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.BLACK);
		g.drawString("" + nummer, xPos + breite / 2, yPos + breite / 4 * 3);
	}
}
