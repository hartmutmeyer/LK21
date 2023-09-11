import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
	private Socket socket;
	private InputStreamReader inNet;
	private OutputStreamWriter outNet;
	private static final int NORMALER_MODUS = 1;
	private static final int GEHEIM_MODUS = 2;
	private int zustand = NORMALER_MODUS;
	private static Object monitor = new Object();

	public ClientThread(Socket sock) {
		this.socket = sock;
		try {
			InputStream is = socket.getInputStream();
			inNet = new InputStreamReader(is, "UTF-8");
			outNet = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			int zeichen;
			char c;
			while ((zeichen = inNet.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case '$':
					passwortLesen();
					break;
				case '%':
					geheimModusBeenden();
					break;
				case '#':
					passwortAendern();
					break;
				default:
					if (Character.isLowerCase(c)) {
						dateiSenden(c);
					} else {
						outNet.write("Falsche Eingabe" + System.lineSeparator());
						outNet.flush();
						System.out.println("PROTOKOLLFEHLER");
					}
				}
			}
			// Die Verbindung (InputStream des Servers bzw. OutputStream des
			// Clients wurde vom Client getrennt --> in.read() == -1
			// und damit die while-Schleife oben verlassen.
			// Jetzt muss nur noch aufgeräumt werden (der Socket - und indirekt
			// damit auch die Streams - werden geschlossen).
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void passwortAendern() throws IOException {
		System.out.println("passwortAendern(): ENTRY");
		int zeichen;
		String neuesPasswort = "";
		while ((zeichen = inNet.read()) != '#') {
			neuesPasswort += (char) zeichen;
		}
		if (zustand == GEHEIM_MODUS) {
			synchronized (monitor) {
				URL url = getClass().getResource("passwort.txt"); // import java.net.URL
				System.out.println("passwortAendern(): " + url.toString());
				try (OutputStreamWriter outFile = new OutputStreamWriter(new FileOutputStream(url.getFile()),
						"UTF-8")) {
					outFile.write(neuesPasswort);
					outFile.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			outNet.write("Password geändert" + System.lineSeparator());
			outNet.flush();
		} else {
			outNet.write("Geheimmodus ist nicht aktiviert" + System.lineSeparator());
			outNet.flush();
		}
	}

	private void geheimModusBeenden() throws IOException {
		zustand = NORMALER_MODUS;
		outNet.write("Geheimmodus ausgeschaltet" + System.lineSeparator());
		outNet.flush();
	}

	private void passwortLesen() throws IOException {
		if (zustand == NORMALER_MODUS) {
			int zeichen;
			String passwortVomClientEmpfangen = "";
			while ((zeichen = inNet.read()) != '$') {
				passwortVomClientEmpfangen += (char) zeichen;
			}
			synchronized (monitor) {
				if (passwortVomClientEmpfangen.equals(dateiLesen("passwort.txt"))) {
					zustand = GEHEIM_MODUS;
					outNet.write("Geheimmodus aktiviert" + System.lineSeparator());
				} else {
					outNet.write("Falsches Passwort" + System.lineSeparator());
				}
			}
			outNet.flush();
		} else {
			System.out.println("passwortLesen(): Bereits im normalen Modus!!");
		}
	}

	private void dateiSenden(char c) {
		try {
			String dateiInhalt = dateiLesen(c + ".txt");
			switch (zustand) {
			case GEHEIM_MODUS:
				String geheimnis = "";
				int posLetztesLeerzeichen = -1;
				for (int i = 0; i < dateiInhalt.length(); i++) {
					if (Character.isSpaceChar(dateiInhalt.charAt(i))) {
						posLetztesLeerzeichen = i;
					}
					if (i == posLetztesLeerzeichen + 2) {
						geheimnis += dateiInhalt.charAt(i);
					}
				}
				outNet.write(geheimnis + System.lineSeparator());
				break;
			case NORMALER_MODUS:
				outNet.write(dateiInhalt + System.lineSeparator());
				break;
			default:
				outNet.write("FALSCHE EINGABE" + System.lineSeparator());
			}
			outNet.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String dateiLesen(String dateiName) throws IOException {
		String dateiInhalt = "";
		URL url = getClass().getResource(dateiName); // import java.net.URL
		if (url == null) {
			outNet.write("Die Datei existiert nicht." + System.lineSeparator());
			outNet.flush();
			return "";
		}
		System.out.println("dateiLesen(): " + url.toString());
		try (InputStreamReader inFile = new InputStreamReader(new FileInputStream(url.getFile()), "UTF-8")) {
			int zeichen;
			while ((zeichen = inFile.read()) != -1) {
				dateiInhalt += (char) zeichen;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dateiInhalt;
	}
}
