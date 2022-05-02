import java.awt.Color;
import java.awt.Graphics;

import hilfe.HZeichnen;

public class Drache {
	
	protected int x;
	protected int y;
	private Color farbe;
	
	public Drache(int x, int y, Color farbe) {
		this.x = x;
		this.y = y;
		this.farbe = farbe;
	}

	public void zeichnen(Graphics g) {
		g.setColor(farbe);
		HZeichnen.fillDreieck(g, x + 40, y, x, y + 40, x + 80, y + 40);
		HZeichnen.fillDreieck(g, x + 40, y + 120, x, y + 40, x + 80, y + 40);		
	}
}
