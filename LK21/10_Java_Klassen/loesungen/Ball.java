import java.awt.*;
import java.awt.event.*;
import hilfe.*;

class Ball {
	private int x = 25;
	private int y = 250;
	private int breite = 50;
	private Color farbe;
	private boolean nachRechts = true;
	private int kreisZähler = 0;
	private static int anzahl;

	public Ball(Color farbe) {
		this.farbe = farbe;
		x = 25 + 50 * anzahl;
		anzahl++;
	}

	private int kreisOben(int x) {
		return 250 - (int) Math.sqrt(40000 - (x - 225) * (x - 225));
	}

	private int kreisUnten(double x) {
		return 250 + (int) Math.sqrt(40000 - (x - 225) * (x - 225));
	}

	public void zeichnen(Graphics g) {
		if (nachRechts) {
			x = x + 2;
			if (x >= 425)
				nachRechts = false;
		} else {
			x = x - 2;
			if (x <= 25) {
				nachRechts = true;
				kreisZähler++;
				if (kreisZähler == 3)
					kreisZähler = 0;
			}
		}
		if (kreisZähler < 2) {
			if (nachRechts)
				y = kreisOben(x);
			else
				y = kreisUnten(x);
		}
		g.setColor(farbe);
		g.fillOval(x, y, breite, breite);
	}

}
