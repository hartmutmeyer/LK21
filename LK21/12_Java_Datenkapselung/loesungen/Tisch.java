import java.awt.*;

public class Tisch {
	private int x, y;          // linke obere Ecke
	private int hoehe = 50;
	private int breite = 100;  // Wichtig: Die Breite muss immer das Doppelte der Höhe sein!

	public Tisch(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setHoehe(int h) {
		hoehe = h;
		breite = 2 * h;
	}

	public void zeichnen(Graphics g) {
		g.fillRect(x, y, breite, 10);
		g.fillRect(x + 5, y, 10, hoehe);
		g.fillRect(x + breite - 15, y, 10, hoehe);
	}
}
