import java.awt.*;
import java.awt.event.*;

class Adventsgesteck {
	private int x;
	private int y;
	Kerze k1, k2, k3, k4;

	public Adventsgesteck(int x, int y) {
		this.x = x;
		this.y = y;
		k1 = new SchrumpfendeKerze(x + 20, y + 40, Color.RED, false);
		k2 = new SchrumpfendeKerze(x + 80, y + 40, Color.BLUE, true);
		k3 = new SchrumpfendeKerze(x + 140, y + 40, Color.RED, false);
		k4 = new SchrumpfendeKerze(x + 200, y + 40, Color.BLUE, true);
	}

	public void zeichnen(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y + 100, 240, 30);
		k1.zeichnen(g);
		k2.zeichnen(g);
		k3.zeichnen(g);
		k4.zeichnen(g);
	}

}
