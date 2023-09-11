import java.net.*;
import java.io.*;
import java.sql.*;

class ClientThread extends Thread {
	Socket socket;
	InputStreamReader in;
	OutputStreamWriter out;
	Statement stmt;
	
	static Object monitor = new Object();

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	void datenbankStarten() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/buchladen?serverTimezone=UTC&useSSL=false", "root", "root");
		stmt = conn.createStatement();
	}

	@Override
	public void run() {
		try {
			in = new InputStreamReader(socket.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			System.out.println("Client-Thread gestartet");
			datenbankStarten();
			buecherlisteSenden();
			int zeichen;
			char c;
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case 'S':
					stornieren();
					break;
				case 'B':
					bestellen();
					break;
				default:
					System.out.println("Ungültiges Kommando empfangen: " + c);
				}
			}
			socket.close();
		} catch (Exception e) {
			System.out.println("Exception vom Client erhalten: " + e.getMessage());
			e.printStackTrace();
		}
	}

	void stornieren() throws Exception {
		int zeichen;
		String kundennr = "";
		int anzahl = 0;
		String cmdSQL;
		while ((zeichen = in.read()) != '$') {
			kundennr += (char) zeichen;
		}
		String isbn = "";
		while ((zeichen = in.read()) != '§') {
			isbn += (char) zeichen;
		}
		// Bestellung löschen und alten Wert für vorhanden wieder herstellen
		cmdSQL = "SELECT anzahl FROM bestellung WHERE kundennr='" + kundennr + "' AND isbn='" + isbn + "'";
		System.out.println(cmdSQL);
		ResultSet res = stmt.executeQuery(cmdSQL);
		while (res.next()) {
			anzahl += res.getInt("anzahl");
		}
		if (anzahl == 0) {
			out.write('F');
			out.flush();
			System.out.println("Keine Bestellung vorhanden. Senden: F");
		} else {
			cmdSQL = "DELETE FROM bestellung WHERE kundennr='" + kundennr + "' AND isbn='" + isbn + "'";
			System.out.println(cmdSQL);
			int erfolg = stmt.executeUpdate(cmdSQL);
			synchronized (monitor) {
				cmdSQL = "SELECT bestand FROM buch WHERE isbn='" + isbn + "'";
				System.out.println(cmdSQL);
				res = stmt.executeQuery(cmdSQL);
				int bestand = 0;
				if (res.next()) {
					bestand = res.getInt("bestand");
				}
				cmdSQL = "UPDATE buch SET bestand=" + (bestand + anzahl) + " WHERE isbn='" + isbn + "'";
				System.out.println(cmdSQL);
				erfolg = stmt.executeUpdate(cmdSQL);
			}
			out.write('S');
			out.flush();
			System.out.println("Stornierung ausgeführt. Senden: S");
		}
	}

	void bestellen() throws Exception {
		int zeichen;
		String kundennr = "";
		String isbn = "";
		String anzahl = "";
		String cmdSQL;
		while ((zeichen = in.read()) != '$') {
			kundennr += (char) zeichen;
		}
		while ((zeichen = in.read()) != '§') {
			isbn += (char) zeichen;
		}
		int zahl = 0;
		while ((zeichen = in.read()) != '%') {
			anzahl += (char) zeichen;
		}
		try {
			zahl = Integer.parseInt(anzahl);
		} catch (Exception e) { // nichts
		}
		// Überprüfen, ob noch ausreichend Bücher vorhanden sind
		int vorhanden = 0;
		synchronized (monitor) {
			cmdSQL = "SELECT bestand FROM buch WHERE isbn='" + isbn + "'";
			System.out.println(cmdSQL);
			ResultSet res = stmt.executeQuery(cmdSQL);
			if (res.next()) {
				vorhanden = res.getInt("bestand");
			}
			if (vorhanden >= zahl) {
				cmdSQL = "UPDATE buch SET bestand=" + (vorhanden - zahl) + " WHERE isbn='" + isbn + "'";
				System.out.println(cmdSQL);
				int erfolg = stmt.executeUpdate(cmdSQL);
			}
		}
		if (vorhanden < zahl) {
			String text = "N" + vorhanden + "$";
			out.write(text);
			out.flush();
			System.out.println("Es sind nicht ausreichen Bücher vorhanden. Senden: " + text);
		} else {
			cmdSQL = "INSERT INTO bestellung VALUES (NULL, "+ kundennr + ", '" + isbn + "', " + anzahl + ")";
			System.out.println(cmdSQL);
			int erfolg = stmt.executeUpdate(cmdSQL);
			out.write('J');
			out.flush();
			System.out.println("Bestellung vorgenommen. Senden: J");
		}
	}

	void buecherlisteSenden() throws Exception {
		String cmdSQL;
		System.out.println("Bücherliste senden:");
		cmdSQL = "SELECT * FROM buch ORDER BY titel";
		System.out.println(cmdSQL);
		ResultSet rs = stmt.executeQuery(cmdSQL);
		out.write('L');
		//out.flush();
		if (rs.next()) {
			String zeile = rs.getString("isbn") + "   " + rs.getString("titel")
					+ " (" + rs.getString("autor") + ")";
			out.write(zeile);
			//out.flush();
			System.out.println("L" + zeile);
		}
		while (rs.next()) {
			String zeile = "§" + rs.getString("isbn") + "   "
					+ rs.getString("titel") + " (" + rs.getString("autor")
					+ ")";
			out.write(zeile);
			//out.flush();
			System.out.println(zeile);
		}
		out.write('$');
		out.flush();
		System.out.println("$");
	}

}
