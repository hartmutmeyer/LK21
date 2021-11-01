import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rekursion3 extends JFrame {

	private JPanel contentPane;
	private JTextField tfStufe;
	private Turtle t = new Turtle(350, 300);

	public Rekursion3() {
		super("Rekursion 3");
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStufe = new JLabel("Stufe:");
		lblStufe.setBounds(12, 17, 50, 15);
		contentPane.add(lblStufe);
		
		tfStufe = new JTextField();
		tfStufe.setBounds(80, 15, 50, 19);
		contentPane.add(tfStufe);
		tfStufe.setColumns(10);
		
		JButton btnAufg1 = new JButton("Aufg1");
		btnAufg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg1();
			}
		});
		btnAufg1.setBounds(142, 12, 67, 25);
		contentPane.add(btnAufg1);
		
		JButton btnAufg2 = new JButton("Aufg2");
		btnAufg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg2();
			}
		});
		btnAufg2.setBounds(221, 12, 67, 25);
		contentPane.add(btnAufg2);
		
		JButton btnAufg3 = new JButton("Aufg3");
		btnAufg3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg3();
			}
		});
		btnAufg3.setBounds(300, 12, 67, 25);
		contentPane.add(btnAufg3);
		
		JButton btnAufg4 = new JButton("Aufg4");
		btnAufg4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg4();
			}
		});
		btnAufg4.setBounds(379, 12, 67, 25);
		contentPane.add(btnAufg4);
		
		JButton btnAufg5 = new JButton("Aufg5");
		btnAufg5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg5();
			}
		});
		btnAufg5.setBounds(458, 12, 67, 25);
		contentPane.add(btnAufg5);
		
		JButton btnAufg6 = new JButton("Aufg6");
		btnAufg6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg6();
			}
		});
		btnAufg6.setBounds(537, 12, 67, 25);
		contentPane.add(btnAufg6);
		
		JButton btnAufg7 = new JButton("Aufg7");
		btnAufg7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg7();
			}
		});
		btnAufg7.setBounds(616, 12, 67, 25);
		contentPane.add(btnAufg7);
		
		JButton btnLoeschen = new JButton("löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.loeschen();
				t.neuStart(350, 300);
			}
		});
		btnLoeschen.setBounds(698, 12, 79, 25);
		contentPane.add(btnLoeschen);
		
		t.setBackground(Color.WHITE);
		t.setBounds(12, 49, 769, 609);
		contentPane.add(t);
	}
	
	private int getStufen() {
		int stufen;
		try {
			stufen = Integer.parseInt(tfStufe.getText());
		} catch (Exception e) {
			stufen = 1;
		}
		if (stufen <= 0) {
			stufen = 1;
		}
		return stufen;
	}

	// Aufgabe 1:
	
	private void koch(double laenge, int stufen) {
		if (stufen == 1) {
			t.vor(laenge);
		} else {
			double lNeu = laenge / 3;
			koch(lNeu, stufen - 1);
			t.drehen(60);
			koch(lNeu, stufen - 1);
			t.drehen(-120);
			koch(lNeu, stufen - 1);
			t.drehen(60);
			koch(lNeu, stufen - 1);
		}
	}
	
	private void aufg1 () {
		t.neuStart(100, 300);
		t.drehen(-90);
		koch(500, getStufen());
	}

	// Aufgabe 2:
	
	private void aufg2() {
		t.neuStart(100, 450);
		int laenge = 500;
		int stufen = getStufen();
		t.drehen(-30);
		koch(laenge, stufen);
		t.drehen(-120);
		koch(laenge, stufen);
		t.drehen(-120);
		koch(laenge, stufen);
	}
	
	// Aufgabe 3:
	
	private void aufg3() {
		t.neuStart(100, 500);
		int laenge = 500;
		int stufen = getStufen();
		t.drehen(-90);
		koch(laenge, stufen);
		t.drehen(120);
		koch(laenge, stufen);
		t.drehen(120);
		koch(laenge, stufen);
	}
	
	// Aufgabe 4:
	
	private void aufg4() {
		t.neuStart(100, 550);
		int laenge = 500;
		int stufen = getStufen();
		t.drehen(-90);
		koch(laenge, stufen);
		t.drehen(90);
		koch(laenge, stufen);
		t.drehen(90);
		koch(laenge, stufen);
		t.drehen(90);
		koch(laenge, stufen);
	}
	
	// Aufgabe 5:
	
	private void koch2(double laenge, int stufen) {
		if (stufen == 1) {
			t.vor(laenge);
		} else {
			double neuLaenge = laenge / 3;
			koch2(neuLaenge, stufen - 1);
			t.drehen(60);
			koch2(neuLaenge, stufen - 1);
			t.drehen(60);
			koch2(neuLaenge, stufen - 1);
			t.drehen(-120);
			koch2(neuLaenge, stufen - 1);
			t.drehen(-120);
			koch2(neuLaenge, stufen - 1);
			t.drehen(60);
			koch2(neuLaenge, stufen - 1);
			t.drehen(60);
			koch2(neuLaenge, stufen - 1);
		}
	}

	private void aufg5() {
		t.neuStart(100, 550);
		t.drehen(-90);
		koch2(500, getStufen());
	}
	
	// Aufgabe 6:
	
	private void sierpinski(double laenge, int stufen) {
		for (int i = 0; i < 3; i++) {
			t.vor(laenge);
			if (stufen > 1)
				sierpinski(laenge / 2, stufen - 1);
			t.vor(-laenge);
			t.drehen(120);
		}
	}

	private void aufg6() {
		t.neuStart(350, 380);
		sierpinski(150, getStufen());
	}
	
	// Aufgabe 7:
	
	private void pythagoras(double laenge, int winkel, int stufe) {
		t.vor(laenge);
		t.drehen(90);
		t.vor(laenge);
		t.drehen(90);
		t.vor(laenge);
		t.drehen(-180 + winkel);
		double laenge1 = Math.cos(Math.PI * winkel / 180) * laenge;
		if (stufe > 1)
			pythagoras(laenge1, winkel, stufe - 1);
		else
			t.vor(laenge1);
		t.drehen(-90);
		double laenge2 = Math.sin(Math.PI * winkel / 180) * laenge;
		if (stufe > 1)
			pythagoras(laenge2, winkel, stufe - 1);
		else
			t.vor(laenge2);
		t.drehen(-90 - winkel);
		t.vor(laenge);
		t.drehen(90);
		t.vor(laenge);
		t.drehen(90);
		t.vor(laenge);
	}

	private void aufg7() {
		t.neuStart(400, 550);
		t.drehen(-90);
		pythagoras(120, 35, getStufen());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rekursion3 frame = new Rekursion3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
