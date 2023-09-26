import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tmpQ2.ExklusivReisen;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class ExklusivReisen extends JFrame {
	private static final int WIDTH = 560;
	private static final int HEIGHT = 520;
	private JLabel lblVerfuegbareReisen = new JLabel();
	private DefaultListModel<String> verfuegbareReisen = new DefaultListModel<String>();
	private JList<String> listVerfuegbareReisen = new JList<String>(verfuegbareReisen);
	private JLabel lblVorname = new JLabel();
	private JTextField tfVorname = new JTextField();
	private JLabel lblNachname = new JLabel();
	private JTextField tfNachname = new JTextField();
	private JButton btnReiseListe = new JButton();
	private JLabel lblAnzahlPersonen = new JLabel();
	private JTextField tfAnzahlPersonen = new JTextField();
	private JButton btnReiseBuchen = new JButton();
	private JLabel lblGebuchteReisen = new JLabel();
	private DefaultListModel<String> gebuchteReisen = new DefaultListModel<String>();
	private JList<String> listGebuchteReisen = new JList<String>(gebuchteReisen);
	private JButton btnStornieren = new JButton();
	private Statement stmt;
	private final JScrollPane scrollPaneVerfuegbareReisen = new JScrollPane();
	private final JScrollPane scrollPaneGebuchteReisen = new JScrollPane();
	private String sql;
	private String listenEintrag;

	public ExklusivReisen() {
		createGUI();
		datenbankVerbindung();
		reiseliste();
	}

	private void createGUI() {
		setTitle("Exclusiv-Reisen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblVerfuegbareReisen.setBounds(12, 12, 116, 16);
		lblVerfuegbareReisen.setText("Verfügbare Reisen:");
		contentPane.add(lblVerfuegbareReisen);
		scrollPaneVerfuegbareReisen.setBounds(12, 36, 536, 158);

		contentPane.add(scrollPaneVerfuegbareReisen);
		scrollPaneVerfuegbareReisen.setViewportView(listVerfuegbareReisen);
		listVerfuegbareReisen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lblVorname.setBounds(12, 211, 58, 16);
		lblVorname.setText("Vorname:");
		contentPane.add(lblVorname);
		tfVorname.setBounds(80, 208, 100, 24);
		tfVorname.setText("");
		contentPane.add(tfVorname);
		lblNachname.setBounds(198, 211, 69, 16);
		lblNachname.setText("Nachname:");
		contentPane.add(lblNachname);
		tfNachname.setBounds(285, 208, 100, 24);
		tfNachname.setText("");
		contentPane.add(tfNachname);
		btnReiseListe.setBounds(421, 208, 127, 25);
		btnReiseListe.setText("Reise-Liste");
		contentPane.add(btnReiseListe);
		btnReiseListe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				reisenDerPerson();
			}
		});

		lblAnzahlPersonen.setBounds(12, 251, 160, 16);
		lblAnzahlPersonen.setText("Anzahl reisende Personen:");
		contentPane.add(lblAnzahlPersonen);
		tfAnzahlPersonen.setBounds(184, 248, 113, 24);
		tfAnzahlPersonen.setText("");
		contentPane.add(tfAnzahlPersonen);
		btnReiseBuchen.setBounds(309, 248, 239, 25);
		btnReiseBuchen.setText("markierte Reise buchen");
		contentPane.add(btnReiseBuchen);
		btnReiseBuchen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				reiseBuchen();
			}
		});

		lblGebuchteReisen.setBounds(12, 301, 201, 16);
		lblGebuchteReisen.setText("Von der Person gebuchte Reisen:");
		contentPane.add(lblGebuchteReisen);
		scrollPaneGebuchteReisen.setBounds(12, 328, 536, 143);

		contentPane.add(scrollPaneGebuchteReisen);
		scrollPaneGebuchteReisen.setViewportView(listGebuchteReisen);
		listGebuchteReisen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		btnStornieren.setBounds(309, 483, 239, 25);
		btnStornieren.setText("markierte Buchung stornieren");
		contentPane.add(btnStornieren);
		btnStornieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				reiseStornieren();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	public void datenbankVerbindung() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/exklusiv_reisen?serverTimezone=UTC&useSSL=false", "root", "root");
			stmt = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void reiseliste() {
		verfuegbareReisen.clear();
		try {
			sql = "SELECT * FROM reise";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ausgebucht = "";
				if (rs.getString("ausgebucht").equals("ja")) {
					ausgebucht = ", ausgebucht";
				}
				listenEintrag = rs.getString("reise_id") + ") " + rs.getString("land") + ", "
						+ rs.getString("von") + " bis " + rs.getString("bis") + ausgebucht;
				verfuegbareReisen.addElement(listenEintrag);
				System.out.println(listenEintrag);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "reiseliste(): " + e.getMessage());
		}
	}

	// Anfang Ereignisprozeduren

	public void reisenDerPerson() {
		try {
			gebuchteReisen.clear();
			sql = "SELECT land, von, bis, reise.reise_id, buchung_id FROM reise, buchung "
					+ "WHERE reise.reise_id = buchung.buchung_reise_id AND vorname='" + tfVorname.getText()
					+ "' and nachname='" + tfNachname.getText() + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				listenEintrag = rs.getString("land") + ", " + rs.getString("von") + " bis "
						+ rs.getString("bis") + ", #" + rs.getString("reise_id") + "~" + rs.getString("buchung_id");
				gebuchteReisen.addElement(listenEintrag);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "reisenDerPerson(): " + e.getMessage());
		}
	}

	public String getReiseNr() {
		String help = listVerfuegbareReisen.getSelectedValue();
		int i = help.indexOf(')');
		help = help.substring(0, i);
		return help;
	}

	public int getFreiePlaetze(String reiseNr) {
		int anzahl = 0;
		try {
			ResultSet rs = stmt.executeQuery("SELECT anzahl FROM buchung WHERE buchung_reise_id=" + reiseNr);
			while (rs.next()) {
				anzahl += rs.getInt(1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "getFreiePlaetze(): " + e.getMessage());
		}

		return (12 - anzahl);
	}

	public void reiseBuchen() {
		if (tfVorname.getText().length() == 0 || tfNachname.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "reiseBuchen(): Bitte geben Sie den Namen der Person vollständig an.");
			return;
		}
		if (listVerfuegbareReisen.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(this, "reiseBuchen(): Bitte wählen Sie eine Reise aus.");
			return;
		}
		String reiseNr = getReiseNr();
		System.out.println("ReiseNr:" + reiseNr);
		int freiePlätze = getFreiePlaetze(reiseNr);
		System.out.println("Freie Plätze:" + freiePlätze);
		int anzahl;
		try {
			anzahl = Integer.parseInt(tfAnzahlPersonen.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "reiseBuchen(): Bitte wählen sie die Anzahl der Personen korrekt aus.");
			return;
		}
		if (anzahl <= 0) {
			JOptionPane.showMessageDialog(this, "reiseBuchen(): Bitte wählen sie die Anzahl der Personen korrekt aus.");
			return;
		}
		if (anzahl > freiePlätze) {
			JOptionPane.showMessageDialog(this, "reiseBuchen(): Es sind nicht mehr ausreichend freie Plätze verfügbar.");
			return;
		}
		buchung(reiseNr, anzahl);
		if (anzahl == freiePlätze) {
			ausgebucht(reiseNr);
		}
	}

	void buchung(String reiseNr, int anzahl) {
		try {
			sql = "INSERT INTO buchung VALUES (NULL,'" + tfVorname.getText() + "','"
					+ tfNachname.getText() + "'," + anzahl + "," + reiseNr + ")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "buchung(): " + e.getMessage());
		}
		reisenDerPerson();
	}

	public void ausgebucht(String reiseNr) {
		try {
			sql = "UPDATE reise SET ausgebucht='ja' WHERE reise_id=" + reiseNr;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "ausgebucht(): " + e.getMessage());
		}
		reiseliste();
	}

	public void reiseStornieren() {
		try {
			String help = listGebuchteReisen.getSelectedValue();
			if (help == null) {
				JOptionPane.showMessageDialog(this, "reiseStornieren(): Bitte wählen Sie die Reise aus, die storniert werden soll.");
				return;
			}
			int indexTilde = help.indexOf('~');
			String buchungsNr = help.substring(indexTilde + 1);
			int indexHashtag = help.indexOf('#');
			String reiseNr = help.substring(indexHashtag + 1, indexTilde);
			System.out.println("reiseNr" + reiseNr);
			// Zeitersparnis: keine Sicherheitsabfrage
			sql = "DELETE FROM buchung WHERE buchung_id=" + buchungsNr;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			reisenDerPerson();
			sql = "UPDATE reise SET ausgebucht='nein' WHERE reise_id=" + reiseNr;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			reiseliste();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "reiseStornieren(): " + e.getMessage());
		}

	}

	// Ende Ereignisprozeduren

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ExklusivReisen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
