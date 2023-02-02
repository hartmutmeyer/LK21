import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pferd extends JFrame {
	// globale Variablen
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.YELLOW;
	private static final Color FOREGROUND = Color.BLACK;
	private JPanel zeichenflaeche;
	private Image horse1, horse2;
	private boolean showHorse1 = true;
	private int x = 0;

	public Pferd(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		zeichenflaeche = new JPanel() {
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
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		horse1 = getToolkit().getImage(getClass().getResource("horse1.gif"));
		horse2 = getToolkit().getImage(getClass().getResource("horse2.gif"));
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(horse1, 1);
		mt.addImage(horse2, 2);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
		PferdTimer timer = new PferdTimer(this);
		timer.start();
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		if (showHorse1) {
			g.drawImage(horse1, x, 300, this);
		} else {
			g.drawImage(horse2, x, 300, this);
		}
		showHorse1 = !showHorse1;
		x += 10;
		if (x > 600) {
			x = -200;
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Pferd("Pferd");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}