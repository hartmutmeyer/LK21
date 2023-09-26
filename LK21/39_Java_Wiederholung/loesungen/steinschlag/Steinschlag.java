import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Steinschlag extends JFrame {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JLabel zeichenflaeche;
	private Mann mann;
	private Stein[] stein = new Stein[10];
	private Random zufall = new Random();

	public Steinschlag(final String title) {
		createGUI();
		mann = new Mann(250, 420);
		addKeyListener(mann);
		int x = 20;
		int vy;
		for (int i = 0; i < 10; i++) {
			vy = zufall.nextInt(4) + 1;
			stein[i] = new Stein(x, vy, mann);
			x += 50;
		}
		MyTimer timer = new MyTimer(20, this);
		timer.start();
	}
	
	private void createGUI() {
		setTitle("Steinschlag");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		zeichenflaeche = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				myPaint(g);
			}
		};
		zeichenflaeche.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		zeichenflaeche.setOpaque(true);
		zeichenflaeche.setBackground(BACKGROUND);
		zeichenflaeche.setForeground(FOREGROUND);
		zeichenflaeche.setDoubleBuffered(true);
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void myPaint(Graphics g) {
		if (mann.zeichnen(g)) {       // zeichnen() liefert true, wenn der Mann die Grenzen des Spielfelds übertritt
			System.out.println("Du darfst nicht aus dem Fenster laufen!");
			System.exit(0);
		}
		for (int i = 0; i < 10; i++) {
			if (stein[i].zeichnen(g)) {
				System.out.println("AUA!");
				System.exit(0);       // Mann wurde von einem Stein getroffen: AUA!
			}
		}

	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Steinschlag("Steinschlag");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}