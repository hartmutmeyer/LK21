import java.awt.Color;
import java.awt.Graphics;

public class Raupe {
	private int x, y, v_x, zaehler1, zaehler2;
	private Color farbe;

	public Raupe(int x, int y, Color farbe, int v_x) {
		this.x = x;
		this.y = y;
		this.farbe = farbe;
		this.v_x = v_x;
	}

	public void zeichnen(Graphics g) {
		if (zaehler1++ % 3 == 0) {
			zaehler2++;
		}
		if ((zaehler2 % 2 == 0) && (v_x > 0)) {
			g.setColor(farbe);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x + 60, y, 20, 20);
			g.fillRect(x + 10, y, 60, 20);
		} else {
			g.setColor(farbe);
			g.fillOval(x + 10, y, 20, 20);
			g.fillOval(x + 50, y, 20, 20);
			g.fillRect(x + 20, y, 40, 20);
		}
	}

	public void bewegen() {
		if ((x += v_x) == 500) {
			x = -100;
		}
	}
}
