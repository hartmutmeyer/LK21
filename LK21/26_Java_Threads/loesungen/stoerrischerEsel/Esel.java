import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Esel extends JFrame implements MouseListener {
	// globale Variablen
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final Color BACKGROUND = Color.YELLOW;
	private static final Color FOREGROUND = Color.BLACK;
	private JPanel zeichenflaeche;
	private Image image;
	private int x = 170;
	protected int y = 190;
	private final int STEHEN = 0;
	private final int NACH_RECHTS = 1;
	private final int NACH_LINKS = 2;
	private int zustand = STEHEN;
	private int xZiel;

	public Esel(final String title) {
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
		zeichenflaeche.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		image = getToolkit().getImage(getClass().getResource("esel.gif"));
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
		zeichenflaeche.addMouseListener(this);

		EselTimer t = new EselTimer(this);
		t.start();
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		g.drawImage(image, x, y, this);
		switch (zustand) {
		case NACH_LINKS:
			x--;
			if (x <= xZiel) {
				zustand = STEHEN;
			}
			break;
		case NACH_RECHTS:
			x++;
			if (x >= xZiel) {
				zustand = STEHEN;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		if (mY > y && mY < y + 130) {
			if (mX >= x + 70) {
				zustand = NACH_LINKS;
				xZiel = mX - 140 - 150;
			} else {
				zustand = NACH_RECHTS;
				xZiel = mX + 150;
			}
			if (mX > x && mX < x + 140) {
				y = 140;
				EselSpringTimer timer = new EselSpringTimer(this);
				timer.start();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Esel("Störrischer Esel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}