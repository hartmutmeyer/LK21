import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FarbenMischen extends JFrame {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private JPanel contentPane;
	private JLabel lblTitel = new JLabel();
	private JLabel lblRot = new JLabel();
	private JTextField tfRot = new JTextField();
	private JLabel lblGruen = new JLabel();
	private JTextField tfGruen = new JTextField();
	private JLabel lblBlau = new JLabel();
	private JTextField tfBlau = new JTextField();
	private JButton btnHintergrundSetzen = new JButton();
	private JLabel lblDummy;

	public FarbenMischen(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		contentPane.setLayout(new FlowLayout());
		lblTitel.setText("Farben Mischen");
		lblTitel.setFont(new Font("MS Sans Serif", 3, 18));
		contentPane.add(lblTitel);
		lblDummy = new JLabel("                                       ");
		contentPane.add(lblDummy);
		lblRot.setText("Rot:");
		contentPane.add(lblRot);
		tfRot.setColumns(4);
		contentPane.add(tfRot);
		lblGruen.setText("Grün:");
		contentPane.add(lblGruen);
		tfGruen.setColumns(4);
		contentPane.add(tfGruen);
		lblBlau.setText("Blau:");
		contentPane.add(lblBlau);
		tfBlau.setColumns(4);
		contentPane.add(tfBlau);
		btnHintergrundSetzen.setText("Hintergrund setzen");
		contentPane.add(btnHintergrundSetzen);
		btnHintergrundSetzen.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void buttonActionPerformed(final ActionEvent evt) {
		Color c = new Color(Integer.parseInt(tfRot.getText()),
				            Integer.parseInt(tfGruen.getText()), 
				            Integer.parseInt(tfBlau.getText()));
		contentPane.setBackground(c);
		lblTitel.setBackground(c);
		lblRot.setBackground(c);
		lblGruen.setBackground(c);
		lblBlau.setBackground(c);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new FarbenMischen("FarbenMischen");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}