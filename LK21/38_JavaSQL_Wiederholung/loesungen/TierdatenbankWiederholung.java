import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class TierdatenbankWiederholung extends JFrame {

	private JPanel contentPane;
	private JTextField textField_BesitzerNr;
	private JTextField textField_Vorname;
	private JTextField textField_Nachname;
	private List listTiere;

	public TierdatenbankWiederholung() {
		createGUI();
	}

	protected void actionPerformed_Laden() {
		datenbankLaden();
	}

	protected void actionPerformed_Loeschen() {
		datenbankLoeschen();
	}

	private void datenbankLaden() {
		ResultSet rs = null;
		String cmdSQL;
		String besitzerID = textField_BesitzerNr.getText();

		cmdSQL = "SELECT vorname,nachname,name,tierart FROM tier,besitzer,beziehung WHERE besitzer_id=";
		cmdSQL = cmdSQL + besitzerID;
		cmdSQL = cmdSQL + " AND beziehung.beziehung_tier_id = tier.tier_id";
		cmdSQL = cmdSQL + " AND beziehung.beziehung_besitzer_id = besitzer.besitzer_id";
		cmdSQL = cmdSQL + " AND lebendig='ja'";

		System.out.println(cmdSQL);

		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/haustier?serverTimezone=UTC&useSSL=false", "root", "root");
			Statement stmt = conn.createStatement()
		) {
			rs = stmt.executeQuery(cmdSQL);
			if (rs.isBeforeFirst()) {
				listTiere.removeAll();
				while (rs.next()) {
					listTiere.add(rs.getString("name") + " ("
							+ rs.getString("tierart") + ")");
					textField_Vorname.setText(rs.getString("vorname"));
					textField_Nachname.setText(rs.getString("nachname"));
				}
			} else {
				listTiere.removeAll();
				textField_Vorname.setText("");
				textField_Nachname.setText("");
				System.out.println("Einen Besitzer mit dieser ID gibt es nicht!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void datenbankLoeschen() {
		int ergebnis = 0;
		String cmdSQL;
		String besitzerID = textField_BesitzerNr.getText();

		cmdSQL = "DELETE FROM besitzer WHERE besitzer_id=" + besitzerID;

		System.out.println(cmdSQL);

		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/haustier?serverTimezone=UTC&useSSL=false", "root", "root");
			Statement stmt = conn.createStatement()
		) {
			ergebnis = stmt.executeUpdate(cmdSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		listTiere.removeAll();
		textField_Vorname.setText("");
		textField_Nachname.setText("");
		if (ergebnis == 1) {
			System.out.println("Der Besitzer mit der ID " + besitzerID + " wurde gelöscht.");
		} else {
			System.out.println("Einen Besitzer mit dieser ID gibt es nicht!");
		}
	}
	
	private void createGUI() {
		setTitle("Haustier-Datenbank: Wiederholung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBesitzerNr = new JLabel("BesitzerNr:");
		lblBesitzerNr.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBesitzerNr.setBounds(12, 12, 69, 15);
		contentPane.add(lblBesitzerNr);

		textField_BesitzerNr = new JTextField();
		lblBesitzerNr.setLabelFor(textField_BesitzerNr);
		textField_BesitzerNr.setBounds(84, 10, 47, 19);
		contentPane.add(textField_BesitzerNr);
		textField_BesitzerNr.setColumns(10);

		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVorname.setBounds(12, 52, 61, 15);
		contentPane.add(lblVorname);

		textField_Vorname = new JTextField();
		textField_Vorname.setEditable(false);
		lblVorname.setLabelFor(textField_Vorname);
		textField_Vorname.setBounds(84, 50, 114, 19);
		contentPane.add(textField_Vorname);
		textField_Vorname.setColumns(10);

		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNachname.setBounds(224, 52, 69, 15);
		contentPane.add(lblNachname);

		textField_Nachname = new JTextField();
		textField_Nachname.setEditable(false);
		lblNachname.setLabelFor(textField_Nachname);
		textField_Nachname.setBounds(300, 50, 134, 19);
		contentPane.add(textField_Nachname);
		textField_Nachname.setColumns(10);

		JLabel lblTiere = new JLabel("Tiere:");
		lblTiere.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTiere.setBounds(12, 100, 55, 15);
		contentPane.add(lblTiere);

		listTiere = new List();
		listTiere.setBounds(12, 121, 422, 142);
		contentPane.add(listTiere);

		JButton btnLaden = new JButton("Laden");
		btnLaden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionPerformed_Laden();
			}
		});
		btnLaden.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLaden.setBounds(224, 7, 95, 25);
		contentPane.add(btnLaden);

		JButton btnLoeschen = new JButton("Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionPerformed_Loeschen();
			}
		});
		btnLoeschen.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLoeschen.setBounds(339, 7, 95, 25);
		contentPane.add(btnLoeschen);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TierdatenbankWiederholung frame = new TierdatenbankWiederholung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
