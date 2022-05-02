import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Karo3 extends Karo2 {
	static int nr;
	private int meineNr;

	public Karo3(int x, int y) {
		super(x, y);
		nr++;
		meineNr = nr;
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.BLACK);
		g.drawString("" + meineNr, x + 36, y - 5);
	}
}