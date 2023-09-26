import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import java.sql.*;

public class Tierdatenbank4 extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfTierart;
	private JTextField tflebendig;
	private JTextField tfGeschlecht;
	private JTextField tfGeburtstag;
	private JTextField tfTodestag;

	private String name;
	private String tierart;
	private String lebendig;
	private String geschlecht;
	private String geburtstag;
	private String todestag;
	private String cmdSQL;

	public Tierdatenbank4() {
		super("Haustier-Datenbank: Aufgabe 4");
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNeuesTier = new JLabel("Neues Tier");
		lblNeuesTier.setHorizontalAlignment(SwingConstants.CENTER);
		lblNeuesTier.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNeuesTier.setBounds(165, 12, 109, 17);
		contentPane.add(lblNeuesTier);

		JLabel lblName = new JLabel("Name (Pflichfeld):");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(22, 59, 195, 15);
		contentPane.add(lblName);

		JLabel lblTierart = new JLabel("Tierart (Pflichtfeld):");
		lblTierart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTierart.setBounds(22, 86, 195, 15);
		contentPane.add(lblTierart);

		JLabel lblLebendig = new JLabel("lebendig [*ja*,nein]:");
		lblLebendig.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLebendig.setBounds(22, 113, 195, 15);
		contentPane.add(lblLebendig);

		JLabel lblGeschlecht = new JLabel(
				"Geschlecht [männlich,*weiblich*]:");
		lblGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeschlecht.setBounds(22, 140, 195, 15);
		contentPane.add(lblGeschlecht);

		JLabel lblGeburtstag = new JLabel("Geburtstag (YYYY-MM-DD):");
		lblGeburtstag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeburtstag.setBounds(22, 167, 195, 15);
		contentPane.add(lblGeburtstag);

		JLabel lblTodestag = new JLabel("Todestag (YYYY-MM-DD):");
		lblTodestag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTodestag.setBounds(22, 194, 195, 15);
		contentPane.add(lblTodestag);

		tfName = new JTextField();
		lblName.setLabelFor(tfName);
		tfName.setBounds(223, 57, 114, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);

		tfTierart = new JTextField();
		lblTierart.setLabelFor(tfTierart);
		tfTierart.setBounds(223, 84, 114, 19);
		contentPane.add(tfTierart);
		tfTierart.setColumns(10);

		tflebendig = new JTextField();
		lblLebendig.setLabelFor(tflebendig);
		tflebendig.setBounds(223, 111, 114, 19);
		contentPane.add(tflebendig);
		tflebendig.setColumns(10);

		tfGeschlecht = new JTextField();
		lblGeschlecht.setLabelFor(tfGeschlecht);
		tfGeschlecht.setBounds(223, 138, 114, 19);
		contentPane.add(tfGeschlecht);
		tfGeschlecht.setColumns(10);

		tfGeburtstag = new JTextField();
		lblGeburtstag.setLabelFor(tfGeburtstag);
		tfGeburtstag.setBounds(223, 165, 114, 19);
		contentPane.add(tfGeburtstag);
		tfGeburtstag.setColumns(10);

		tfTodestag = new JTextField();
		lblTodestag.setLabelFor(tfTodestag);
		tfTodestag.setBounds(223, 192, 114, 19);
		contentPane.add(tfTodestag);
		tfTodestag.setColumns(10);

		JButton btnHinzufuegen = new JButton("hinzufügen");
		btnHinzufuegen.setBounds(176, 236, 98, 25);
		contentPane.add(btnHinzufuegen);

		btnHinzufuegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				tierHinzufuegen();
			}
		});		
	}

	public void datenbankTierHinzufuegen() {
		int ergebnis = 0;

		try (
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/haustier?serverTimezone=UTC&useSSL=false", "root", "root");	
			Statement stmt = conn.createStatement()
		) {
			System.out.println(cmdSQL);
			ergebnis = stmt.executeUpdate(cmdSQL);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Fehler beim Hinzufügen der Daten",
					JOptionPane.ERROR_MESSAGE);
		}

		if (ergebnis == 1) {
			JOptionPane.showMessageDialog(this,
					"Das Tier wurde erfolgreich hinzugefügt");
		} else {
			JOptionPane.showMessageDialog(this,
					"Das Tier wurde NICHT hinzugefügt");
		}
	}

	public void tierHinzufuegen() {
		// TODO hier Quelltext einfügen
		name = tfName.getText();
		tierart = tfTierart.getText();
		lebendig = tflebendig.getText();
		geschlecht = tfGeschlecht.getText();
		geburtstag = tfGeburtstag.getText();
		todestag = tfTodestag.getText();

		cmdSQL = "INSERT INTO tier VALUES ('" + name + "','" + tierart + "',";

		if (lebendig.equals("")) {
			cmdSQL = cmdSQL + "NULL,";
		} else {
			cmdSQL = cmdSQL + "'" + lebendig + "',";
		}
		if (geschlecht.equals("")) {
			cmdSQL = cmdSQL + "NULL,";
		} else {
			cmdSQL = cmdSQL + "'" + geschlecht + "',";
		}
		if (geburtstag.equals("")) {
			cmdSQL = cmdSQL + "NULL,";
		} else {
			cmdSQL = cmdSQL + "'" + geburtstag + "',";
		}
		if (todestag.equals("")) {
			cmdSQL = cmdSQL + "NULL,NULL)";
		} else {
			cmdSQL = cmdSQL + "'" + todestag + "',NULL)";
		}

		datenbankTierHinzufuegen();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Tierdatenbank4 frame = new Tierdatenbank4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
