import java.awt.*;

public class Ampel {
	private Lampe rot, gruen, gelb;
	private int xPos, yPos;
	private final static int GRUEN = 0;
	private final static int GELB = 1;
	private final static int ROT = 2;
	private final static int GELBROT = 3;
	private int zustand = GELB;

	public Ampel(int x, int y) {
		xPos = x;
		yPos = y;
		rot = new Lampe(xPos + 10, yPos + 10, Color.RED);
		gelb = new Lampe(xPos + 10, yPos + 50, Color.YELLOW);
		gruen = new Lampe(xPos + 10, yPos + 90, Color.GREEN);
		rot.aus();
		gelb.aus();
		gruen.aus();
	}

	public void umschalten() {
		switch (zustand) {
		case GRUEN:
			zustand = GELB;
			rot.aus();
			gelb.an();
			gruen.aus();
			break;
		case GELB:
			zustand = ROT;
			rot.an();
			gelb.aus();
			gruen.aus();
			break;
		case ROT:
			zustand = GELBROT;
			rot.an();
			gelb.an();
			gruen.aus();
			break;
		case GELBROT:
			zustand = GRUEN;
			rot.aus();
			gelb.aus();
			gruen.an();
		}
	}

	void zeichnen(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xPos, yPos, 50, 130);
		rot.zeichnen(g);
		gelb.zeichnen(g);
		gruen.zeichnen(g);
	}
}