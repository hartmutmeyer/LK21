import java.awt.Color;
import java.awt.Graphics;

public class Lampe {
	private int x;
	private int y;
	private Color f;
	final int BREITE = 100;
	private boolean isOn = true;

	public Lampe(int e, int r, Color t) {
		x = e;
		y = r;
		f = t;
	}

	public void an() {
		isOn = true;
	}

	public void koord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void aus() {
		isOn = false;
	}

	public boolean getAn() {
		return isOn;
	}

	public void zeichnen(Graphics g) {
		if (isOn == true) {
			g.setColor(f);
		} else {
			g.setColor(Color.GRAY);
		}
		g.fillOval(x, y, BREITE, BREITE);

	}

}