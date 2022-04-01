package ampel;

import java.awt.*;

public class Ampel {
	Lampe rot, grün, gelb;
	int xPos, yPos;
	int state = 3;

	public Ampel(int x, int y) {
		xPos = x;
		yPos = y;
		rot = new Lampe(Color.RED, xPos + 5, yPos + 5);
		gelb = new Lampe(Color.YELLOW, xPos + 5, yPos + 27);
		grün = new Lampe(Color.GREEN, xPos + 5, yPos + 50);
	}

	public void umschalten() {
		state = (state + 1) % 4;
		switch (state) {
		case 0:
			rot.an();
			gelb.aus();
			break;
		case 1:
			gelb.an();
			break;
		case 2:
			rot.aus();
			gelb.aus();
			grün.an();
			break;
		case 3:
			grün.aus();
			gelb.an();
			break;
		}
	}

	void zeichnen(Graphics g) {
		g.fillRect(xPos, yPos, 30, 75);
		rot.zeichnen(g);
		gelb.zeichnen(g);
		grün.zeichnen(g);
	}
}