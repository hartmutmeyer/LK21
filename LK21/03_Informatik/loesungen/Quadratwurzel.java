import java.util.Scanner;

public class Quadratwurzel {

	public static void main(String[] args) {
		double radikand;
		double akzeptierterFehler = 0.0001;
		int anzahlSchritte = 0;
		double untereGrenze = 0.0;
		double obereGrenze;
		double gerateneLösung;
		double quadrat;
		double abweichung;

		Scanner scanner = new Scanner(System.in);
		System.out.print("Gib eine positive Zahl ein: ");
		radikand = scanner.nextDouble();                  // Eingabe mit Komma statt Punkt!
		obereGrenze = Math.max(1.0, radikand);
		gerateneLösung = (untereGrenze + obereGrenze) / 2.0;
		quadrat = gerateneLösung * gerateneLösung;
		abweichung = Math.abs(quadrat - radikand);

		while (abweichung > akzeptierterFehler) {
			System.out.println("untere Grenze = " + untereGrenze + " obere Grenze = " + obereGrenze + " Antwort = " + gerateneLösung);
			anzahlSchritte++;
			if (quadrat < radikand) {                    // Die geratene Wurzel ist zu klein
				untereGrenze = gerateneLösung;
			} else {                                     // Die geratene Wurzel ist zu groß
				obereGrenze = gerateneLösung;
			}
			gerateneLösung = (obereGrenze + untereGrenze) / 2.0;
			quadrat = gerateneLösung * gerateneLösung;
			abweichung = Math.abs(quadrat - radikand);
		}

		System.out.println("Anzahl Schritte = " + anzahlSchritte);
		System.out.println(gerateneLösung + " ist die Quadratwurzel von " + radikand);
	}

}
