import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Fahne extends JFrame implements ItemListener {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JCheckBox cbWindVonLinks;
	private JLabel zeichenflaeche;

	public Fahne(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		cbWindVonLinks = new JCheckBox("Wind von links", true);
		contentPane.add(cbWindVonLinks);
		cbWindVonLinks.addItemListener(this);
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
		zeichenflaeche.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		g.fillRect(150, 30, 5, 100);
		if (cbWindVonLinks.isSelected()) {
			g.setColor(Color.RED);
			g.fillRect(155, 30, 60, 50);
			g.setColor(Color.BLACK);
			g.drawRect(155, 30, 60, 50);
		} else {
			g.setColor(Color.RED);
			g.fillRect(95, 30, 60, 50);
			g.setColor(Color.BLACK);
			g.drawRect(95, 30, 60, 50);
		}
		g.drawOval(-10, -10, 5, 5);
	}

	public void itemStateChanged(ItemEvent e) {
		repaint();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Fahne("Fahne");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}