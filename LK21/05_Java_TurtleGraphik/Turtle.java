import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class Turtle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double x, y;
	private int winkel = 180; // Blick nach oben
	private double bogenmass;

	public Turtle(int xStart, int yStart) {
		setBackground(Color.WHITE);
		x = xStart;
		y = yStart;
		bogenmass = Math.PI * winkel / 180;
	}

	public void vor(double strecke) {
		Graphics g = getGraphics();
		double dx = strecke * Math.sin(bogenmass);
		double dy = strecke * Math.cos(bogenmass);
		double xEnde = x + dx;
		double yEnde = y + dy;
		g.drawLine((int) Math.round(x), (int) Math.round(y),
				(int) Math.round(xEnde), (int) Math.round(yEnde));
		x = xEnde;
		y = yEnde;
	}

	public void drehen(int plusWinkel) {
		winkel += plusWinkel;
		while (winkel >= 360)
			winkel -= 360;
		while (winkel < 0)
			winkel += 360;
		bogenmass = Math.PI * winkel / 180;
	}

	public void neuStart(int neuX, int neuY, int neuWinkel) {
		x = neuX;
		y = neuY;
		winkel = neuWinkel;
		bogenmass = Math.PI * winkel / 180;
	}

	public void neuStart(int neuX, int neuY) {
		x = neuX;
		y = neuY;
		winkel = 180; // Blick nach oben
		bogenmass = Math.PI * winkel / 180;
	}

	public void loeschen() {
		repaint();
	}

	int getXpos() {
		return (int) Math.round(x);
	}

	int getYpos() {
		return (int) Math.round(y);
	}

	int getWinkel() {
		/*
		 * Umrechnung: Blick nach oben ist 0 Grad (statt 180 wie für die
		 * interne Rechnung)
		 */
		if (winkel >= 180)
			return winkel - 180;
		else
			return winkel + 180;
	}
}
