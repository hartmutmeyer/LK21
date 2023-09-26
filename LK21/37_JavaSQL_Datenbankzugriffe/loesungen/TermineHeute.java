import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class TermineHeute extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> termine = new DefaultListModel<String>();
	private JList<String> listTermine = new JList<String>(termine);
	String heute;

	public TermineHeute() {
		createGUI();
		getDateOfToday();
		datenbankAlteTermineLoeschen();
		datenbankTermineHeuteAbfragen();
	}

	private void getDateOfToday() {
		// heute = "2011-11-21";
		java.sql.Date datumHeute = new java.sql.Date(System.currentTimeMillis());
		heute = datumHeute.toString();		
	}

	public void datenbankAlteTermineLoeschen() {
		int ergebnis = 0;
		String sql = "DELETE FROM termin WHERE datum < '" + heute + "'";

		try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/terminplaner?serverTimezone=UTC&useSSL=false", "root", "root");
			Statement stmt = conn.createStatement()) {
			System.out.println(sql);
			ergebnis = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Fehler beim Löschen der alten Termine",
					JOptionPane.ERROR_MESSAGE);
		}
		if (ergebnis > 0) {
			System.out.println("Es wurden " + ergebnis
					+ " alte Termine gelöscht.");
		} else {
			System.out.println("Es wurden keine alten Termine gelöscht.");
		}
	}

	public void datenbankTermineHeuteAbfragen() {
		ResultSet rs = null;
		String cmdSQL = "SELECT * FROM termin WHERE datum = '" + heute
				+ "' ORDER BY zeit ASC";

		try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/terminplaner?serverTimezone=UTC&useSSL=false", "root", "root");
			Statement stmt = conn.createStatement()) {
			System.out.println(cmdSQL);
			rs = stmt.executeQuery(cmdSQL);
			while (rs.next()) {
				termine.addElement(rs.getString("zeit") + " "
						+ rs.getString("text"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Fehler beim Auslesen der Termine",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void createGUI() {
		setTitle("Termine für heute anzeigen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 323);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTermineHeute = new JLabel("Termine heute:");
		lblTermineHeute.setBounds(12, 12, 98, 15);
		contentPane.add(lblTermineHeute);

		lblTermineHeute.setLabelFor(listTermine);
		listTermine.setBounds(12, 33, 438, 250);
		contentPane.add(listTermine);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TermineHeute frame = new TermineHeute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
