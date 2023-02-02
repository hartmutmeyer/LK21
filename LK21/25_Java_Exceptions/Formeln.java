public class Formeln {

	public static double division(double x1, double x2) throws DurchNullException {
		if (x2 == 0) {
			DurchNullException fehler;
			fehler = new DurchNullException();
			throw fehler;
			// Oder kürzer:
			// throw new DurchNullException("Teilen durch 0 ist verboten!");
		}
		return (x1 / x2);
	}
}
