import java.awt.*;

public class Schiff {
	Image bild;
	Frame f;
	int x, y;
	int yStart;
	boolean up = true;

	public Schiff(int x, int y, Frame f) {
		this.x = x;
		yStart = y;
		this.y = y;
		this.f = f;

		bild = f.getToolkit().getImage(getClass().getResource("schiff.gif"));
		MediaTracker mt = new MediaTracker(f);
		mt.addImage(bild, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
	}

	public void zeichnen(Graphics g) {
		g.drawImage(bild, x, y, f);
		x = x + 1;
		if (up) {
			y = y - 1;
			if (y < yStart - 10) {
				up = false;
			}
		} else {
			y = y + 1;
			if (y > yStart) {
				up = true;
			}
		}
		if (x > f.getWidth()) {
			x = -150;
		}
	}
}
