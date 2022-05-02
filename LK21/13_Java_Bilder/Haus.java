import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

public class Haus {
	private HausAnwendung anwendung;
	private int x, y;
	private Image hausBild;

	public Haus(int x, int y, HausAnwendung anwendung) {
		this.x = x;
		this.y = y;
		this.anwendung = anwendung;
		hausBild = anwendung.getToolkit().getImage(getClass().getResource("haus.gif"));
		MediaTracker mt = new MediaTracker(anwendung);
		mt.addImage(hausBild, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
	}

	public void zeichnen(Graphics g) {
		g.drawImage(hausBild, x, y, anwendung);
	}
}
