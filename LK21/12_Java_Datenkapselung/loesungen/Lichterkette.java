import java.awt.Color;
import java.awt.Graphics;

public class Lichterkette {
	private Lampe lampeRot = new Lampe(0, 0, Color.RED);
	private Lampe lampeBlau = new Lampe(0, 0, Color.BLUE);
	private Lampe lampeGelb = new Lampe(0, 0, Color.YELLOW);
	private Lampe lampeGruen = new Lampe(0, 0, Color.GREEN);
	private Lampe lampeCyan = new Lampe(0, 0, Color.CYAN);
	private int zustand = 0;

	public Lichterkette(int x, int y) {
		lampeRot.koord(x, y);
		lampeBlau.koord(x + 95, y + 5);
		lampeGelb.koord(x + 190, y - 5);
		lampeGruen.koord(x + 285, y);
		lampeCyan.koord(x + 380, y + 10);
	}

	public void an() {
		lampeRot.an();
		lampeBlau.an();
		lampeGelb.an();
		lampeGruen.an();
		lampeCyan.an();
		zustand = 0;
	}

	public void aus() {
		lampeRot.aus();
		lampeBlau.aus();
		lampeGelb.aus();
		lampeGruen.aus();
		lampeCyan.aus();
		zustand = 2;
	}

	public void zustand(int f) {
		if (f > 2 || f < 0) {
			zustand = 0;
		} else {
			zustand = f;
		}
	}

	public void zeichnen(Graphics g) {
		zustand++;
		switch (zustand % 3) {
		case 0: 
			lampeRot.an();
			lampeBlau.aus();
			lampeGelb.an();
			lampeGruen.aus();
			lampeCyan.an();
			break;
		case 1:
			lampeRot.aus();
			lampeBlau.an();
			lampeGelb.aus();
			lampeGruen.an();
			lampeCyan.aus();
			break;
		case 2:
			aus();
		}	
		lampeRot.zeichnen(g);
		lampeBlau.zeichnen(g);
		lampeGelb.zeichnen(g);
		lampeGruen.zeichnen(g);
		lampeCyan.zeichnen(g);
	}

}