import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Karo {
	public int x, y;
	public int zustand = 0;

	public Karo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		g.setColor(Color.RED);
		switch (zustand) {
		case 0: {
			int[] xx = { x, x + 40, x + 80, x + 40 };
			int[] yy = { y + 40, y, y + 40, y + 80 };
			g.fillPolygon(xx, yy, 4);
		}
			break;
		case 1:
		case 3:
			int[] xx = { x + 20, x + 40, x + 60, x + 40 };
			int[] yy = { y + 40, y, y + 40, y + 80 };
			g.fillPolygon(xx, yy, 4);
			break;
		case 2:
			g.drawLine(x + 40, y, x + 40, y + 80);
			break;
		}
		zustand++;
		if (zustand == 4) {
			zustand = 0;
		}

	}
}