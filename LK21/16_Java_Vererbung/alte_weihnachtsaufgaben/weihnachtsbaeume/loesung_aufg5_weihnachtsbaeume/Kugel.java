import java.awt.*;
import java.awt.event.*;
import hilfe.*;
import java.util.*;

class Kugel {
	private int x, y;

	public Kugel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 20, 20);
	}
}
