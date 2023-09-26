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
import java.sql.SQLException;

public class PasswortAendernDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNeuesPasswort;
	private OnlineBanking parent;

	public PasswortAendernDialog(OnlineBanking parent) {
		super(parent, true);
		this.parent = parent;
		setTitle("CashBank: Passwort ändern");
		setBounds(100, 100, 700, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNeuesPasswort = new JLabel("Neues Passwort:");
		lblNeuesPasswort.setBounds(12, 12, 125, 15);
		contentPanel.add(lblNeuesPasswort);

		tfNeuesPasswort = new JTextField();
		tfNeuesPasswort.setBounds(155, 10, 529, 19);
		contentPanel.add(tfNeuesPasswort);
		tfNeuesPasswort.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwortAendern();
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

	protected void passwortAendern() {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/onlinebanking?serverTimezone=UTC&useSSL=false", "root", "root");
				PreparedStatement ps = conn.prepareStatement("UPDATE konto SET passwort = ? WHERE kontonummer = ?")) {
			ps.setString(1, tfNeuesPasswort.getText());
			ps.setInt(2, parent.kontonummer);
			System.out.println(ps.toString());
			int erfolg = ps.executeUpdate();
			if (erfolg == 1) {
				JOptionPane.showMessageDialog(this, "Passwort erfolgreich geändert ...");
				parent.anzeigen();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "", "Fehler: Passwort konnte nicht geändert werden ...", JOptionPane.ERROR_MESSAGE);
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
