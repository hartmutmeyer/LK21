import java.awt.Color;
import java.awt.Graphics;

public class RahmenLampe extends Lampe {

	public RahmenLampe(Color f, int x, int y) {
		super(f, x, y);
	}

	@Override
	public void zeichnen(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(xPos - 4, yPos - 4, breite + 8, breite + 8);
		super.zeichnen(g);
	}
}
