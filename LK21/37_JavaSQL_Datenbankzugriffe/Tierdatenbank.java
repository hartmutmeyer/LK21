import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Tierdatenbank extends JFrame {
	// globale Variablen
	private static final int WIDTH = 250;
	private static final int HEIGHT = 200;
	private DefaultListModel<String> tierliste = new DefaultListModel<String>();

	public Tierdatenbank(final String title) {
		super(title);
		createGUI();
		datenbankAbfrage();
	}
	
	private void datenbankAbfrage() {
		try (
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/haustier?serverTimezone=UTC&useSSL=false", "root", "root");

			Statement stmt = conn.createStatement()
		) {
			ResultSet rs = stmt.executeQuery("SELECT name, tierart FROM tier");
			while (rs.next()) {
				tierliste.addElement(rs.getString("name") + ", " + rs.getString("tierart"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		JLabel lblTierListe = new JLabel();
		lblTierListe.setText("Liste aller Tiere:");
		contentPane.add("North", lblTierListe);
		JList<String> listTiere = new JList<String>(tierliste);
		contentPane.add("Center", listTiere);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Tierdatenbank("Haustier-Datenbank");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}