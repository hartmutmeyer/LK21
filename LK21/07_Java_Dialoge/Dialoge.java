import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dialoge extends JFrame {

	private JPanel contentPane;

	public Dialoge() {
		super("Dialoge");
		createGUI();
	}
	
	private void createGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAufg1 = new JButton("Aufgabe 1: Zahl zwischen 1 und 100?");
		btnAufg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg1();
			}
		});
		btnAufg1.setBounds(12, 12, 320, 25);
		contentPane.add(btnAufg1);
		
		JButton btnAufg2 = new JButton("Aufgabe 2: Zahl kleiner als 10 oder größer als 20?");
		btnAufg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg2();
			}
		});
		btnAufg2.setBounds(12, 49, 320, 25);
		contentPane.add(btnAufg2);
		
		JButton btnAufg3 = new JButton("Aufgabe 3: Gerade Zahl?");
		btnAufg3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg3();
			}
		});
		btnAufg3.setBounds(12, 86, 320, 25);
		contentPane.add(btnAufg3);
		
		JButton btnAufg4 = new JButton("Aufgabe 4: Punkte --> Note");
		btnAufg4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg4();
			}
		});
		btnAufg4.setBounds(12, 123, 320, 25);
		contentPane.add(btnAufg4);
		
		JButton btnAufgabeMathetrainer = new JButton("Aufgabe 5: Mathe-Trainer");
		btnAufgabeMathetrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg5();
			}
		});
		btnAufgabeMathetrainer.setBounds(12, 160, 320, 25);
		contentPane.add(btnAufgabeMathetrainer);
		
		JButton btnAufg6 = new JButton("Aufgabe 6: x² + p·x + q = 0 lösen");
		btnAufg6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg6();
			}
		});
		btnAufg6.setBounds(12, 197, 320, 25);
		contentPane.add(btnAufg6);
		
		JButton btnAufg7 = new JButton("Aufgabe 7: Body Maß Index");
		btnAufg7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg7();
			}
		});
		btnAufg7.setBounds(12, 234, 320, 25);
		contentPane.add(btnAufg7);
		
		JButton btnAufg8 = new JButton("Aufgabe 8: Niedersachsenticket");
		btnAufg8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg8();
			}
		});
		btnAufg8.setBounds(12, 271, 320, 25);
		contentPane.add(btnAufg8);
	}

	private void aufg1() {
		// Aufgabe 1
		
	}
	
	private void aufg2() {
		// Aufgabe 2
		
	}
	
	private void aufg3() {
		// Aufgabe 3
		
	}
	
	private void aufg4() {
		// Aufgabe 4
		
	}
	
	private void aufg5() {
		// Aufgabe 5
		
	}
	
	private void aufg6() {
		// Aufgabe 6
		
	}
	
	private void aufg7() {
		// Aufgabe 7
		
	}
	
	private void aufg8() {
		// Aufgabe 8
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dialoge frame = new Dialoge();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
