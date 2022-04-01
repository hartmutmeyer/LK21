import java.awt.*;

public class Tisch {

	private int x, y;          // linke obere Ecke
	public int hoehe = 50;
	public int breite = 100;   // Wichtig: Die Breite muss immer das Doppelte der Hoehe sein!

	public Tisch(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void zeichnen(Graphics g) {
		g.fillRect(x, y, breite, 10);
		g.fillRect(x + 5, y, 10, hoehe);
		g.fillRect(x + breite - 15, y, 10, hoehe);
	}
}
