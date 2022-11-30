import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyJListDynamisch extends JFrame {
	// globale Variablen
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private String[] faecher = {"Englisch", "Deutsch", "Geschichte", 
        "Mathematik", "Musik", "Physik"};
	private DefaultListModel<String> faecherliste = new DefaultListModel<String>();

	public MyJListDynamisch(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JList<String> listFaecher = new JList<String>(faecherliste);
		listFaecher.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.add(listFaecher);
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
					new MyJListDynamisch("MyJList - dynamisch");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}