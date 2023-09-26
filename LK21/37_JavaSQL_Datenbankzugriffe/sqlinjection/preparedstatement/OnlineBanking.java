import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnlineBanking extends JFrame {

	private JPanel contentPane;
	public final static int ABGEMELDET = 0;
	public final static int ANGEMELDET = 1;
	public int zustand = ABGEMELDET;
	private JButton btnAnmelden;
	private JButton btnPasswortAendern;
	private JTextField tfKontonummer;
	private JTextField tfKunde;
	private JTextField tfKontostand;
	public int kontonummer;
	public String kunde;
	public String kontostand;
	private LoginDialog anmeldeDialog;
	private PasswortAendernDialog passwortAendernDialog;

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAnmelden = new JButton("anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anmelden();
			}
		});
		btnAnmelden.setBounds(264, 12, 170, 25);
		contentPane.add(btnAnmelden);

		btnPasswortAendern = new JButton("Passwort ändern");
		btnPasswortAendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwortAendern();
			}
		});
		btnPasswortAendern.setEnabled(false);
		btnPasswortAendern.setBounds(264, 49, 170, 25);
		contentPane.add(btnPasswortAendern);

		JLabel lblKontonummer = new JLabel("Kontonummer:");
		lblKontonummer.setBounds(12, 101, 125, 15);
		contentPane.add(lblKontonummer);

		tfKontonummer = new JTextField();
		tfKontonummer.setEditable(false);
		tfKontonummer.setBounds(155, 99, 114, 19);
		contentPane.add(tfKontonummer);
		tfKontonummer.setColumns(10);

		JLabel lblKunde = new JLabel("Kunde:");
		lblKunde.setBounds(12, 128, 70, 15);
		contentPane.add(lblKunde);

		tfKunde = new JTextField();
		tfKunde.setEditable(false);
		tfKunde.setBounds(155, 126, 279, 19);
		contentPane.add(tfKunde);
		tfKunde.setColumns(10);

		JLabel lblKontostand = new JLabel("Kontostand:");
		lblKontostand.setBounds(12, 155, 125, 15);
		contentPane.add(lblKontostand);

		tfKontostand = new JTextField();
		tfKontostand.setEditable(false);
		tfKontostand.setBounds(155, 153, 114, 19);
		contentPane.add(tfKontostand);
		tfKontostand.setColumns(10);
	}

	private void anmelden() {
		if (zustand == ABGEMELDET) {
			try {
				anmeldeDialog = new LoginDialog(this);
				anmeldeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				anmeldeDialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (zustand == ANGEMELDET) {
				btnAnmelden.setText("abmelden");
				btnPasswortAendern.setEnabled(true);
				tfKontonummer.setText("" + kontonummer);
				tfKunde.setText(kunde);
				tfKontostand.setText(kontostand);
			}
		} else {
			btnAnmelden.setText("anmelden");
			btnPasswortAendern.setEnabled(false);
			tfKontonummer.setText("");
			tfKunde.setText("");
			tfKontostand.setText("");
			zustand = ABGEMELDET;
		}
	}
	
	private void passwortAendern() {
		try {
			passwortAendernDialog = new PasswortAendernDialog(this);
			passwortAendernDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			passwortAendernDialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void anzeigen() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinebanking?serverTimezone=UTC&useSSL=false", "root", "root");
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM konto WHERE kontonummer = ?")) {
			ps.setInt(1, kontonummer);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kontonummer = rs.getInt("kontonummer");
				kunde = rs.getString("anrede") + " " + rs.getString("vorname") + " " + rs.getString("name");
				kontostand = rs.getString("kontostand");
				tfKontonummer.setText("" + kontonummer);
				tfKunde.setText(kunde);
				tfKontostand.setText(kontostand);
			} else {
				System.out.println("OnlineBanking: unbekanntes Konto (darf nicht passieren)!?");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public OnlineBanking() {
		super("CashBank Online-Banking");
		createGUI();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineBanking frame = new OnlineBanking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
