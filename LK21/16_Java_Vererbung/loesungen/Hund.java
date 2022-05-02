import java.awt.Graphics;

public class Hund extends Tier {
	
	// Aufgabe (d)
	public Hund() {
		super(false);
	}
	
	// Aufgabe (c)
	public Hund(boolean weiblich) {
		super(weiblich);
	}
	
	public void zeichnen (Graphics g, int x, int y) {
		// Nur männliche Hunde sollen gezeichnet werden. Weibliche Hunde
		// verstecken sich. Rufe die Methode zeichnen aus der Superklasse
		// Tier auf, wenn der Hund männlich ist.
		if (getWeiblich() == false) {
			super.zeichnen(g, x, y);
		}
	}
}
