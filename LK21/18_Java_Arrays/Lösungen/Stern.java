import java.awt.*;
import java.awt.event.*;
import hilfe.*;
import java.util.*;

public class Stern {
	public int x, y;
	static Random rnd = new Random();
	Color farbe;

	public Stern(int x, int y) {
		this.x = x;
		this.y = y;
		farbe = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
	}

	public void zeichnen(Graphics g) {
		int[] xx = { x, x + 40, x + 10, x + 20, x + 30 };
		int[] yy = { y + 20, y + 20, y, y + 30, y };
		g.setColor(farbe);
		g.fillPolygon(xx, yy, 5);

	}
}