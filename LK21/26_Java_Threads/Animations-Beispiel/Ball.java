package java_27_Threads.doubleBuffering;
import java.awt.*;

public class Ball {
	int x;
	int y;
	int durchmesser;
	Color farbe;
	boolean gefuellt;
	int vy = 0;
	int vx = 0;

	public Ball(int xpos, int ypos, int d, Color farbe, boolean gefuellt) {
		x = xpos;
		y = ypos;
		durchmesser = d;
		this.farbe = farbe;
		this.gefuellt = gefuellt;
	}

	public Ball(int xpos, int ypos, int d, int vx, Color farbe, boolean gefuellt) {
		x = xpos;
		y = ypos;
		this.vx = vx;
		durchmesser = d;
		this.farbe = farbe;
		this.gefuellt = gefuellt;
	}

	public void zeichnen(Graphics g) {
		if (!((x < (500 - durchmesser)) && (x > 0))) {
			vx *= -1;
		}
		if ((vy == 0) && !(y < (500 - durchmesser)) && (x % 2 == 0)) {
			if (vx > 0) {
				vx--;
			}
			if (vx < 0) {
				vx++;
			}
		}
		if (!(y < (500 - durchmesser)) && (vy > 0)) {
			vy *= -1;
		}
		if ((y < (500 - durchmesser)) || (vy != 0)) {
			vy++;
		}
		x += vx;
		y += vy;
		g.setColor(farbe);
		if (gefuellt == true) {
			g.fillOval(x, y, durchmesser, durchmesser);
		} else {
			g.drawOval(x, y, durchmesser, durchmesser);
		}
	}
}