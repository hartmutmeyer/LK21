import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyJComboBox extends JFrame {
	// globale Variablen
	private static final int WIDTH = 200;
	private static final int HEIGHT = 20;
	private String[] faecher = {"Englisch", "Deutsch", "Geschichte", 
        "Mathematik", "Musik", "Physik"};
	private DefaultComboBoxModel<String> faecherliste = new DefaultComboBoxModel<String>();

	public MyJComboBox(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JComboBox<String> comboboxFaecher = new JComboBox<String>(faecherliste);
		comboboxFaecher.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.add(comboboxFaecher);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		
		for (String fach: faecher) {
			faecherliste.addElement(fach);
		}
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MyJComboBox("MyJComboBox");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}