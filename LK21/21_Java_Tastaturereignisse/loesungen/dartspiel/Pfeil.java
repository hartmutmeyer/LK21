import java.awt.Graphics;

public class Pfeil {
	private int x;
	private int y;
	private int xStart;
	private int yStart;
	private int speedX;
	private int speedY;

	public Pfeil(int x, int y) {
		this.x = x;
		this.y = y;
		xStart = x;
		yStart = y;
	}

	public void zeichnen(Graphics g) {
		g.drawLine(x, y, x + 20, y - 5);
		g.fillOval(x + 20, y - 7, 4, 4);
	}
	
	public void bewegen() {
		if (!(x > 395 && (y > 155 && y < 275))) {
			x += speedX;
		}
		y += speedY;
	}
	
	public void fallenLassen() {
		speedY = 2;
	}
	
	public void schiessen() {
		speedX = 5;
		speedY = 0;
	}
	
	public void reset() {
		x = xStart;
		y = yStart;
		speedX = 0;
		speedY = 0;
	}
}
