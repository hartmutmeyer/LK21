public class Synchronisation {
	static int zaehler = 0;

	public static void main(final String[] args) {
		MeinThread thread_1 = new MeinThread(1);
		MeinThread thread_2 = new MeinThread(2);
		thread_1.start();
		thread_2.start();
	}
}
