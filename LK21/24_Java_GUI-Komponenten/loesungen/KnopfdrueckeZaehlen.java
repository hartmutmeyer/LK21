import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KnopfdrueckeZaehlen extends JFrame {

	private JPanel contentPane;
	private int zaehler;
	private JLabel lblAnzahlKlicks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KnopfdrueckeZaehlen frame = new KnopfdrueckeZaehlen();
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
	public KnopfdrueckeZaehlen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnKlicker = new JButton("Klick mich - BITTE, BITTE");
		btnKlicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zaehlerHochzaehlen();
			}
		});
		contentPane.add(btnKlicker);
		
		lblAnzahlKlicks = new JLabel("Du hast mich noch nicht geklickt :-(");
		contentPane.add(lblAnzahlKlicks);
	}

	protected void zaehlerHochzaehlen() {
		zaehler++;
		lblAnzahlKlicks.setText("Danke: Du hast mich schon " + zaehler + " Mal geklickt!");
	}

}
