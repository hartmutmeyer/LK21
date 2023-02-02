public class ExceptionBsp {

	public static void main(final String[] args) {
		try {
			for (int i = 5; i > -5; i--) {
				System.out.println("10 durch " + i + " = " + Formeln.division(10, i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
