import java.awt.*;
import java.util.*;

class Kette {
	private Kreis k1, k2, k3, k4, k5, k6;
	private boolean an = true;
	private int zaehler;
	private Random zufall;

	public Kette(Color farbe, int x, int y, Random zufall) {
		k1 = new Kreis(farbe, x + 20, y);
		k2 = new Kreis(farbe, x + 60, y);
		k3 = new Kreis(farbe, x + 80, y + 40);
		k4 = new Kreis(farbe, x + 60, y + 80);
		k5 = new Kreis(farbe, x + 20, y + 80);
		k6 = new Kreis(farbe, x, y + 40);
		this.zufall = zufall;
		zaehler = zufall.nextInt(11) + 5;
	}

	void zeichnen(Graphics g) {
		if (an) {
			k1.zeichnen(g);
			k2.zeichnen(g);
			k3.zeichnen(g);
			k4.zeichnen(g);
			k5.zeichnen(g);
			k6.zeichnen(g);
		}
		zaehler--;
		if (zaehler == 0) {
			zaehler = zufall.nextInt(11) + 5;
			an = !an;
		}
	}

}