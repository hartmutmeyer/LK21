import java.awt.Color;
import java.awt.Graphics;

public class Blume {
	public int x;
	public int y;
	public Color farbe;
	public static int anzahlBlumen;
	public boolean verwelkt = false;
	public int verwelktZaehler;
	
	public Blume(int x, int y) {
		this.x = x;
		this.y = y;
		switch (anzahlBlumen % 3) {
		case 0:
			this.farbe = Color.RED;
			break;
		case 1:
			this.farbe = Color.BLUE;
			break;
		case 2:
			this.farbe = Color.CYAN;
			break;
		}
		anzahlBlumen++;
	}
	
	public void zeichnen(Graphics g) {
		// Blütenstempel
		g.setColor(Color.YELLOW);
		g.fillOval(x + 20, y + 20, 20, 20);
		// Stängel
		g.setColor(Color.GREEN);
		g.fillRect(x + 29, y + 40, 3, 1000);
		// Blütenblätter zeichnen, solange sie nicht verwelkt ist
		if (!verwelkt) {
			g.setColor(farbe);
			g.fillOval(x + 20, y,      20, 20);
			g.fillOval(x + 40, y + 20, 20, 20);
			g.fillOval(x + 20, y + 40, 20, 20);
			g.fillOval(x     , y + 20, 20, 20);
		}
		// Blume soll wachsen: Bei jedem Aufruf von zeichnen() um ein Pixel
		if (y > 100) {
			y--;
		} else { // 2 Sekunden nach Erreichen der Maximalen Höhe verwelken!
			if (verwelktZaehler++ > 200) {
				verwelkt = true;
			}
		}
	}
}
