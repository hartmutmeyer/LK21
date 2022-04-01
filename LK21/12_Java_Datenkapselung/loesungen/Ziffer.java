import java.awt.*;

public class Ziffer {
	private int xPos, yPos;
	private int zahl = 0;
	private int maxValue;

	Ziffer(int x, int y, int maxValue) {
		xPos = x;
		yPos = y;
		this.maxValue = maxValue;
	}

	void zeichnen(Graphics g) {
		g.drawString(Integer.toString(zahl), xPos, yPos);
	}

	boolean hochzaehlen() {
	// gibt zurück, ob die "10" erreicht ist
		zahl = zahl + 1;
		if (zahl > maxValue) {
			zahl = 0;
			return true;
		} else {
			return false;
		}
	}

}
