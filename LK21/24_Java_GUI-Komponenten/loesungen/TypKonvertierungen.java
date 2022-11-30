import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TypKonvertierungen {

	public static void main(String[] args) {
		// Konvertierung von char nach int
		// Im Ergebnis erhält die int Variable den numerischen UTF8-Wert der char-Variable.
		// Dazu ist kein type cast nötig.
		// Ausgabe: c = A und zahl = 65
		char c = 'A';
		int zahl = c;
		System.out.println("c = " + c + " und zahl = " + zahl);
		
		// Konvertierung von int nach char
		// Im Ergebnis erhält die char Variable den UTF8-Character, der dem Zahlenwert entspricht.
		// Dazu ist ein type cast nötig!
		// Ausgabe: zahl = 65 und c = A
		zahl = 65;
		c = (char) zahl;
		System.out.println("zahl = " + zahl + " und c = " + c);
		
		// Konvertierung von boolean nach int
		// NICHT ERLAUBT!
		// Auch nicht mit explizitem type cast.
		// Fehler: Type mismatch: cannot convert from boolean to int
		// boolean b = true;
		// zahl = b;
		
		// Konvertierung von String nach int
		// NICHT ERLAUBT!
		// Auch nicht mit explizitem type cast.
		// Fehler: Type mismatch: cannot convert from String to int
		// String text = "Hallo";
		// zahl = text;

		// Konvertierung von int nach String
		// NICHT DIREKT ERLAUBT!
		// Auch nicht mit explizitem type cast.
		// Fehler: Type mismatch: cannot convert from int to String
		// zahl = 65;
		// String text = zahl;
		// ABER MIT EINEM TRICK GEHT ES DOCH:
		// Wenn ein int-Wert an einen String angehängt wird (auch an einen leeren String), wird der
		// int-Wert als String dargestellt.
		// Ausgabe: 65
		zahl = 65;
		String text = "" + zahl;
		System.out.println(text);
		
		// Konvertierung von char nach String
		// NICHT DIREKT ERLAUBT!
		// Auch nicht mit explizitem type cast.
		// Fehler: Type mismatch: cannot convert from char to String
		// c = 'A';
		// text = c;
		// ABER MIT EINEM TRICK HEHT ES DOCH:
		// Wenn ein char-Wert an einen String angehängt wird (auch an einen leeren String), wird der
		// char-Wert als String dargestellt.
		// Ausgabe: A
		c = 'A';
		text = "" + c;
		System.out.println(text);
		
		// Konvertierung von JButton nach JLabel
		// NICHT ERLAUBT!
		// Auch nicht mit explizitem type cast.
		// Fehler: Type mismatch: cannot convert from JButton to JLabel
		// JButton b = new JButton("Drück mich");
		// JLabel l = b;
		
		// Konvertierung von JButton nach JLabel über den "Umweg" eines type cast nach Object
		// Der Java-Compiler hat daran nichts auszusetzen (das Programm lässt sich übersetzen und ausführen).
		// ABER DANN GIBT ES ZUR LAUFZEIT EINEN FEHLER!
		// Fehler zur Laufzeit: java.lang.ClassCastException: javax.swing.JButton cannot be cast to javax.swing.JTextField
		JButton b = new JButton("Drück mich");
		Object obj = (Object) b;
		JTextField tf = (JTextField) obj;
	}
}
