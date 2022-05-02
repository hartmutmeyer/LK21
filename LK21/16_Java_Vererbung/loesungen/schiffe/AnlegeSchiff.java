import java.awt.Color;
import java.awt.Graphics;

public class AnlegeSchiff extends FensterSchiff {
	private int stegPos;
	private int zaehler = 0;

	public AnlegeSchiff(Color f, int y, int speed, int stegPos) {
		super(f, y, speed);
		this.stegPos = stegPos;
	}

	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		if (xPos == stegPos) {
			speed = 0;
			zaehler++;
		}
		if (zaehler == 30) {
			speed = 2;
			zaehler = 0;
		}
	}
}
