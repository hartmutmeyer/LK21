import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FahrzeugeHinzufuegen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKFZKennzeichen;
	private JTextField textFieldTyp;
	private JTextField textFieldFarbe;
	private JTextField textFieldBaujahr;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FahrzeugeHinzufuegen frame = new FahrzeugeHinzufuegen();
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
	public FahrzeugeHinzufuegen() {
		super("Fahrzeuge Hinzufügen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 10, 5));

		JLabel lblKFZKennzeichen = new JLabel("KFZ-Kennzeichen:");
		lblKFZKennzeichen.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblKFZKennzeichen);

		textFieldKFZKennzeichen = new JTextField();
		lblKFZKennzeichen.setLabelFor(textFieldKFZKennzeichen);
		panel.add(textFieldKFZKennzeichen);
		textFieldKFZKennzeichen.setColumns(10);

		JLabel lblTyp = new JLabel("Typ:");
		lblTyp.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTyp);

		textFieldTyp = new JTextField();
		lblTyp.setLabelFor(textFieldTyp);
		textFieldTyp.setText("");
		panel.add(textFieldTyp);
		textFieldTyp.setColumns(10);

		JLabel lblFarbe = new JLabel("Farbe:");
		lblFarbe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblFarbe);

		textFieldFarbe = new JTextField();
		lblFarbe.setLabelFor(textFieldFarbe);
		textFieldFarbe.setText("");
		panel.add(textFieldFarbe);
		textFieldFarbe.setColumns(10);

		JLabel lblBaujahr = new JLabel("Baujahr:");
		lblBaujahr.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblBaujahr);

		textFieldBaujahr = new JTextField();
		lblBaujahr.setLabelFor(textFieldBaujahr);
		textFieldBaujahr.setText("");
		panel.add(textFieldBaujahr);
		textFieldBaujahr.setColumns(10);

		JButton btnFahrzeugHinzufgen = new JButton("Fahrzeug hinzufügen");
		btnFahrzeugHinzufgen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datenbankFahrzeugHinzufuegen();
			}
		});
		contentPane.add(btnFahrzeugHinzufgen, BorderLayout.SOUTH);

		datenbankOeffnen();
	}

	public void datenbankFahrzeugHinzufuegen() {
		int ergebnis = 0;
		boolean kfzZeichenNochNichtVergeben = false;
		String cmdSQL;
		Statement stmt;
		ResultSet rs = null;

		cmdSQL = "SELECT kfz_zeichen FROM fahrzeughalter WHERE kfz_zeichen='"
				+ textFieldKFZKennzeichen.getText()
				+ "' AND abgemeldet IS NULL";
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
			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "Es gibt bereits ein angemeldetes Fahrzeug mit diesem KFZ-Zeichen!");
			} else {
				kfzZeichenNochNichtVergeben = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		if (kfzZeichenNochNichtVergeben) {
			cmdSQL = "INSERT INTO fahrzeug VALUES ('"
					+ textFieldKFZKennzeichen.getText() + "','"
					+ textFieldTyp.getText() + "','" + textFieldFarbe.getText()
					+ "'," + textFieldBaujahr.getText() + ")";

			System.out.println(cmdSQL);

			try {
				stmt = conn.createStatement();
				ergebnis = stmt.executeUpdate(cmdSQL);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (ergebnis == 1) {
				JOptionPane.showMessageDialog(this, "Das Fahrzeug mit dem KFZ-Zeichen "
								+ textFieldKFZKennzeichen.getText()
								+ " wurde hinzugefügt.");
			} else {
				System.out.println("Es gibt bereits ein angemeldetes Fahrzeug mit diesem KFZ-Zeichen!");
			}
		}
	}

	private void datenbankOeffnen() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/fahrzeuge?serverTimezone=UTC&useSSL=false", "root", "root");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
