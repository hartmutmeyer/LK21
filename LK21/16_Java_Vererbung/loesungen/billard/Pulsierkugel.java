package vererbung.billard;

import java.awt.*;

public class Pulsierkugel extends Kugel {
	// Datenkapselung: immer wenn die breite geändert wird muss auch die
	// Variable breiteOriginal mit geändert werden!!!
	protected boolean bWachsen;
	protected int breiteOriginal;

	public Pulsierkugel(Color c, int x, int y, int dX, int dY) {
		super(c, x, y, dX, dY);
		breiteOriginal = 20;
	}

	@Override
	public boolean bewegen(int fensterBreite, int fensterHoehe) {
		boolean change;

		change = super.bewegen(fensterBreite, fensterHoehe);
		if (bWachsen) {
			breite++;
			if (breite == breiteOriginal + 5)
				bWachsen = false;
		} else { // Schrumpfen
			breite--;
			if (breite == breiteOriginal - 5)
				bWachsen = true;
		}

		return change;
	}
}
