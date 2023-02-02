public class DurchNullException extends Exception {

	public DurchNullException() {
		super("Man kann nicht durch 0 teilen.");
	}

	public DurchNullException(String text) {
		super(text);
	}

}
