import java.awt.*;

public class Lampe {
	protected Color farbe;
	protected boolean isAn = false;
	protected int xPos, yPos;
	protected final int breite = 50;

	public Lampe(Color f, int x, int y) {
		farbe = f;
		xPos = x;
		yPos = y;
	}

	public boolean getAn() {
		return isAn;
	}

	public void schalten(boolean an) {
		isAn = an;
	}

	public void zeichnen(Graphics g) {
		if (isAn) {
			g.setColor(farbe);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillOval(xPos, yPos, breite, breite);
	}
}
