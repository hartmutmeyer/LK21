import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Wolke {
	private int x;
	private int y;

	public Wolke(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y + 20, 40, 40);
		g.fillOval(x + 30, y, 40, 40);
		g.fillOval(x + 60, y + 20, 40, 40);
		g.fillRect(x + 20, y + 20, 60, 40);
	}

}
