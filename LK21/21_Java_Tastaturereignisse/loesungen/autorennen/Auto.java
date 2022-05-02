import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Auto implements KeyListener {
	private boolean[] keys = new boolean[1024];
	int x, y;
	int v;
	int spieler;
	Color farbe;
	
	public Auto(int spieler, Color farbe, Autorennen fenster) {
		this.spieler = spieler;
		this.farbe = farbe;
		fenster.addKeyListener(this);
		y = Autorennen.HEIGHT - 100;
		if (spieler == 1) {
			x = 170;
		} else {
			x = 570;
		}
	}
	
	private void bewegen() {
		if (spieler == 1) {
			if (isKeyPressed(KeyEvent.VK_UP)) {
				v++;
				if (v > 10) {
					v = 10;
				}
			}
			if (isKeyPressed(KeyEvent.VK_DOWN)) {
				v--;
				if (v < 0) {
					v = 0;
				}
			}
			if (isKeyPressed(KeyEvent.VK_LEFT)) {
				x -= 5;
			}
			if (isKeyPressed(KeyEvent.VK_RIGHT)) {
				x += 5;
			}
		} else {
			if (isKeyPressed(KeyEvent.VK_NUMPAD5)) {
				v++;
				if (v > 10) {
					v = 10;
				}
			}
			if (isKeyPressed(KeyEvent.VK_NUMPAD2)) {
				v--;
				if (v < 0) {
					v = 0;
				}
			}
			if (isKeyPressed(KeyEvent.VK_NUMPAD1)) {
				x -= 5;
			}
			if (isKeyPressed(KeyEvent.VK_NUMPAD3)) {
				x += 5;
			}
		}
		y -= v;
	}
	
	void zeichnen(Graphics g) {
		g.setColor(farbe);
		g.fillOval(x + 10, y + 0, 40, 40);
		g.fillRect(x + 10, y + 20, 40, 60);
		g.setColor(Color.BLACK);
		g.fillRect(x + 0, y + 20, 10, 20);
		g.fillRect(x + 50, y + 20, 10, 20);
		g.fillRect(x + 0, y + 60, 10, 20);
		g.fillRect(x + 50, y + 60, 10, 20);
		bewegen();
	}
	
	private boolean isKeyPressed(int key) {
		return keys[key];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
