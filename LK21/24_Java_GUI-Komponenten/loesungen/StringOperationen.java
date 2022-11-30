import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StringOperationen extends JFrame {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private JLabel zeichenflaeche;

	public StringOperationen(final String title) {
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
		zeichenflaeche.setFont(new Font("Arial", Font.PLAIN, 48));
		contentPane.add(zeichenflaeche);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
        String original = "software";
        String result;

/*1*/   result = " hallo ";
/*2*/   result = result.replace('l',original.charAt(6));
/*3*/   result = result.trim();
        int index = original.indexOf('t');
/*4*/   result = result.substring(0,index);
/*5*/   result += "d";
/*6*/   result = result.toUpperCase();
/*7*/   result += original.substring(4);

        g.drawString(result, 30, 70);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new StringOperationen("String-Operationen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}