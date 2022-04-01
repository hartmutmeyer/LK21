import hilfe.HZeichnen;

import java.awt.Color;
import java.awt.Graphics;

public class Stern {
	Color farbe;
	private int x, y, v_x, zaehler;
	private boolean blinkend = false;
	public static boolean wolke_da = false;
	private static int anzahlSterne;

	public Stern(int x, int y) {
		this.x = x;
		this.y = y;
		v_x = 3;
		if ((anzahlSterne++ % 2) == 0) {
			farbe = Color.YELLOW;
		} else {
			farbe = Color.ORANGE;
		}
	}

	public Stern(int x, int y, boolean blinkend) {
		this.x = x;
		this.y = y;
		this.blinkend = blinkend;
		v_x = 3;
		if ((anzahlSterne++ % 2) == 0) {
			farbe = Color.YELLOW;
		} else {
			farbe = Color.ORANGE;
		}
	}

	public void zeichnen(Graphics g, int width) {
		if (blinkend == true) {
			if (zaehler++ % 2 == 0) {
				if (wolke_da == false) {
					g.setColor(farbe);
					HZeichnen.fillDreieck(g, x + 20, y, x + 40, y + 30, x, y + 30);
					HZeichnen.fillDreieck(g, x, y + 10, x + 40, y + 10, x + 20, y + 40);
				}
			}
		} else {
			if (wolke_da == false) {
				g.setColor(farbe);
				HZeichnen.fillDreieck(g, x + 20, y, x + 40, y + 30, x, y + 30);
				HZeichnen.fillDreieck(g, x, y + 10, x + 40, y + 10, x + 20, y + 40);
			}
		}
		if ((x += v_x) > width) {
			x = -40;
		}
	}
}
