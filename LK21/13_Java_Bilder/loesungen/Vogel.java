import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Vogel {
	private int x;
	private int y;
	private int speed;
	boolean fluegel = true;

	public Vogel() {
		x = 600;
		y = 500;
		speed = 15;
	}

	public void zeichnen(Graphics g) {
		Himmel himmel = (Himmel) Frame.getFrames()[0];
		if (fluegel) {
			g.drawImage(himmel.vogel1, x, y, himmel);
		} else {
			g.drawImage(himmel.vogel2, x, y, himmel);
		}
	}

	public void bewegen() {
		x = x - speed;
		if (x < -70) {
			x = 800;
		}
		if (fluegel) {
			fluegel = false;
		} else {
			fluegel = true;
		}
		y = y - 3;
	}

}
