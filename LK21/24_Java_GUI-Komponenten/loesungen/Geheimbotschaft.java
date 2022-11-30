import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Geheimbotschaft extends JFrame {

	private JPanel contentPane;
	private JTextField tfEingabe;
	private JTextField tfErgebnis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Geheimbotschaft frame = new Geheimbotschaft();
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
	public Geheimbotschaft() {
		setTitle("Geheimbotschaft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEingabe = new JLabel("Eingabe:");
		lblEingabe.setBounds(12, 12, 55, 15);
		contentPane.add(lblEingabe);
		
		tfEingabe = new JTextField();
		tfEingabe.setBounds(12, 39, 579, 19);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		JLabel lblGeheimbotschaft = new JLabel("Geheimbotschaft:");
		lblGeheimbotschaft.setBounds(12, 109, 120, 15);
		contentPane.add(lblGeheimbotschaft);
		
		JButton btnAuswerten = new JButton("auswerten");
		btnAuswerten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entschluesseln();
			}
		});
		btnAuswerten.setBounds(254, 70, 98, 25);
		contentPane.add(btnAuswerten);
		
		tfErgebnis = new JTextField();
		tfErgebnis.setEditable(false);
		tfErgebnis.setBounds(141, 107, 450, 19);
		contentPane.add(tfErgebnis);
		tfErgebnis.setColumns(10);
	}

	private void entschluesseln() {
		String eingabe = tfEingabe.getText().toLowerCase();
		String stripped = "";
		String ergebnis = "";
		char c;
		for (int i = 0; i < eingabe.length(); i++) {
			c = eingabe.charAt(i);
			if (Character.isLetter(c)) {
				stripped += c;
			}
		}
		for (int i = 0; i < stripped.length() - 1; i++) {
			if (stripped.charAt(i) == 'e') {
				ergebnis += stripped.charAt(i + 1);
			}
		}
		tfErgebnis.setText(ergebnis);
	}

}
