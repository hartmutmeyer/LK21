public class Dreieck implements Geometrie {

	@Override
	public boolean istEckig() {
		return true;
	}

	@Override
	public int anzahlEcken() {
		return 3;
	}
	
	public static void main(String[] args) {
		Dreieck dreieck = new Dreieck();
		if (dreieck.istEckig()) {
			System.out.println("Ein Dreieck hat " + dreieck.anzahlEcken() + " Ecken.");
		} else {
			System.out.println("Ein Dreieck hat keine Ecken.");
		}
	}

}
