import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Karo2 extends Karo {
	public Karo2(int x, int y) {
		super(x, y);
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.GREEN);
		g.fillOval(x + 30, y - 20, 20, 20);
		g.fillOval(x + 30, y + 80, 20, 20);
	}
}