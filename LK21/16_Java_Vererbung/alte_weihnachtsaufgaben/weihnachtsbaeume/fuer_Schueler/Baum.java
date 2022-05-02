import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class Baum {
	protected int x, y;

	public Baum(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		Color gruen = new Color(40, 150, 0);
		g.setColor(gruen);
		HZeichnen.fillDreieck(g, x + 40, y + 60, x + 100, y, x + 160, y + 60);
		HZeichnen.fillDreieck(g, x + 20, y + 120, x + 100, y + 40, x + 180,
				y + 120);
		HZeichnen.fillDreieck(g, x, y + 180, x + 100, y + 80, x + 200, y + 180);
		g.setColor(Color.BLACK);
		HZeichnen.drawDreieck(g, x + 40, y + 60, x + 100, y, x + 160, y + 60);
		HZeichnen.drawDreieck(g, x + 20, y + 120, x + 100, y + 40, x + 180,
				y + 120);
		HZeichnen.drawDreieck(g, x, y + 180, x + 100, y + 80, x + 200, y + 180);
		g.setColor(gruen);
		g.fillRect(x + 80, y + 35, 40, 70);
		g.fillRect(x + 60, y + 90, 80, 50);
		g.setColor(Color.BLACK);
	}
}
