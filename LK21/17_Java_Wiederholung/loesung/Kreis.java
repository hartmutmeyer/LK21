import java.awt.*;

class Kreis {
	private final int BREITE = 40;
	private Color farbe;
	private int x, y;
	private static int startNr = 0;
	private int farbNr = 0;

	public Kreis(Color farbe, int x, int y) {
		this.x = x;
		this.y = y;
		this.farbe = farbe;
		farbNr = startNr;
		startNr++;
		if (startNr == 3) {
			startNr = 0;
		}
	}

	public void zeichnen(Graphics g) {
		if (farbNr == 2) {
			g.setColor(Color.ORANGE);
		}
		else {
			g.setColor(farbe);
		}
		farbNr++;
		if (farbNr == 3) {
			farbNr = 0;
		}
		g.fillOval(x, y, BREITE, BREITE);
	}

}