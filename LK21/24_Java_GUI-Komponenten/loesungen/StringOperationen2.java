public class StrinOperationen2 {

	public static void main(String[] args) {
		// Aufgabe 3a
		String s1 = "hallo";
		String s2 = " HALLO ";
		/* Abschneiden der Leerzeichen von String s2 */
		s2 = s2.trim();
		/* Vergleich der Strings ohne Berücksichtigung von Groß- und
		Kleinschreibung */
		if ( s1.equalsIgnoreCase(s2)) {
			System.out.println("Die Strings unterscheiden sich höchstens in Groß- und Kleinschreibung.");
		} else {
			System.out.println("Die Strings sind nicht gleich.");
		}
		// Aufgabe 3b
		int zahl1 = 11;
		int zahl2 = 22;
		System.out.println("" + zahl1 + zahl2);
	}
}
