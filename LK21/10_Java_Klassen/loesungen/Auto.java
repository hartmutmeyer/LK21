import java.awt.*;
import java.awt.event.*;
import hilfe.*;
import java.util.*;

public class Auto {
	private int x = 10;
	private int y;
	private int speed;
	private Color farbe;
	private static Random zufallsgenerator = new Random();
	private int zustand = 0;
	private static int zaehler = 0;
	private int myNr;
	public static int sieger = 0;
	private int runde = 0;

	public Auto(int y, Color farbe) {
		this.y = y;
		this.farbe = farbe;
		speed = zufallsgenerator.nextInt(10) + 5;
		zaehler++;
		myNr = zaehler;
	}

	public void zeichnen(Graphics g) {
		if (runde < 3) {
			g.setColor(farbe);
			g.fillRect(x + 30, y, 50, 20);
			g.fillRect(x + 10, y + 20, 90, 20);
			g.setColor(Color.YELLOW);
			g.fillRect(x + 40, y + 10, 10, 10);
			g.fillRect(x + 60, y + 10, 10, 10);
			g.setColor(Color.BLACK);
			g.fillRect(x, y + 30, 10, 10);
			g.fillOval(x + 20, y + 30, 20, 20);
			g.fillOval(x + 70, y + 30, 20, 20);
			g.drawString("" + myNr, x + 50, y + 35);

			zustand++;
			if (zustand == 4)
				zustand = 0;
			g.setColor(Color.GRAY);
			if (zustand >= 1) {
				g.fillOval(x - 20, y + 30, 10, 10);
			}
			if (zustand >= 2) {
				g.fillOval(x - 40, y + 30, 10, 10);
			}
			if (zustand == 3) {
				g.fillOval(x - 60, y + 30, 10, 10);
			}
			x = x + speed;
			if (x > 800) {
				x = -100;
				runde++;
				if (runde == 3) {
					if (sieger == 0) {
						sieger = myNr;
					}
				}
			}
		}
	}
}
