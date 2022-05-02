import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class KugelBaum extends Baum {
	Kugel k1, k2, k3, k4, k5, k6;

	public KugelBaum(int x, int y) {
		super(x, y);
		k1 = new Kugel(x + 90, y + 30);
		k2 = new Kugel(x + 70, y + 80);
		k3 = new Kugel(x + 110, y + 80);
		k4 = new Kugel(x + 90, y + 120);
		k5 = new Kugel(x + 50, y + 150);
		k6 = new Kugel(x + 130, y + 150);
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		k1.zeichnen(g);
		k2.zeichnen(g);
		k3.zeichnen(g);
		k4.zeichnen(g);
		k5.zeichnen(g);
		k6.zeichnen(g);
	}
}
