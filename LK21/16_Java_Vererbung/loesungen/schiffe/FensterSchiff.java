import java.awt.Color;
import java.awt.Graphics;

public class FensterSchiff extends Schiff {

	public FensterSchiff(Color f, int y, int speed) {
		super(f, y, speed);
	}

	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.YELLOW);
		g.fillOval(xPos + 40, yPos + 10, 10, 10);
		g.fillOval(xPos + 60, yPos + 10, 10, 10);
	}
}
