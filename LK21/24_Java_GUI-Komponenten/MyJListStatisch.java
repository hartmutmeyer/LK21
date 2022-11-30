import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyJListStatisch extends JFrame {
	// globale Variablen
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private String[] faecher = {"Englisch", "Deutsch", "Geschichte", 
        "Mathematik", "Musik", "Physik"};

	public MyJListStatisch(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JList<String> listFaecher = new JList<String>(faecher);
		listFaecher.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.add(listFaecher);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MyJListStatisch("MyJList - statisch");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}