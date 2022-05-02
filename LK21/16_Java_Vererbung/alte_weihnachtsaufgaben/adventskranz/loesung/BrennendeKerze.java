import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class BrennendeKerze extends Kerze {
	protected boolean bGelb = false;
	protected boolean brennend;

	public BrennendeKerze(int x, int y, Color farbe, boolean brennend) {
		super(x, y, farbe);
		this.brennend = brennend;
	}

	public void zeichnen(Graphics g) {
		if (brennend) {
			bGelb = !bGelb;
			if (bGelb) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillOval(x, y - 20, 20, 20);
			HZeichnen.fillDreieck(g, x, y - 10, x + 20, y - 10, x + 10, y - 40);
		}
		super.zeichnen(g);
	}
}
