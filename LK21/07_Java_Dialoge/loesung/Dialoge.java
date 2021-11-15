import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg1();
			}
		});
		btnAufg1.setBounds(12, 12, 320, 25);
		contentPane.add(btnAufg1);

		JButton btnAufg2 = new JButton("Aufgabe 2: Zahl kleiner als 10 oder größer als 20?");
		btnAufg2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg2();
			}
		});
		btnAufg2.setBounds(12, 49, 320, 25);
		contentPane.add(btnAufg2);

		JButton btnAufg3 = new JButton("Aufgabe 3: Gerade Zahl?");
		btnAufg3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg3();
			}
		});
		btnAufg3.setBounds(12, 86, 320, 25);
		contentPane.add(btnAufg3);

		JButton btnAufg4 = new JButton("Aufgabe 4: Punkte --> Note");
		btnAufg4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg4();
			}
		});
		btnAufg4.setBounds(12, 123, 320, 25);
		contentPane.add(btnAufg4);

		JButton btnAufgabeMathetrainer = new JButton("Aufgabe 5: Mathe-Trainer");
		btnAufgabeMathetrainer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg5();
			}
		});
		btnAufgabeMathetrainer.setBounds(12, 160, 320, 25);
		contentPane.add(btnAufgabeMathetrainer);

		JButton btnAufg6 = new JButton("Aufgabe 6: x² + p·x + q = 0 lösen");
		btnAufg6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg6();
			}
		});
		btnAufg6.setBounds(12, 197, 320, 25);
		contentPane.add(btnAufg6);

		JButton btnAufg7 = new JButton("Aufgabe 7: Body Maß Index");
		btnAufg7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg7();
			}
		});
		btnAufg7.setBounds(12, 234, 320, 25);
		contentPane.add(btnAufg7);

		JButton btnAufg8 = new JButton("Aufgabe 8: Niedersachsenticket");
		btnAufg8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aufg8();
			}
		});
		btnAufg8.setBounds(12, 271, 320, 25);
		contentPane.add(btnAufg8);
	}

	private boolean zwischen1und100(int zahl) {
		if (zahl > 0 && zahl < 101) {
			return true;
		} else {
			return false;
		}
	}

	private void aufg1() {
		// Aufgabe 1
		String text = JOptionPane.showInputDialog("Gib eine Zahl ein");
		int zahl = Integer.parseInt(text);
		if (zwischen1und100(zahl)) {
			JOptionPane.showMessageDialog(this, "Die Zahl liegt im Bereich von Eins bis Hundert.");
		} else {
			JOptionPane.showMessageDialog(this, "Die Zahl ist kleiner als Eins oder größer als Hundert.");
		}
	}

	private boolean kleiner10oderGroesser20(double zahl) {
		if (zahl < 10 || zahl > 20) {
			return true;
		} else {
			return false;
		}
	}

	private void aufg2() {
		// Aufgabe 2
		String text = JOptionPane.showInputDialog("Gib eine Zahl ein");
		double zahl = Double.parseDouble(text);
		if (kleiner10oderGroesser20(zahl)) {
			JOptionPane.showMessageDialog(this, "Die Zahl ist kleiner als 10 oder größer als 20.");
		} else {
			JOptionPane.showMessageDialog(this, "Die Zahl liegt im Bereich zwischen 10 und 20.");
		}
	}

	private boolean istGerade(int zahl) {
		if (zahl % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void aufg3() {
		// Aufgabe 3
		String text = JOptionPane.showInputDialog("Gib eine ganze Zahl ein");
		int zahl = Integer.parseInt(text);
		if (istGerade(zahl)) {
			JOptionPane.showMessageDialog(this, zahl + " ist gerade");
		} else {
			JOptionPane.showMessageDialog(this, zahl + " ist ungerade");
		}
	}

	private void aufg4() {
		// Aufgabe 4
		String text;
		int punkte;

		while ((text = JOptionPane.showInputDialog("Notenpunkte?")) != null) {
			punkte = Integer.parseInt(text);
			JOptionPane.showMessageDialog(this, text + " Notenpunkte entsprechen der Note " + punkte2note(punkte));
		}
	}

	private int punkte2note(int punkte) {
		switch (punkte) {
		case 0:
			return 6;
		case 1:
		case 2:
		case 3:
			return 5;
		case 4:
		case 5:
		case 6:
			return 4;
		case 7:
		case 8:
		case 9:
			return 3;
		case 10:
		case 11:
		case 12:
			return 2;
		case 13:
		case 14:
		case 15:
			return 1;
		default:
			return -1;
		}

	}

	private void aufg5() {
		// Aufgabe 5
		for (int i1 = 5; i1 <= 7; i1++) {
			for (int i2 = 7; i2 <= 9; i2++) {
				while (Integer.parseInt(JOptionPane.showInputDialog(i1 + "*" + i2 + " = ?")) != i1 * i2) {
					JOptionPane.showMessageDialog(this, "Falsch. Bitte noch mal probieren!");
				}
				JOptionPane.showMessageDialog(this, "Korrekt.");
			}
		}
	}

	private void aufg6() {
		// Aufgabe 6
		double wurzel, x1, x2, p, q, radikand;

		p = Double.parseDouble(JOptionPane.showInputDialog("p = ?"));
		q = Double.parseDouble(JOptionPane.showInputDialog("q = ?"));
		radikand = Math.pow(p / 2, 2) - q;
		if (radikand < 0) {
			JOptionPane.showMessageDialog(this, "keine Lösung");
		} else {
			if (radikand == 0) {
				JOptionPane.showMessageDialog(this, "x = " + -p / 2);
			} else {
				wurzel = Math.sqrt(radikand);
				x1 = -p / 2 + wurzel;
				x2 = -p / 2 - wurzel;
				JOptionPane.showMessageDialog(this, "x = " + x1 + " oder x = " + x2);
			}
		}
	}

	private void aufg7() {
		// Aufgabe 7
		double groesse, gewicht, bmi;

		groesse = Double.parseDouble(JOptionPane.showInputDialog("Gib deine Größe in Metern ein:"));
		gewicht = Double.parseDouble(JOptionPane.showInputDialog("Gib dein Gewicht in Kilogramm ein:"));
		bmi = gewicht / Math.pow(groesse, 2);
		if (bmi < 18.5) {
			JOptionPane.showMessageDialog(this, "Untergewicht");
		} else {
			if (bmi > 25) {
				JOptionPane.showMessageDialog(this, "Übergewicht");
			} else {
				JOptionPane.showMessageDialog(this, "Normalgewicht");
			}
		}
	}

	private void aufg8() {
		// Aufgabe 8
		int personen;
		String eingabe;

		while ((eingabe = JOptionPane.showInputDialog("Wie viele Personen fahren mit?")) != null) {
			personen = Integer.parseInt(eingabe);
			JOptionPane.showMessageDialog(this, "Jeder zahlt " + fahrpreisProPerson(personen) + " EUR");
		}
	}

	private double fahrpreisProPerson(int anzahlPersonen) {
		return (28 * Math.ceil(anzahlPersonen / 5.0)) / anzahlPersonen;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
