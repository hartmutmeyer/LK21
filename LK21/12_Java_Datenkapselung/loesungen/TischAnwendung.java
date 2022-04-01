import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TischAnwendung extends JFrame {

	private JPanel contentPane;
	private JTextField tfHoehe;
	private JPanel zeichenflaeche;
	private Tisch tisch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TischAnwendung frame = new TischAnwendung();
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
	public TischAnwendung() {
		super("Fantastika Tisch-Konfigurator");
		createGUI();
		tisch = new Tisch(50, 100);
	}
	
	private void createGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHoehe = new JLabel("Höhe des Tisches in Pixeln:");
		lblHoehe.setBounds(12, 12, 160, 15);
		contentPane.add(lblHoehe);
		
		tfHoehe = new JTextField();
		tfHoehe.setText("50");
		tfHoehe.setBounds(190, 10, 50, 19);
		contentPane.add(tfHoehe);
		tfHoehe.setColumns(10);
		
		JButton btnTischNeuZeichnen = new JButton("Tisch neu zeichnen");
		btnTischNeuZeichnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tisch.setHoehe(Integer.parseInt(tfHoehe.getText()));
				repaint();
			}
		});
		btnTischNeuZeichnen.setBounds(258, 7, 176, 25);
		contentPane.add(btnTischNeuZeichnen);
		
		zeichenflaeche = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				myPaint(g);
			}
		};
		zeichenflaeche.setBackground(Color.WHITE);
		zeichenflaeche.setBounds(12, 50, 422, 374);
		contentPane.add(zeichenflaeche);
	}
	
	private void myPaint(Graphics g) {
		tisch.zeichnen(g);
	}
}
