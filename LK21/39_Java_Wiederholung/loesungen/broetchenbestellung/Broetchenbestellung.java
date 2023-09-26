import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Broetchenbestellung extends JFrame {
	// globale Variablen
	private static final int WIDTH = 393;
	private static final int HEIGHT = 192;
	private JCheckBox cbVollkorn = new JCheckBox();
	private JLabel lblAnzahl = new JLabel();
	private JTextField tfAnzahl = new JTextField();
	private JButton btnBerechnen = new JButton();
	private JLabel lblPreis = new JLabel();
	private JTextField tfPreis = new JTextField();

	public Broetchenbestellung(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		cbVollkorn.setBounds(240, 32, 145, 17);
		cbVollkorn.setText("Vollkornbrötchen");
		contentPane.add(cbVollkorn);
		lblAnzahl.setBounds(24, 32, 109, 16);
		lblAnzahl.setText("Anzahl Brötchen:");
		contentPane.add(lblAnzahl);
		tfAnzahl.setBounds(136, 24, 81, 24);
		contentPane.add(tfAnzahl);
		btnBerechnen.setBounds(112, 72, 139, 25);
		btnBerechnen.setText("Preis berechnen");
		btnBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnPreisBerechnen_ActionPerformed(evt);
			}
		});
		contentPane.add(btnBerechnen);
		lblPreis.setBounds(16, 128, 88, 16);
		lblPreis.setText("Preis in Euro:");
		contentPane.add(lblPreis);
		tfPreis.setBounds(112, 120, 137, 24);
		tfPreis.setEditable(false);
		contentPane.add(tfPreis);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void btnPreisBerechnen_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfügen
		int anzahl = 0;
		try {
			anzahl = Integer.parseInt(tfAnzahl.getText());
			if (anzahl < 1 || anzahl > 100) {
				throw new Exception();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
							"Bitte geben sie eine positive ganze Zahl zwischen 1 und 100 ein.");
			return;
		}
		double preis;
		if (cbVollkorn.isSelected() == true) {
			preis = anzahl * 0.6;
		} else {
			preis = anzahl * 0.3;
		}
		tfPreis.setText("" + preis);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Broetchenbestellung("Brötchen-Bestellung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
