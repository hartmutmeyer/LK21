import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicTacToe extends JFrame implements ActionListener {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private JButton[] btnFeld = new JButton[9];
	private JButton btnReset = new JButton("Zurücksetzen");
	private boolean spieler1 = true;

	public TicTacToe(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 15));
		setContentPane(contentPane);
		JPanel pnlSpielfeld = new JPanel();
		pnlSpielfeld.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pnlSpielfeld.setLayout(new GridLayout(3, 3, 4, 4));
		for (int i = 0; i < 9; i++) {
			btnFeld[i] = new JButton();
			btnFeld[i].setFont(new Font("Arial", Font.BOLD, 72));
			pnlSpielfeld.add(btnFeld[i]);
			btnFeld[i].addActionListener(this);
		}
		contentPane.add(pnlSpielfeld, BorderLayout.CENTER);
		contentPane.add(btnReset, BorderLayout.PAGE_END);
		btnReset.addActionListener(this);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnReset)) {
			for (int i = 0; i < 9; i++) {
				btnFeld[i].setText("");
			}
			spieler1 = true;
		} else {
			JButton but = (JButton) e.getSource();
			if (spieler1) {
				but.setText("X");
			}
			else {
				but.setText("O");
			}
			spieler1 = !spieler1;
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TicTacToe("Tic Tac Toe");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}