import java.awt.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Tierdatenbank1 extends JFrame {
	// globale Variablen
	private static final int WIDTH = 350;
	private static final int HEIGHT = 220;
	private DefaultListModel<String> tierliste = new DefaultListModel<String>();

	public Tierdatenbank1(final String title) {
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
			String query = "SELECT nachname, vorname, name, tierart, geschlecht ";
			query += "FROM besitzer, tier, beziehung WHERE ";
			query += "besitzer_id = beziehung.beziehung_besitzer_id ";
			query += "AND tier.tier_id = beziehung.beziehung_tier_id ";
			query += "AND lebendig='ja' ORDER BY nachname asc";
			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				tierliste.addElement(result.getString("nachname") + ", "
						+ result.getString("vorname") + ": "
						+ result.getString("name") + ", "
						+ result.getString("tierart") + ", "
						+ result.getString("geschlecht"));
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
		lblTierListe.setText("Liste aller lebendigen Tiere und ihrer Besitzer:");
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
					new Tierdatenbank1("Haustier-Datenbank: Aufgabe 1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}