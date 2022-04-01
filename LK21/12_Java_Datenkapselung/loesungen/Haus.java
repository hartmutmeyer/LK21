import java.awt.Graphics;

public class Haus {

	private int x, y;             // x und y Position der linken oberen Ecke des Hauses
	private int hoehe;            // Höhe des Hauses
	
	public Haus(int x, int y, int hoehe) {
		this.x = x;
		this.y = y;
		this.hoehe = hoehe;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public void zeichnen(Graphics g) {
		g.drawRect(x, y - hoehe, hoehe, hoehe);
		g.drawLine(x, y - hoehe, x + hoehe/2, (int) (y - (hoehe * 1.5)));
		g.drawLine(x + hoehe/2, (int) (y - (hoehe * 1.5)), x + hoehe, y - hoehe);
	}
}
