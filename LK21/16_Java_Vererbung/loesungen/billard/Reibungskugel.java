package vererbung.billard;

import java.awt.*;

public class Reibungskugel extends Kugel {
	// immer wenn sich die Bewegungsrichtung ändert -> Geschwindigkeitsverlust
	// erst 1 mal aussetzen, dann zweimal aussetzen, ...
	protected int aussetzenAnzahl;
	protected int aussetzenCounter;

	public Reibungskugel(Color c, int x, int y, int dX, int dY) {
		super(c, x, y, dX, dY);
	}

	@Override
	public boolean bewegen(int fensterBreite, int fensterHoehe) {
		boolean change = false;
		if (aussetzenCounter == 0) {
			change = super.bewegen(fensterBreite, fensterHoehe);
			if (change)
				aussetzenAnzahl++;
		} // sonst: aussetzen

		if (aussetzenAnzahl > 0)
			aussetzenCounter = (aussetzenCounter + 1) % aussetzenAnzahl;
		return change;
	}
}
