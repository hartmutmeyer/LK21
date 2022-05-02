import java.awt.*;

public class QuadratLampe extends Lampe {
	public QuadratLampe(Color f, int x, int y) {
		super(f, x, y);
	}

	public void zeichnen(Graphics g) {
		if (isAn) {
			g.setColor(farbe);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(xPos, yPos, breite, breite);
	}
}
