import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyJListMitScrollPane extends JFrame {
	// globale Variablen
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	private String[] faecher = {"Deutsch", "Biologie", "Englisch", "Geschichte", 
        "Informatik", "Mathematik", "Musik", "Physik"};
	private DefaultListModel<String> faecherliste = new DefaultListModel<String>();

	public MyJListMitScrollPane(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.add(scrollPane);
		JList<String> listFaecher = new JList<String>(faecherliste);
		scrollPane.setViewportView(listFaecher);
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
					new MyJListMitScrollPane("MyJList - mit ScrollPane");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}