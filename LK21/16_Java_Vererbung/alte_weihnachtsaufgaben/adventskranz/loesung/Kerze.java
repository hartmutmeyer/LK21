import java.awt.*;
import java.awt.event.*;

class Kerze {
	protected int x;
	protected int y;
	protected int hoehe = 60;
	protected Color farbe;

	public Kerze(int x, int y, Color farbe) {
		this.x = x;
		this.y = y;
		this.farbe = farbe;
	}

	public void zeichnen(Graphics g) {
		g.setColor(farbe);
		g.fillRect(x, y, 20, hoehe);
		g.setColor(Color.BLACK);
		g.fillRect(x + 9, y - 10, 3, 10);
	}
}
