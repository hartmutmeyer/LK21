import java.awt.*;
import java.awt.event.*;

public class Streifen implements KeyListener {
	private StreifenAnwendung f;
	private int x = 100;
	private int y;
	private int hoehe = 30;
	private int breite = 30;
	private char plus;
	private char minus;

	public Streifen(StreifenAnwendung f, int y, char p, char m) {
		this.f = f;
		this.y = y;
		plus = p;
		minus = m;
		f.addKeyListener(this);
	}

	public void zeichnen(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, breite, hoehe);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == plus && breite < 350) {
			breite += 10;
			f.repaint();
		}
		if (c == minus && breite > 10) {
			breite -= 10;
			f.repaint();
		}
	}
}
