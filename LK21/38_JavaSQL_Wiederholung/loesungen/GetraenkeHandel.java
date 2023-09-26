import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GetraenkeHandel extends JFrame {
	private static final int WIDTH = 450;
	private static final int HEIGHT = 320;
	private JLabel lblGetraenkeListe = new JLabel();
	private DefaultListModel<String> getraenkeListe = new DefaultListModel<String>();
	private JList<String> listGetraenke = new JList<String>(getraenkeListe);
	private JLabel lblGetraenk = new JLabel();
	private JTextField tfGetraenk = new JTextField();
	private JLabel lblAnzahl = new JLabel();
	private JTextField tfAnzahl = new JTextField();
	private JButton btnHinzufuegen = new JButton();
	private JButton btnAbziehen = new JButton();
	private JButton btnNachbestellung = new JButton();
	private JButton btnAutomatischeNachbestellung = new JButton();
	private Statement stmt;
	private String sql;
	private String listenEintrag;

	public GetraenkeHandel() {
		createGUI();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/getraenkehandel?serverTimezone=UTC&useSSL=false", "root", "root");
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		listeEinlesen();
	}

	private void createGUI() {
		setTitle("Getränke-Handel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblGetraenkeListe.setBounds(12, 12, 113, 16);
		lblGetraenkeListe.setText("Getränke-Liste:");
		contentPane.add(lblGetraenkeListe);
		listGetraenke.setBounds(12, 40, 426, 145);
		contentPane.add(listGetraenke);
		lblGetraenk.setBounds(12, 200, 50, 16);
		lblGetraenk.setText("Getränk:");
		contentPane.add(lblGetraenk);
		tfGetraenk.setBounds(80, 197, 209, 24);
		tfGetraenk.setText("");
		contentPane.add(tfGetraenk);
		lblAnzahl.setBounds(307, 200, 45, 16);
		lblAnzahl.setText("Anzahl:");
		contentPane.add(lblAnzahl);
		tfAnzahl.setBounds(367, 197, 71, 24);
		tfAnzahl.setText("");
		contentPane.add(tfAnzahl);
		btnHinzufuegen.setBounds(12, 233, 134, 24);
		btnHinzufuegen.setText("Hinzufügen");
		btnHinzufuegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				hinzufuegen();
			}
		});
		contentPane.add(btnHinzufuegen);
		btnAbziehen.setBounds(158, 233, 134, 24);
		btnAbziehen.setText("Abziehen");
		btnAbziehen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				abziehen();
			}
		});
		contentPane.add(btnAbziehen);
		btnNachbestellung.setBounds(304, 233, 134, 24);
		btnNachbestellung.setText("Nachbestellung");
		btnNachbestellung.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				nachbestellung();
			}
		});
		contentPane.add(btnNachbestellung);
		btnAutomatischeNachbestellung.setBounds(12, 282, 426, 25);
		btnAutomatischeNachbestellung.setText("Automatische Nachbestellung für alle");
		btnAutomatischeNachbestellung.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				automatischeNachbestellung();
			}
		});
		contentPane.add(btnAutomatischeNachbestellung);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}

	public void listeEinlesen() {
		try {
			sql = "SELECT * FROM getränk ORDER BY name";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			getraenkeListe.clear();
			while (rs.next()) {
				listenEintrag = rs.getString("name") + ": " + rs.getString("anzahl") + "   ["
						+ rs.getString("min_anzahl") + "," + rs.getString("max_anzahl") + "]" + "   L:"
						+ rs.getString("getränk_lieferant_id");
				getraenkeListe.addElement(listenEintrag);
			}
		} catch (Exception e) {
			System.out.println("listeEinlesen(): " + e.getMessage());
		}
	}

	public void hinzufuegen() {
		try {

			int plus = Integer.parseInt(tfAnzahl.getText());
			sql = "SELECT anzahl FROM getränk where name='" + tfGetraenk.getText() + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int anz = rs.getInt(1);
			anz += plus;
			sql = "UPDATE getränk SET anzahl=" + anz + " WHERE name='" + tfGetraenk.getText() + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			listeEinlesen();
		} catch (Exception e) {
			System.out.println("hinzufuegen(): " + e.getMessage());
		}
	}

	public void abziehen() {
		try {
			int minus = Integer.parseInt(tfAnzahl.getText());
			sql = "SELECT anzahl FROM getränk WHERE name='" + tfGetraenk.getText() + "'";
			System.out.println("SELECT anzahl FROM getränk WHERE name='" + tfGetraenk.getText() + "'");
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int anz = rs.getInt("anzahl");
			anz -= minus;
			sql = "UPDATE getränk set Anzahl=" + anz + " WHERE name='" + tfGetraenk.getText() + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			listeEinlesen();
		} catch (Exception e) {
			System.out.println("abziehen(): " + e.getMessage());
		}
	}

	public void nachbestellung() {
		try {
			int anzahl = Integer.parseInt(tfAnzahl.getText());
			String getraenk = tfGetraenk.getText();
			sql = "SELECT firma, straße, ort FROM getränk, lieferant WHERE name='" + getraenk
					+ "' and lieferant_id = getränk_lieferant_id";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println(rs.getString("firma"));
				System.out.println(rs.getString("straße"));
				System.out.println(rs.getString("ort"));
				System.out.println("Wir benötigen " + anzahl + " Kisten " + getraenk + ".");
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("nachbestellung(): " + e.getMessage());
		}
	}

	public void automatischeNachbestellung() {
		try {
			System.out.println("AUTOMATISCHE BESTELLUNG - BEGIN");
			System.out.println("");
			for (int lieferant = 1; lieferant < 10; lieferant++) {
				sql = "SELECT firma, straße, ort FROM lieferant WHERE lieferant_id =" + lieferant;
				//System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String Name = rs.getString("firma");
					String Strasse = rs.getString("straße");
					String Ort = rs.getString("ort");
					sql = "SELECT * FROM getränk WHERE getränk_lieferant_id = " + lieferant;
					//System.out.println(sql);
					rs = stmt.executeQuery(sql);
					boolean bestellungNoetig = false;
					while (rs.next()) {
						int anzahl = rs.getInt("anzahl");
						int min = rs.getInt("min_anzahl");
						int max = rs.getInt("max_anzahl");
						String getraenk = rs.getString("name");
						if (anzahl < min) {
							if (bestellungNoetig == false) {
								System.out.println(Name);
								System.out.println(Strasse);
								System.out.println(Ort);
								bestellungNoetig = true;
							}
							int anfordern = max - anzahl;
							System.out.println("Wir benötigen " + anfordern + " Kisten " + getraenk + ".");
							// Weitere Bestellungen vom selben Lieferanten
						}
					}
					if (bestellungNoetig) {
						System.out.println("");
					}
				}
			}
			System.out.println("AUTOMATISCHE BESTELLUNG - ENDE");
			System.out.println("");
		} catch (Exception e) {
			System.out.println("automatischeNachbestellung(): " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new GetraenkeHandel();
	}
}
