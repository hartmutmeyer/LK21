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

public class PotatoAnwendung extends JFrame {
	// globale Variablen
	private static final int WIDTH = 900;
	private static final int HEIGHT = 350;
	private static final Color BACKGROUND = Color.YELLOW;
	protected JPanel zeichenflaeche;
	private Image image;
	private Potato potatoes[] = new Potato[10];

	public PotatoAnwendung(final String title) {
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
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		image = getToolkit().getImage(getClass().getResource("potato.gif"));
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
		for (int i = 0; i < 10; i++) {
			potatoes[i] = new Potato(this, 40 + 80 * i, 200);
		}
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int i = 0; i < 10; i++) {
			g.drawImage(image, potatoes[i].x, potatoes[i].y, this);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new PotatoAnwendung("Jumping Potatoes");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}