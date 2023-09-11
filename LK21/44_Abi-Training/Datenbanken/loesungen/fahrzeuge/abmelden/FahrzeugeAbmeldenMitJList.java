import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class FahrzeugeAbmeldenMitJList extends JFrame {
	private Connection conn;
	private JTextField textFieldAbmeldedatum;
	private JList<String> listFahrzeuge;
	private DefaultListModel<String> fahrzeuge = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FahrzeugeAbmeldenMitJList frame = new FahrzeugeAbmeldenMitJList(
							"Fahrzeuge Abmeldenen");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FahrzeugeAbmeldenMitJList(String title) {
		super(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 238, 238));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblFahrzeugliste = new JLabel("Fahrzeugliste:");
		lblFahrzeugliste.setBounds(12, 12, 120, 22);
		panel.add(lblFahrzeugliste);

		JScrollPane scrollPane = new JScrollPane();
		lblFahrzeugliste.setLabelFor(scrollPane);
		scrollPane.setBounds(12, 46, 422, 183);
		panel.add(scrollPane);

		listFahrzeuge = new JList<String>(fahrzeuge);
		listFahrzeuge.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listFahrzeuge);

		JLabel lblAbmeldedatum = new JLabel("Abmeldedatum:");
		lblAbmeldedatum.setBounds(12, 241, 120, 20);
		panel.add(lblAbmeldedatum);

		textFieldAbmeldedatum = new JTextField();
		lblAbmeldedatum.setLabelFor(textFieldAbmeldedatum);
		textFieldAbmeldedatum.setToolTipText("JJJJ-MM-TT  z.B. 2012-02-27");
		textFieldAbmeldedatum.setBounds(141, 242, 108, 19);
		panel.add(textFieldAbmeldedatum);
		textFieldAbmeldedatum.setColumns(10);

		JButton btnFahrzeugAbmelden = new JButton("Kfz abmelden");
		btnFahrzeugAbmelden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fahrzeugAbmelden();
			}
		});
		btnFahrzeugAbmelden.setBounds(261, 239, 173, 25);
		panel.add(btnFahrzeugAbmelden);

		datenbankOeffnen();
		datenbankAngemeldeteFahrzeugeAnzeigen();
	}

	private void datenbankOeffnen() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fahrzeuge?serverTimezone=UTC&useSSL=false", "root", "root");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void datenbankAngemeldeteFahrzeugeAnzeigen() {
		ResultSet rs = null;
		String cmdSQL;
		Statement stmt;

		fahrzeuge.clear();
		cmdSQL = "SELECT kfz_zeichen, vorname, nachname, geburtstag FROM fahrzeughalter WHERE abgemeldet IS NULL";
		System.out.println(cmdSQL);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(cmdSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			rs.beforeFirst();
			while (rs.next()) {
				fahrzeuge.addElement(rs.getString("kfz_zeichen") + ", "
						+ rs.getString("vorname") + " "
						+ rs.getString("nachname") + " ("
						+ rs.getString("geburtstag") + ")");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void datenbankFahrzeugAbmelden(String kfzzeichen,
			String abmeldedatum) {
		int ergebnis = 0;
		String cmdSQL;

		cmdSQL = "UPDATE fahrzeughalter SET abgemeldet='" + abmeldedatum
				+ "' WHERE kfz_zeichen='" + kfzzeichen
				+ "' AND abgemeldet IS NULL";

		System.out.println(cmdSQL);

		Statement stmt;
		try {
			stmt = conn.createStatement();
			ergebnis = stmt.executeUpdate(cmdSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (ergebnis == 1) {
			System.out.println("Das Fahrzeug mit dem KFZ-Zeichen " + kfzzeichen + " wurde abgemeldet.");
		} else {
			System.out.println("Es gibt kein angemeldetes Fahrzeug mit diesem KFZ-Zeichen!");
		}
	}

	private void fahrzeugAbmelden() {
		String abmeldedatum = textFieldAbmeldedatum.getText();
		if (abmeldedatum != "" && !listFahrzeuge.isSelectionEmpty()) {
			int i = listFahrzeuge.getSelectedValue().indexOf(',');
			String kfzzeichen = listFahrzeuge.getSelectedValue().substring(0, i);
			datenbankFahrzeugAbmelden(kfzzeichen, abmeldedatum);
			datenbankAngemeldeteFahrzeugeAnzeigen();
		}
	}
}
