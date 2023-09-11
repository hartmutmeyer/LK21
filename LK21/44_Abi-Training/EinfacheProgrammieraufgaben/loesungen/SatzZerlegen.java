import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SatzZerlegen extends JFrame {

	private JPanel contentPane;
	private JTextField tfEingabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SatzZerlegen frame = new SatzZerlegen();
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
	public SatzZerlegen() {
		setTitle("Aufgabe 1: Satz in Wörter zerlegen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfEingabe = new JTextField();
		tfEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satzInWoerterZerlegenMitSplit();
			}
		});
		tfEingabe.setBounds(12, 12, 418, 19);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		JButton btnSatzZerlegen = new JButton("Satz in Wörter zerlegen");
		btnSatzZerlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satzInWoerterZerlegen();
			}
		});
		btnSatzZerlegen.setBounds(12, 43, 418, 25);
		contentPane.add(btnSatzZerlegen);
	}

	private void satzInWoerterZerlegen() {
		String satz = tfEingabe.getText().trim();    // Eingabe ohne Leerzeichen am Anfang und Ende
		String wort = "";
		char zeichen;
		for (int i = 0; i < satz.length(); i++) {
			if ((zeichen = satz.charAt(i)) != ' ') { // "sammeln" bis zum nächsten Leerzeichen
				wort += zeichen;
			} else {
				System.out.println(wort);
				wort = "";
			}
		}
		System.out.println(wort);                    // Das letzte Wort wurde noch nicht ausgegeben ... 
	}
	
	private void satzInWoerterZerlegenMitSplit() {
		String[] woerter = tfEingabe.getText().split(" ");
		for (String wort: woerter) {
			System.out.println(wort);
		}
	}
}
