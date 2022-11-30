import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcherButton extends JFrame implements ActionListener {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 120;
	JButton btnEins, btnZwei, btnDrei;
	JLabel lblAusgabe1, lblAusgabe2;

	public WelcherButton(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		setSize(WIDTH, HEIGHT);
		btnEins = new JButton("1");
		contentPane.add(btnEins);
		btnEins.addActionListener(this);
		btnZwei = new JButton("2");
		contentPane.add(btnZwei);
		btnZwei.addActionListener(this);
		btnDrei = new JButton("3");
		contentPane.add(btnDrei);
		btnDrei.addActionListener(this);
		lblAusgabe1 = new JLabel("Es wurde Button _ gedrückt.");
		contentPane.add(lblAusgabe1);
		lblAusgabe2 = new JLabel("Es wurde Button _ gedrückt.");
		contentPane.add(lblAusgabe2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		// 1. Alternative: lange Variante
		if (button.equals(btnEins)) {
			lblAusgabe1.setText("Es wurde Button 1 gedrückt.");
		}
		if (button.equals(btnZwei)) {
			lblAusgabe1.setText("Es wurde Button 2 gedrückt.");
		}
		if (button.equals(btnDrei)) {
			lblAusgabe1.setText("Es wurde Button 3 gedrückt.");
		}

		// 2. Alternative: kurze Variante
		lblAusgabe2.setText("Es wurde Button " + button.getText() + " gedrückt.");
	}

	public static void main(final String[] args) {
		new WelcherButton("WelcherButton");
	}
}
