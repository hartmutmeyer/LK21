import java.awt.*;
import java.awt.event.*;
import hilfe.*;
import java.util.*;

public class Fisch {
	private Meer meer;
	private int bildNr;
	private int x, y, speedX, speedY;

	public Fisch(Meer meer) {
		this.meer = meer;
		bildNr = meer.zufall.nextInt(3);
		x = meer.zufall.nextInt(meer.WIDTH);
		y = meer.zufall.nextInt(meer.HEIGHT);
		speedX = meer.zufall.nextInt(5) + 1;
		speedY = meer.zufall.nextInt(5) - 2; // -2 bis +2
		if (bildNr == 2) {
			speedX = -speedX;
		}
	}

	public void zeichnen(Graphics g) {
		g.drawImage(meer.bild[bildNr], x, y, meer);
		x = x + speedX;
		y = y + speedY;
		if (bildNr < 2) {
			if (x > meer.WIDTH) {
				x = -100;
				y = meer.zufall.nextInt(meer.HEIGHT);
				speedX = meer.zufall.nextInt(5) + 1;
				speedY = meer.zufall.nextInt(5) - 2; // -2 bis +2
			}
		} else {
			if (x + 100 < 0) {
				x = meer.WIDTH;
				y = meer.zufall.nextInt(meer.HEIGHT);
				speedX = -(meer.zufall.nextInt(5) + 1);
				speedY = meer.zufall.nextInt(5) - 2; // -2 bis +2
			}
		}
	}
}
