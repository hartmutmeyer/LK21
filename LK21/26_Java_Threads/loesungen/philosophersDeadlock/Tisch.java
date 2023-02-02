import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tisch extends JFrame {
	// globale Variablen
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JPanel zeichenflaeche;
	private Philosoph philosoph[] = new Philosoph[5];
	private Image imgEssen, imgEinStaebchen, imgAusruhen, img;
	boolean staebchen[] = new boolean[5];

	public Tisch(final String title) {
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

		imgAusruhen = getToolkit().getImage(getClass().getResource("waiting.jpg"));
		imgEinStaebchen = getToolkit().getImage(getClass().getResource("oneArm.jpg"));
		imgEssen = getToolkit().getImage(getClass().getResource("eating.jpg"));
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(imgAusruhen, 1);
		mt.addImage(imgEinStaebchen, 2);
		mt.addImage(imgEssen, 3);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}

		for (int platz = 0; platz < 5; platz++) {
			philosoph[platz] = new Philosoph(this, platz);
			philosoph[platz].start();
		}
	}

	Image getImage(Philosoph phil) {
		switch (phil.zustand) {
		case Philosoph.OHNE_STAEBCHEN:
			img = imgAusruhen;
			break;
		case Philosoph.MIT_EINEM_STAEBCHEN:
			img = imgEinStaebchen;
			break;
		case Philosoph.MIT_ZWEI_STAEBCHEN:
			img = imgEssen;
		}
		return img;
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		g.drawImage(getImage(philosoph[0]), 170, 60, this);
		g.drawImage(getImage(philosoph[1]), 270, 130, this);
		g.drawImage(getImage(philosoph[2]), 245, 230, this);
		g.drawImage(getImage(philosoph[3]), 120, 250, this);
		g.drawImage(getImage(philosoph[4]), 70, 150, this);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Tisch("Philosophers Dinner - Deadlock");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}