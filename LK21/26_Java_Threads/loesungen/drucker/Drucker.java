import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Drucker extends JFrame {
	// globale Variablen
	private static final int WIDTH = 400;
	private static final int HEIGHT = 50;
	private JLabel lblAufDemDrucker;
	JTextField tfDrucker;
	DruckerThread thr[] = new DruckerThread[5];

	public Drucker(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(10));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		lblAufDemDrucker = new JLabel("Ausgabe auf dem Drucker:");
		contentPane.add(lblAufDemDrucker);
		tfDrucker = new JTextField(20);
		contentPane.add(tfDrucker);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		for (int i = 0; i < 5; i++) {
			thr[i] = new DruckerThread(this, i);
			thr[i].start();
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Drucker("Drucker-Simulation");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
