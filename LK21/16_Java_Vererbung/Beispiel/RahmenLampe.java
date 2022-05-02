import java.awt.*;

public class RahmenLampe extends Lampe {
	public RahmenLampe(Color f, int x, int y) {
		super(f, x, y);
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.BLACK);
		g.drawOval(xPos, yPos, breite, breite);
	}
}
