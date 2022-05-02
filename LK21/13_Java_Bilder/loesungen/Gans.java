import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Gans {
	private int x;
	private int y;
	private int speed;
	int zustand = 0;

	public Gans(int x) {
		this.x = x;
		y = 200;
		speed = 10;
	}

	public Gans(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 20;
	}

	public Gans(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	public void zeichnen(Graphics g) {
		Himmel himmel = (Himmel) Frame.getFrames()[0];
		zustand++;
		if (zustand == 4) {
			zustand = 0;
		}
		g.drawImage(himmel.gans[zustand], x, y, himmel);
	}

	public void bewegen() {
		x = x + speed;
		if (x > 800) {
			x = -120;
		}
	}

}
