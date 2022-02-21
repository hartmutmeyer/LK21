class Demo {
	public String name = "Demo";
	public double gewicht = 55.5;
	public int groesse;
	public static int alter;

	public Demo(String name, double gewicht) {
		this.name = name;
		gewicht = 87.2;
		alter = 50;
	}

	public Demo(String name, int a) {
		this.name = name;
		groesse = 183;
		alter = a;
	}

	public void drucken() {
		// Ausgabe der Daten eines Objektes auf die Konsole
		System.out.println("Name: " + name);
		System.out.println("Gewicht: " + gewicht);
		System.out.println("Groesse: " + groesse);
		System.out.println("Alter: " + alter);
		System.out.println("   ---");
	}

	public static void main(final String[] args) {
		Demo a = new Demo("Mona", 51.3);
		Demo b;
		Demo c = a;
		Demo d = new Demo("Simson", 19);
		c.gewicht = 45.0;
		a.drucken();
		c.drucken();
		d.drucken();
	}
}
