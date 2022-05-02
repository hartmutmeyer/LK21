import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class SchrumpfendeKerze extends BrennendeKerze {
	public SchrumpfendeKerze(int x, int y, Color farbe, boolean brennend) {
		super(x, y, farbe, brennend);
	}

	public void zeichnen(Graphics g) {
		if (brennend) {
			if (hoehe > 0) {
				hoehe--;
				y++;
			} else {
				brennend = false;
			}
		}
		super.zeichnen(g);
	}
}
