import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Mandelbrot extends JFrame {
	// globale Variablen
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 1200;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JLabel zeichenflaeche;
	private final double MIN_CX = 0.40;
	private final double MAX_CX = 0.45;
	private final double MIN_CY = -0.25;
	private final double MAX_CY = -0.20;
	private final int MAX_ITERATIONEN = 10000;
	private double cx, cy, minCx, maxCx, minCy, maxCy, punktAbstandX, punktAbstandY, maxBetragsQuadrat;
	private int iterationsWert, maxIterationen;

	public Mandelbrot(final String title) {
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
		zeichenflaeche.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.add(zeichenflaeche);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		minCx = MIN_CX;
		maxCx = MAX_CX;
		minCy = MIN_CY;
		maxCy = MAX_CY;
		punktAbstandX = (maxCx - minCx) / WIDTH;
		punktAbstandY = (maxCy - minCy) / HEIGHT;
		maxIterationen = MAX_ITERATIONEN;
		maxBetragsQuadrat = 4.0;
	}

	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		for (int x = 0; x < WIDTH; x++) {
			cx = minCx + x * punktAbstandX;
			for (int y = 0; y < HEIGHT; y++) {
				cy = minCy + y * punktAbstandY;
				iterationsWert = punktIteration(cx, cy, maxBetragsQuadrat, maxIterationen);
				int red = (int) ((iterationsWert * 25.0) / maxIterationen * 255) % 255;
				g.setColor(new Color(255 - red, 255 - red, 255 - red));
				g.fillRect(x, y, 1, 1);
			}
		}
	}

	public int punktIteration(double cx, double cy, double maxBetragQuadrat, int maxIter) {
		double betragQuadrat = 0.0;
		int iter = 0;
		double x = 0.0;
		double y = 0.0;
		double xTemp;

		while ((betragQuadrat <= maxBetragQuadrat) && (iter < maxIter)) {
			xTemp = x * x - y * y + cx;
			y = 2 * x * y + cy;
			x = xTemp;
			iter++;
			betragQuadrat = x * x + y * y;
		}

		return iter;
		// Wird ein kontinuierlicherer Farbverlauf gewünscht, so bietet sich alternativ die Formel
		// return (int) (iter - Math.log(Math.log(betragQuadrat)/Math.log(4)) / Math.log(2));
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Mandelbrot("Mandelbrot");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}