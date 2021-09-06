import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TurtleGrafik extends JFrame {

	private JPanel contentPane;
	private Turtle t;

	public TurtleGrafik() {
		super("Turtle-Grafik");
		createGUI();
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAufg1 = new JButton("Aufg1");
		btnAufg1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg1();
			}
		});
		btnAufg1.setBounds(12, 12, 67, 25);
		contentPane.add(btnAufg1);
		
		JButton btnAufg2 = new JButton("Aufg2");
		btnAufg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg2();
			}
		});
		btnAufg2.setBounds(91, 12, 67, 25);
		contentPane.add(btnAufg2);
		
		JButton btnAufg3 = new JButton("Aufg3");
		btnAufg3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg3();
			}
		});
		btnAufg3.setBounds(170, 12, 67, 25);
		contentPane.add(btnAufg3);
		
		JButton btnAufg4 = new JButton("Aufg4");
		btnAufg4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg4();
			}
		});
		btnAufg4.setBounds(249, 12, 67, 25);
		contentPane.add(btnAufg4);
		
		JButton btnAufg5 = new JButton("Aufg5");
		btnAufg5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg5();
			}
		});
		btnAufg5.setBounds(328, 12, 67, 25);
		contentPane.add(btnAufg5);
		
		JButton btnAufg6 = new JButton("Aufg6");
		btnAufg6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg6();
			}
		});
		btnAufg6.setBounds(407, 12, 67, 25);
		contentPane.add(btnAufg6);
		
		JButton btnAufg7 = new JButton("Aufg7");
		btnAufg7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg7();
			}
		});
		btnAufg7.setBounds(486, 12, 67, 25);
		contentPane.add(btnAufg7);
		
		JButton btnAufg8 = new JButton("Aufg8");
		btnAufg8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg8();
			}
		});
		btnAufg8.setBounds(565, 12, 67, 25);
		contentPane.add(btnAufg8);
		
		JButton btnAufg9 = new JButton("Aufg9");
		btnAufg9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg9();
			}
		});
		btnAufg9.setBounds(644, 12, 67, 25);
		contentPane.add(btnAufg9);
		
		JButton btnAufg10 = new JButton("Aufg10");
		btnAufg10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg10();
			}
		});
		btnAufg10.setBounds(723, 12, 74, 25);
		contentPane.add(btnAufg10);
		
		JButton btnAufg11 = new JButton("Aufg11");
		btnAufg11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aufg11();
			}
		});
		btnAufg11.setBounds(809, 12, 74, 25);
		contentPane.add(btnAufg11);
		
		t = new Turtle(350, 300);
		t.setBounds(12, 48, 769, 609);
		contentPane.add(t);
		
		JButton btnLoeschen = new JButton("löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.loeschen();
				t.neuStart(350, 300);
			}
		});
		btnLoeschen.setBounds(793, 49, 98, 609);
		contentPane.add(btnLoeschen);
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
	
	private void aufg9() {
		// Aufgabe 9
		
	}
	
	private void aufg10() {
		// Aufgabe 10
		
	}
	
	private void aufg11() {
		// Aufgabe 11
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TurtleGrafik frame = new TurtleGrafik();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
