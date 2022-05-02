import java.awt.*;
import java.awt.event.*;
import hilfe.*;
import java.util.*;

public class Stern2 extends Stern {
	public int x, y;
	static Random rnd = new Random();
	Color farbe;
	boolean an = true;
	static int nr = 0;

	public Stern2(int x, int y) {
		super(x, y);
		nr++;
		if (nr % 3 == 0) {
			an = false;
		} else {
			an = true;
		}
	}

	public void zeichnen(Graphics g) {
		if (an) {
			super.zeichnen(g);
			an = false;
		} else {
			an = true;
		}
	}
}