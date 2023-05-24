package tmpQ1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {

	private Socket s;
	private InputStreamReader in;
	private OutputStreamWriter out;
	int ergebnis = 0;

	public ClientThread(Socket s) {
		this.s = s;
		try {
			in = new InputStreamReader(s.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int zeichen;
		char c;
		try {
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case '+':
					addieren();
					break;
				case '-':
					subtrahieren();
					break;
				case '!':
					ergebnisAusgeben();
					break;
				default:
					System.out.println("DAS SOLLTE NICHT PASSIEREN");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ergebnisAusgeben() throws IOException {
		int zeichen;
		if ((zeichen = in.read()) == '$') {
			try {
				Random zufall = new Random();
				if (zufall.nextInt(2) == 0) {
					out.write("#" + ergebnis + "$");
					out.flush();
				} else {
					out.write("%GAME OVER$");
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Direkt nach '!' ein anderes Zeichen als '$' empfangen!!");
			;
		}
	}

	private void addieren() {
		int zeichen;
		char c;
		String summand = "";
		try {
			while ((zeichen = in.read()) != '$') {
				summand += (char) zeichen;
			}
			ergebnis += Integer.parseInt(summand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void subtrahieren() {
		int zeichen;
		char c;
		String minusDing = "";
		try {
			while ((zeichen = in.read()) != '$') {
				minusDing += (char) zeichen;
			}
			ergebnis -= Integer.parseInt(minusDing);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
