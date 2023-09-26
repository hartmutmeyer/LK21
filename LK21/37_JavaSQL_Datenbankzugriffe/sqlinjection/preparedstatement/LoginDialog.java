import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfKontonummer;
	private JTextField tfPasswort;
	private OnlineBanking parent;

	public LoginDialog(OnlineBanking parent) {
		super(parent, true);
		this.parent = parent;
		setTitle("CashBank Kunden-Login");
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblKontonummer = new JLabel("Kontonummer:");
		lblKontonummer.setBounds(70, 24, 125, 15);
		contentPanel.add(lblKontonummer);
		tfKontonummer = new JTextField();
		tfKontonummer.setBounds(213, 24, 114, 19);
		contentPanel.add(tfKontonummer);
		tfKontonummer.setColumns(10);
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setBounds(70, 61, 125, 15);
		contentPanel.add(lblPasswort);
		tfPasswort = new JTextField();
		tfPasswort.setBounds(213, 61, 114, 19);
		contentPanel.add(tfPasswort);
		tfPasswort.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anmelden();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abbrechen();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private void anmelden() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinebanking?serverTimezone=UTC&useSSL=false", "root", "root");
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM konto WHERE kontonummer = ? AND passwort = ?")) {
			ps.setString(1, tfKontonummer.getText());
			ps.setString(2, tfPasswort.getText());
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				parent.kontonummer = rs.getInt("kontonummer");
				parent.kunde = rs.getString("anrede") + " " + rs.getString("vorname") + " " + rs.getString("name");
				parent.kontostand = rs.getString("kontostand");
				parent.zustand = OnlineBanking.ANGEMELDET;
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Unbekannter Kunde oder falsches Passwort!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void abbrechen() {
		this.dispose();
	}

}
