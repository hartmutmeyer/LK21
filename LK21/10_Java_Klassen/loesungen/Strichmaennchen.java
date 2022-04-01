// von Jens Altrock

import java.awt.Color;
import java.awt.Graphics;

public class Strichmaennchen {
	private int x;
	private int y;
	private int geschw;
	private Color kleidung;
	private Color gesicht = new Color(237, 150, 18);
	private String namen;

	public Strichmaennchen(int x, int y, int g, Color f, String namen) {
		this.x = x;
		this.y = y;
		geschw = g;
		kleidung = f;
		this.namen = namen;
	}

	public void stehen(Graphics g, boolean grinsen, boolean turbo) {
		if (geschw >= 40) {
			grinsen = true;
		}
		g.setColor(gesicht);
		g.fillOval(x + 28, y + 12, 6, 6);
		g.fillOval(x, y, 30, 30);
		g.setColor(kleidung);
		g.fillOval(x, y + 30, 30, 60);
		g.setColor(Color.BLACK);
		g.drawLine(x + 15, y + 48, x + 15, y + 58);
		g.drawLine(x + 15, y + 58, x + 35, y + 58);
		if (grinsen) {
			g.drawLine(x + 15, y + 20, x + 25, y + 25);
		} else {
			g.drawLine(x + 15, y + 22, x + 28, y + 22);
		}
		g.fillOval(x + 18, y + 7, 5, 5);
		g.drawLine(x + 15, y + 90, x + 15, y + 130);
		g.drawLine(x + 15, y + 130, x + 22, y + 130);
		g.drawString(namen, x + 40, y + 5);
		x += geschw;
		if (x >= 400) {
			x = -80;
			y += 130;
		}
		if (turbo) {
			geschw++;
		}
		if (y >= 500) {
			y = 1;
		}
		if (y <= -90) {
			y = 518;
			x = 10;
		}
	}

	public void gehen(Graphics g, boolean grinsen, boolean turbo) {
		if (geschw >= 40) {
			grinsen = true;
		}
		g.setColor(gesicht);
		g.fillOval(x + 28, y + 12, 6, 6);
		g.fillOval(x, y, 30, 30);
		g.setColor(kleidung);
		g.fillOval(x, y + 30, 30, 60);
		g.setColor(Color.BLACK);
		g.drawLine(x + 15, y + 48, x + 15, y + 58);
		g.drawLine(x + 15, y + 58, x + 35, y + 58);
		if (grinsen) {
			g.drawLine(x + 15, y + 20, x + 25, y + 25);
		} else {
			g.drawLine(x + 15, y + 22, x + 28, y + 22);
		}
		g.fillOval(x + 18, y + 7, 5, 5);
		g.drawLine(x + 15, y + 90, x + 15, y + 130);
		g.drawLine(x + 15, y + 130, x + 22, y + 130);
		g.drawLine(x + 15, y + 90, x + 40, y + 130);
		g.drawLine(x + 40, y + 130, x + 49, y + 123);
		g.drawString(namen, x + 40, y + 5);
		x += geschw;
		if (x >= 400) {
			x = -80;
			y += 130;
		}
		if (y >= 500) {
			y = 1;
		}
		if (turbo) {
			geschw++;
		}
	}

	public void treppe(Graphics g, Boolean grinsen) {
		g.setColor(gesicht);
		g.fillOval(x + 28, y + 12, 6, 6);
		g.fillOval(x, y, 30, 30);
		g.setColor(kleidung);
		g.fillOval(x, y + 30, 30, 60);
		g.setColor(Color.BLACK);
		g.drawLine(x + 15, y + 48, x + 15, y + 58);
		g.drawLine(x + 15, y + 58, x + 35, y + 58);
		if (grinsen) {
			g.drawLine(x + 15, y + 20, x + 25, y + 25);
		} else {
			g.drawLine(x + 15, y + 22, x + 28, y + 22);
		}
		g.fillOval(x + 18, y + 7, 5, 5);
		g.drawLine(x + 15, y + 90, x + 15, y + 130);
		g.drawLine(x + 15, y + 130, x + 22, y + 130);
		g.drawLine(x + 15, y + 90, x + 35, y + 80);
		g.drawLine(x + 35, y + 80, x + 35, y + 100);
		g.drawLine(x + 35, y + 100, x + 42, y + 100);
		g.drawString(namen, x + 40, y + 5);
		y -= 30;
		x += 15;
		x -= geschw;
	}
}