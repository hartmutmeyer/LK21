import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class SternBaum extends KugelBaum {
	protected int zustand = 0;

	public SternBaum(int x, int y) {
		super(x, y);
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.YELLOW);
		switch (zustand) {
		case 0:
			HZeichnen.fillDreieck(g, x + 80, y - 30, x + 120, y - 30, x + 100,
					y);
			HZeichnen.fillDreieck(g, x + 80, y - 10, x + 120, y - 10, x + 100,
					y - 40);
			break;
		case 1:
		case 3:
			HZeichnen.fillDreieck(g, x + 90, y - 30, x + 110, y - 30, x + 100,
					y);
			HZeichnen.fillDreieck(g, x + 90, y - 10, x + 110, y - 10, x + 100,
					y - 40);
			break;
		case 2:
			g.drawLine(x + 100, y, x + 100, y - 40);
			break;
		}
		zustand++;
		if (zustand == 4) {
			zustand = 0;
		}
	}
}
