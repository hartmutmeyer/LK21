import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JOptionPane;

import hilfe.*;

public class Zahlenlisten extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Random zufall = new Random();
	private int[] liste;
	
	public Zahlenlisten(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		int max = zufall.nextInt(10) + 1;
		liste = new int[max];
		for (int i = 0; i < liste.length; i++) {
			liste[i] = zufall.nextInt(10) + 1;
		}
		JOptionPane.showMessageDialog(this, "Die Größte Zahl ist: "
				+ maximum(liste));
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		ausgabe(g, liste, 50);
	}

	public void ausgabe(Graphics g, int[] feld, int y) {
		for (int i = 0; i < feld.length; i++) {
			g.drawString("" + feld[i], 50 + i * 30, y);
		}
	}

	public int maximum(int[] feld) {
		int max = feld[0];
		for (int i = 1; i < feld.length; i++) {
			if (feld[i] > max) {
				max = feld[i];
			}
		}
		return max;
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zahlenlisten anwendung = new Zahlenlisten("Zahlenlisten");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}