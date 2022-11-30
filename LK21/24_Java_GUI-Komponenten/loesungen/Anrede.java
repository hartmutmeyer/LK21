import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Anrede extends JFrame implements ActionListener {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 100;
	private JLabel lblName = new JLabel();
	private JTextField tfName = new JTextField(20);
	private JCheckBox cbGeschlecht = new JCheckBox();
	private JButton btnAuswerten = new JButton();
	private JLabel lblAnrede = new JLabel();

	public Anrede(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		lblName.setText("Name:");
		cbGeschlecht.setText("weiblich");
		btnAuswerten.setText("auswerten");
		lblAnrede.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		lblAnrede.setFont(new Font("Blackadder ITC", Font.BOLD, 16));
		contentPane.add(lblName);
		contentPane.add(tfName);
		contentPane.add(cbGeschlecht);
		contentPane.add(btnAuswerten);
		contentPane.add(lblAnrede);
		btnAuswerten.addActionListener(this);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String s = "Guten Tag ";
		if (cbGeschlecht.isSelected()) {
			s += "Frau ";
		}
		else {
			s += "Herr ";
		}
		s += tfName.getText();
		s += "!";
		lblAnrede.setText(s);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anrede anwendung = new Anrede("Anrede");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}