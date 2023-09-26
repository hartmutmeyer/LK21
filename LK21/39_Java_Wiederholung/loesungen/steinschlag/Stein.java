import java.awt.Graphics;

public class Stein {
	private int x;
	private int y;
	private int vy;
	private Mann mann;

	public Stein(int x, int vy, Mann mann) {
		this.x = x;
		y = 0;
		this.vy = vy;
		this.mann = mann;
	}
	
	public boolean zeichnen(Graphics g) {
		g.fillRect(x, y, 10, 10);
		y += vy;
		if (y >= 500) {
			y = 0;
		}
		if (y >= 430 && x > mann.getX() - 10 && x < mann.getX() + 40) {
			return true;       // Kollision!
		} else {
			return false;
		}
	}

}
