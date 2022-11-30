import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StringVergleich extends JFrame {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JLabel zeichenflaeche;

	public StringVergleich(final String title) {
		super(title);
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
		zeichenflaeche.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		String s1 = "Hallo";
		String s2 = "Hallo";
		String s3 = "Hal";
		s3 += "lo";
		if (s1 == s2) {
			g.drawString("s1 und s2 sind gleich", 20, 100);
		}
		if (s1 == s3) {
			g.drawString("s1 und s3 sind gleich", 20, 150);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringVergleich anwendung = new StringVergleich("StringVergleich");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
