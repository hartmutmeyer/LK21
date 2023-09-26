import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Tierdatenbank3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	int tierId;


	public Tierdatenbank3(final String title) {
		super(title);
		createGUI();
	}

	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 315, 121);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tiernummer:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 34, 78, 20);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(108, 33, 67, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Löschen");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datenbankEintragLoeschen();		
			}
		});
		btnNewButton.setBounds(196, 33, 89, 23);
		contentPane.add(btnNewButton);
	}

	public void datenbankEintragLoeschen() {
		try (
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/haustier?serverTimezone=UTC&useSSL=false", "root", "root");
			Statement stmt = conn.createStatement();
		) {
			tierId = Integer.parseInt(textField.getText());
			int antwort = JOptionPane.showConfirmDialog(this, "Soll das Tier mit der Id " + tierId + " wirklich gelöscht werden?");
			if (antwort == JOptionPane.YES_OPTION) {
				System.out.println("wird gelöscht");
				String sql = "DELETE FROM beziehung WHERE beziehung_tier_id = " + tierId;
				System.out.println(sql);
				int ergebnis = stmt.executeUpdate(sql);
				sql = "DELETE FROM tier WHERE tier_id = " + tierId;
				System.out.println(sql);
				ergebnis = stmt.executeUpdate(sql);
				if (ergebnis == 1) {
					JOptionPane.showMessageDialog(this, "Das Tier wurde erfolgreich gelöscht.");
				} else {
					JOptionPane.showMessageDialog(this, "Das Tier gab es gar nicht.");
				}
			} else {
				System.out.println("Löschen abgebrochen");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Fehlermeldung");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Tierdatenbank3 frame = new Tierdatenbank3("Haustier-Datenbank: Aufgabe 3");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
