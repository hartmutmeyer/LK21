import java.awt.Color;
import java.awt.Graphics;

import vererbung.drachen.Drache;

public class GesichtDrache extends Drache {

	public GesichtDrache(int x, int y, Color farbe) {
		super(x, y, farbe);
		System.out.println("Ein Gesicht-Drache wurde erzeugt");
	}

	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		g.setColor(Color.BLUE);
		g.fillOval(x + 20, y + 30, 10, 10);  // linkes Auge
		g.fillOval(x + 50, y + 30, 10, 10);  // rechtes Auge
		g.setColor(Color.RED);
		g.fillOval(x + 20,  y + 50, 40, 10); // Mund
	}
}
