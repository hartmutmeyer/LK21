import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rekursion1 extends JFrame {

	private JPanel contentPane;
	private JTextField tfFakultaetVon;
	private JTextField tfErgebnisFakultaet;
	private JTextField tfZahlenfolge;
	private JTextField tfErgebnisZahlenfolge;
	private JTextField tfDinA;
	private JTextField tfBreite;
	private JTextField tfHoehe;

	/**
	 * Create the frame.
	 */
	public Rekursion1() {
		super("Rekursion1");
		createGUI();
	}
	
	public void createGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFakultaetVon = new JLabel("Fakultät von");
		lblFakultaetVon.setBounds(12, 12, 110, 15);
		contentPane.add(lblFakultaetVon);
		
		tfFakultaetVon = new JTextField();
		tfFakultaetVon.setBounds(140, 10, 50, 19);
		contentPane.add(tfFakultaetVon);
		tfFakultaetVon.setColumns(10);
		
		JButton btnBerechnenFakultaet = new JButton("berechnen");
		btnBerechnenFakultaet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfErgebnisFakultaet.setText("" + fakultaet(getEingabe(tfFakultaetVon)));
			}
		});
		btnBerechnenFakultaet.setBounds(202, 7, 98, 25);
		contentPane.add(btnBerechnenFakultaet);
		
		JLabel lblErgebnisFakultaet = new JLabel("Ergebnis:");
		lblErgebnisFakultaet.setBounds(318, 12, 55, 15);
		contentPane.add(lblErgebnisFakultaet);
		
		tfErgebnisFakultaet = new JTextField();
		tfErgebnisFakultaet.setEditable(false);
		tfErgebnisFakultaet.setBounds(391, 10, 50, 19);
		contentPane.add(tfErgebnisFakultaet);
		tfErgebnisFakultaet.setColumns(10);
		
		JLabel lblZahlenfolge = new JLabel("Zahlenfolge");
		lblZahlenfolge.setBounds(12, 49, 110, 15);
		contentPane.add(lblZahlenfolge);
		
		tfZahlenfolge = new JTextField();
		tfZahlenfolge.setBounds(140, 47, 50, 19);
		contentPane.add(tfZahlenfolge);
		tfZahlenfolge.setColumns(10);
		
		JButton btnBerechnenZahlenfolge = new JButton("berechnen");
		btnBerechnenZahlenfolge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfErgebnisZahlenfolge.setText("" + zahlenfolge(getEingabe(tfZahlenfolge)));
			}
		});
		btnBerechnenZahlenfolge.setBounds(202, 44, 98, 25);
		contentPane.add(btnBerechnenZahlenfolge);
		
		JLabel lblErgebnisZahlenfolge = new JLabel("Ergebnis:");
		lblErgebnisZahlenfolge.setBounds(318, 49, 55, 15);
		contentPane.add(lblErgebnisZahlenfolge);
		
		tfErgebnisZahlenfolge = new JTextField();
		tfErgebnisZahlenfolge.setEditable(false);
		tfErgebnisZahlenfolge.setBounds(391, 47, 50, 19);
		contentPane.add(tfErgebnisZahlenfolge);
		tfErgebnisZahlenfolge.setColumns(10);
		
		JLabel lblDinA = new JLabel("Papierformat Din A");
		lblDinA.setBounds(12, 86, 110, 15);
		contentPane.add(lblDinA);
		
		tfDinA = new JTextField();
		tfDinA.setBounds(140, 84, 50, 19);
		contentPane.add(tfDinA);
		tfDinA.setColumns(10);
		
		JButton btnBerechnenDinA = new JButton("berechnen");
		btnBerechnenDinA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfBreite.setText("" + breite(getEingabe(tfDinA)));
				tfHoehe.setText("" + hoehe(getEingabe(tfDinA)));
			}
		});
		btnBerechnenDinA.setBounds(202, 81, 98, 25);
		contentPane.add(btnBerechnenDinA);
		
		JLabel lblBreite = new JLabel("Breite:");
		lblBreite.setBounds(318, 86, 55, 15);
		contentPane.add(lblBreite);
		
		tfBreite = new JTextField();
		tfBreite.setEditable(false);
		tfBreite.setBounds(391, 84, 50, 19);
		contentPane.add(tfBreite);
		tfBreite.setColumns(10);
		
		JLabel lblHoehe = new JLabel("Höhe:");
		lblHoehe.setBounds(318, 113, 55, 15);
		contentPane.add(lblHoehe);
		
		tfHoehe = new JTextField();
		tfHoehe.setEditable(false);
		tfHoehe.setBounds(391, 111, 50, 19);
		contentPane.add(tfHoehe);
		tfHoehe.setColumns(10);
	}
	
	public int getEingabe(JTextField tf) {
		int zahl;
		try {
			zahl = Integer.parseInt(tf.getText());
		} catch (Exception e) {
			zahl = 0;
		}
		if (zahl < 0) {
			zahl = 0;
		}
		return zahl;
	}
	
	// Aufgabe 1

	public int fakultaet(int n) {
		return n;	// muss geändert werden!
	}
	
	// Aufgabe 2

	public int zahlenfolge(int n) {
		return n;	// muss geändert werden!
	}
	
	// Aufgabe 3

	public double breite(int format) {
		return 0.0;	// muss geändert werden!
	}
	
	public double hoehe(int format) {
		return 0.0;	// muss geändert werden!
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rekursion1 frame = new Rekursion1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
