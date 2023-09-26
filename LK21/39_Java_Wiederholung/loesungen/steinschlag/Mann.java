import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mann implements KeyListener {
	private int x;
	private int y;
	private int vx = 0;

	public Mann(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public boolean zeichnen(Graphics g) {
		// Kopf
		g.setColor(Color.ORANGE);
		g.fillOval(x + 10, y, 20, 20);
		// Bauch
		g.setColor(Color.BLUE);
		g.fillOval(x + 10, y + 20, 20, 30);
		// Arme
		g.drawLine(x, y + 40, x + 20, y + 20);
		g.drawLine(x + 20, y + 20, x + 40, y + 40);
		// Beine
		g.drawLine(x + 20, y + 40, x + 10, y + 70);
		g.drawLine(x + 20, y + 40, x + 30, y + 70);
		
		x += vx;
		if (x < 0 || x + 40 > 500) {
			return true;				// Mann hat die Grenzen des Spielfeldes überschritten!
		} else {
			return false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			vx = 2;
			break;
		case KeyEvent.VK_L:
			vx = -2;
			break;
		case KeyEvent.VK_S:
			vx = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
