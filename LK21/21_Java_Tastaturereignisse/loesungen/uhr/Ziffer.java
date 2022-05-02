import java.awt.*;

public class Ziffer {
	protected int xPos, yPos;
	protected int zahl = 0;
	protected int maxValue;

	Ziffer(int x, int y, int maxValue) {
		xPos = x;
		yPos = y;
		this.maxValue = maxValue;
	}

	void zeichnen(Graphics g) {
		g.drawString(Integer.toString(zahl), xPos, yPos);
	}

	boolean hochzaehlen() {
	// gibt zurück, ob die Ziffer auf 0 gesetzt wurde
		if (zahl == maxValue) {
			zahl = 0;
			return true;
		} else {
			zahl++;
			return false;
		}
	}

	void reset() {
	// Zahl zurück setzten
		zahl = 0;
	}
}
