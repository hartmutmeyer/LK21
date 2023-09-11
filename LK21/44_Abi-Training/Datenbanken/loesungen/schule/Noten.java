import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Noten extends JFrame {
	// globale Variablen
	private static final int WIDTH = 350;
	private static final int HEIGHT = 250;
	private JTextField tfSchuelerId;
	private JTextField tfVorname;
	private JTextField tfNachname;
	private JButton btnLoeschen, btnAendern, btnNeu;
	private JComboBox<String> comboBoxNoten;
	private DefaultComboBoxModel<String> noten = new DefaultComboBoxModel<String>();
	private Connection conn;
	private String schuelerID, vorname, nachname, kursID, note;

	public Noten(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfSchuelerId = new JTextField();
		tfSchuelerId.setBounds(108, 12, 114, 19);
		contentPane.add(tfSchuelerId);
		tfSchuelerId.setColumns(10);
		
		JButton btnSchuelerLaden = new JButton("laden");
		btnSchuelerLaden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schuelerID = tfSchuelerId.getText();
				if (datenbankSchuelerDatenLaden()) {
					btnLoeschen.setEnabled(true);
					btnAendern.setEnabled(true);
					btnNeu.setEnabled(true);
				}
			}
		});
		btnSchuelerLaden.setBounds(233, 12, 98, 25);
		contentPane.add(btnSchuelerLaden);
		
		JLabel lblSchuelerId = new JLabel("Schüler ID:");
		lblSchuelerId.setBounds(12, 14, 78, 15);
		contentPane.add(lblSchuelerId);
		
		tfVorname = new JTextField();
		tfVorname.setEditable(false);
		tfVorname.setBounds(108, 57, 114, 19);
		contentPane.add(tfVorname);
		tfVorname.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(12, 59, 78, 17);
		contentPane.add(lblVorname);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setBounds(12, 86, 84, 15);
		contentPane.add(lblNachname);
		
		tfNachname = new JTextField();
		tfNachname.setEditable(false);
		tfNachname.setBounds(108, 84, 114, 19);
		contentPane.add(tfNachname);
		tfNachname.setColumns(10);
		
		comboBoxNoten = new JComboBox<String>(noten);
		comboBoxNoten.setBounds(108, 129, 226, 24);
		contentPane.add(comboBoxNoten);
		
		JLabel lblNoten = new JLabel("Noten:");
		lblNoten.setBounds(12, 134, 55, 15);
		contentPane.add(lblNoten);
		
		btnLoeschen = new JButton("löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datenbankNoteLoeschen();
			}
		});
		btnLoeschen.setEnabled(false);
		btnLoeschen.setBounds(12, 178, 98, 25);
		contentPane.add(btnLoeschen);
		
		btnAendern = new JButton("ändern");
		btnAendern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datenbankNoteAendern();
			}
		});
		btnAendern.setEnabled(false);
		btnAendern.setBounds(122, 178, 98, 25);
		contentPane.add(btnAendern);
		
		btnNeu = new JButton("neu");
		btnNeu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datenbankNeueNote();
			}
		});
		btnNeu.setEnabled(false);
		btnNeu.setBounds(236, 178, 98, 25);
		contentPane.add(btnNeu);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		datenbankOeffnen();
	}

	private void datenbankOeffnen() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/schule?serverTimezone=UTC&useSSL=false", "root", "root");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private boolean datenbankSchuelerDatenLaden() {
		ResultSet rs = null;
		String cmdSQL;
		Statement stmt;
		boolean erfolg = false;

		noten.removeAllElements();
		cmdSQL = "SELECT vorname, nachname FROM schueler WHERE schueler_id = " + schuelerID;
		System.out.println(cmdSQL);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(cmdSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			if (rs.first()) {
				vorname = rs.getString("vorname");
				nachname = rs.getString("nachname");
				tfVorname.setText(vorname);
				tfNachname.setText(nachname);
				erfolg = true;
			} else {
				JOptionPane.showMessageDialog(this, "Kein Schüler mit dieser ID vorhanden!");
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		cmdSQL = "SELECT fach, kurs_id, note FROM kurs, note WHERE note_schueler_id = " + schuelerID;
		cmdSQL += " AND kurs_id = note_kurs_id";
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
				noten.addElement(rs.getString("fach") + "("
						+ rs.getString("kurs_id") + "): "
						+ rs.getString("note"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return erfolg;
	}
	
	private void datenbankNoteLoeschen() {
		String cmdSQL;
		Statement stmt;
		int ergebnis = 0;

		String auswahl = (String) noten.getSelectedItem();
		int i1 = auswahl.indexOf('(') + 1;
		int i2 = auswahl.indexOf(')');
		System.out.println(auswahl);
		kursID = auswahl.substring(i1, i2);
		System.out.println("kurs_id = " + kursID);
		cmdSQL = "DELETE FROM note WHERE note_schueler_id = " + schuelerID + " AND note_kurs_id = " + kursID;
		System.out.println(cmdSQL);
		ergebnis = JOptionPane.showConfirmDialog(this, "Soll der Noteneintrag wirklich gelöscht werden?");
		if (ergebnis == JOptionPane.OK_OPTION) {
			try {
				stmt = conn.createStatement();
				ergebnis = stmt.executeUpdate(cmdSQL);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (ergebnis > 0) {
				datenbankSchuelerDatenLaden();
			}
		}
	}
	
	private void datenbankNoteAendern() {
		String cmdSQL;
		Statement stmt;
		int ergebnis = 0;

		String auswahl = (String) noten.getSelectedItem();
		int i1 = auswahl.indexOf('(') + 1;
		int i2 = auswahl.indexOf(')');
		System.out.println(auswahl);
		kursID = auswahl.substring(i1, i2);
		System.out.println("kurs_id = " + kursID);
		note = JOptionPane.showInputDialog(this, "Bitte die korrigerte Note eingeben:");
		cmdSQL = "UPDATE note SET note = " + note + " WHERE note_schueler_id = " + schuelerID + " AND note_kurs_id = " + kursID;
		System.out.println(cmdSQL);
		try {
			stmt = conn.createStatement();
			ergebnis = stmt.executeUpdate(cmdSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (ergebnis > 0) {
			datenbankSchuelerDatenLaden();
		}
	}
	
	private void datenbankNeueNote() {
		String cmdSQL;
		Statement stmt;
		int ergebnis = 0;

		kursID = JOptionPane.showInputDialog(this, "Neuer Noteneintrag: Bitte die Kurs ID eingeben:");
		note = JOptionPane.showInputDialog(this, "Neuer Noteneintrag: Bitte die Note eingeben:");
		cmdSQL = "INSERT INTO note VALUES (" + kursID + "," + schuelerID + "," + note + ")";
		System.out.println(cmdSQL);
		try {
			stmt = conn.createStatement();
			ergebnis = stmt.executeUpdate(cmdSQL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (ergebnis > 0) {
			datenbankSchuelerDatenLaden();
		}
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Noten("Noten");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}