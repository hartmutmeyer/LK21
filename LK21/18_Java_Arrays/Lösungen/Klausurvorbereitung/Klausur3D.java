import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import hilfe.*;

public class Klausur3D extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private int[][][] zahlen3D = new int[10][10][10];

	public Klausur3D(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		Random zufall = new Random();
		for (int x = 0; x < zahlen3D.length; x++) {
			for (int y = 0; y < zahlen3D[0].length; y++) {
				for (int z = 0; z < zahlen3D[0][0].length; z++) {
					zahlen3D[x][y][z] = zufall.nextInt(100) + 1;
				}
			}
		}

	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		
		// ACHTUNG: z = 3!
		// Es wird nur eine Ebene des Würfels dargestellt!
		for (int x = 0; x < zahlen3D.length; x++) {
			for (int y = 0; y < zahlen3D[0].length; y++) {
				g.drawString("" + zahlen3D[x][y][3], 30 + 45 * x, 30 + 45 * y);
			}
		}

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Klausur3D anwendung = new Klausur3D("Arrays");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}