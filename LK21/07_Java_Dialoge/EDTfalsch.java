import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import hilfe.*;

public class EDTfalsch extends HJFrame {
	// globale Variablen
	final static int WIDTH = 500;
	final static int HEIGHT = 500;
	final static Color BACKGROUND = Color.WHITE;
	final static Color FOREGROUND = Color.BLACK;
	String name;

	public EDTfalsch(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		name = "Rumpelstilzchen";
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		        name = JOptionPane.showInputDialog("Gib deinen Namen ein:");
		    }
		});
		System.out.println("Hallo " + name + "!");
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EDTfalsch anwendung = new EDTfalsch("EDTfalsch");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}