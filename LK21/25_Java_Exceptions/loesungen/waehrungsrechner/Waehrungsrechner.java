import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Waehrungsrechner extends JFrame {
	// globale Variablen
	private static final int WIDTH = 550;
	private static final int HEIGHT = 120;
	private JLabel lblEuro = new JLabel();
	private JTextField tfEuro = new JTextField();
	private JLabel lblKurs = new JLabel();
	private JTextField tfKurs = new JTextField();
	private JButton btnUmrechnen = new JButton();
	private JLabel lblGBP = new JLabel();
	private JTextField tfGBP = new JTextField();
	
	public Waehrungsrechner(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblEuro.setBounds(18, 16, 31, 16);
		lblEuro.setText("Euro:");
		contentPane.add(lblEuro);
		tfEuro.setBounds(86, 13, 145, 24);
		contentPane.add(tfEuro);
		lblKurs.setBounds(249, 16, 111, 16);
		lblKurs.setText("Umrechnungskurs:");
		contentPane.add(lblKurs);
		tfKurs.setBounds(392, 13, 138, 24);
		contentPane.add(tfKurs);
		btnUmrechnen.setBounds(18, 52, 105, 25);
		btnUmrechnen.setText("Umrechnen");
		contentPane.add(btnUmrechnen);
		btnUmrechnen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				umrechnen();
			}
		});

		lblGBP.setBounds(144, 56, 103, 17);
		lblGBP.setText("Britische Pfund:");
		contentPane.add(lblGBP);
		tfGBP.setBounds(249, 49, 281, 24);
		tfGBP.setEditable(false);
		contentPane.add(tfGBP);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	protected void umrechnen() {
		double ergebnis;
		try {
			ergebnis = zahlAuslesen(tfEuro)  * zahlAuslesen(tfKurs);
			tfGBP.setText("" + ergebnis);
		} catch (LeerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (KommaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (ZeichenException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}

	private double zahlAuslesen(JTextField textfeld) throws LeerException, KommaException, ZeichenException {
		char c;
		if (textfeld.getText().isEmpty()) {
			LeerException feler;
			feler = new LeerException();
			throw feler;
		}
		if (textfeld.getText().contains(",")) {
			KommaException feler;
			feler = new KommaException();
			throw feler;			
		}
		String text = textfeld.getText();
		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			if (!(Character.isDigit(c) || c == '.')) {
				ZeichenException feler;
				feler = new ZeichenException();
				throw feler;
			}
		}
		return Double.parseDouble(textfeld.getText());
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Waehrungsrechner("Währungsrechner");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}