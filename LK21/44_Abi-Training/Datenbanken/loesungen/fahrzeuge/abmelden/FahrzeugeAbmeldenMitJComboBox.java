import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class FahrzeugeAbmeldenMitJComboBox extends JFrame {
	private Connection conn;
	private JTextField textFieldAbmeldedatum;
	private JComboBox<String> comboboxFahrzeuge;
	private DefaultComboBoxModel<String> fahrzeuge = new DefaultComboBoxModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FahrzeugeAbmeldenMitJComboBox frame = new FahrzeugeAbmeldenMitJComboBox("Fahrzeuge Abmelden");
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FahrzeugeAbmeldenMitJComboBox(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 165);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 238, 238));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblFahrzeugliste = new JLabel("Fahrzeugliste:");
		lblFahrzeugliste.setBounds(12, 12, 98, 22);
		panel.add(lblFahrzeugliste);

		comboboxFahrzeuge = new JComboBox<String>(fahrzeuge);
		comboboxFahrzeuge.setBounds(12, 46, 422, 22);
		panel.add(comboboxFahrzeuge);

		JLabel lblAbmeldedatum = new JLabel("Abmeldedatum:");
		lblAbmeldedatum.setBounds(12, 98, 98, 20);
		panel.add(lblAbmeldedatum);

		textFieldAbmeldedatum = new JTextField();
		lblAbmeldedatum.setLabelFor(textFieldAbmeldedatum);
		textFieldAbmeldedatum.setToolTipText("JJJJ-MM-TT  z.B. 2012-02-27");
		textFieldAbmeldedatum.setBounds(121, 99, 138, 19);
		panel.add(textFieldAbmeldedatum);
		textFieldAbmeldedatum.setColumns(10);

		JButton btnFahrzeugAbmelden = new JButton("Fahrzeug abmelden");
		btnFahrzeugAbmelden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fahrzeugAbmelden();
			}
		});
		btnFahrzeugAbmelden.setBounds(289, 96, 145, 25);
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

		fahrzeuge.removeAllElements();
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
			JOptionPane.showMessageDialog(this,
					"Das Fahrzeug mit dem KFZ-Zeichen " + kfzzeichen
							+ " wurde abgemeldet.");
		} else {
			JOptionPane.showMessageDialog(this, "Ungültiges Abmeldedatum!");
		}
	}

	private void fahrzeugAbmelden() {
		String abmeldedatum = textFieldAbmeldedatum.getText();
		if (abmeldedatum != "" && comboboxFahrzeuge.getSelectedIndex() != -1) {
			// Bei JComboBox bietet sowohl JComboBox selbst als auch die Klasse des 
			// Daten-Modells eine Methode an, die den ausgewählten Eintrag zurück liefert:
			// String wahl = (String) fahrzeuge.getSelectedItem();
			String wahl = (String) comboboxFahrzeuge.getSelectedItem();
			int i = wahl.indexOf(',');
			String kfzzeichen = wahl.substring(0, i);
			datenbankFahrzeugAbmelden(kfzzeichen, abmeldedatum);
			datenbankAngemeldeteFahrzeugeAnzeigen();
		}
	}
}
