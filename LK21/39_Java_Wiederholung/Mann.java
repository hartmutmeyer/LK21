import java.awt.*;

public class Mann {
	private int x;
	private int y;

	public Mann(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		// Kopf
		g.setColor(Color.ORANGE);
		g.fillOval(x + 10, y, 20, 20);
		// Bauch
		g.setColor(Color.BLUE);
		g.fillOval(x + 10, y + 20, 20, 30);
		// Arme
		g.drawLine(x, y + 40, x + 20, y + 20);
		g.drawLine(x + 20, y + 20, x + 40, y + 40);
		// Beine
		g.drawLine(x + 20, y + 40, x + 10, y + 70);
		g.drawLine(x + 20, y + 40, x + 30, y + 70);
	}
}
