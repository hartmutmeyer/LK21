import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SpringendeBaelle extends JFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JPanel zeichenflaeche;
	private Ball b1, b2, b3;

	public SpringendeBaelle(final String title) {
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
		b1 = new Ball(100, 100, 50, -10, Color.RED, false);
		b2 = new Ball(200, 200, 150, -5, Color.GREEN, true);
		b3 = new Ball(400, 50, 20, 7, Color.BLUE, true);
		MyTimer timer = new MyTimer(30, this);
		timer.start();
	}
	
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		b1.zeichnen(g);
		b2.zeichnen(g);
		b3.zeichnen(g);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SpringendeBaelle("Springende Bälle");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}