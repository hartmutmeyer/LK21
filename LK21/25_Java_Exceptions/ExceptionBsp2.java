public class ExceptionBsp2 {

	public static void test1() throws DurchNullException {
		for (int i = 0; i < 10; i++) {
			System.out.println("10 durch " + i + " = " + Formeln.division(10, i));
		}
	}

	public static void test2() throws DurchNullException {
		try {
			System.out.println("" + Formeln.division(4, Integer.parseInt("Hallo")));
		} catch (DurchNullException exc) {
			// diese Exception soll an anderer Stelle weiter bearbeitet werden
			throw exc; // Objekt exc existiert bereits!
		} catch (Exception exc) {
			System.out.println("Unerwarteter Fehler: " + exc.toString());
		}
	}

	public static void main(final String[] args) {
		try {
			test1();
			test2();
		} catch (DurchNullException e) {
			System.out.println(e.getMessage());
		}
	}
}
