import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class CountDown extends JFrame {
	// globale Variablen
	private static final int WIDTH = 250;
	private static final int HEIGHT = 110;
	static JPanel contentPane;

	public CountDown(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JTextField tfCountDown = new JTextField();
		tfCountDown.setHorizontalAlignment(SwingConstants.CENTER);
	    tfCountDown.setBounds(104, 28, 41, 24);
	    tfCountDown.setEditable(false);
	    contentPane.add(tfCountDown);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	    ZaehlThread thread = new ZaehlThread(tfCountDown);
	    thread.start();
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CountDown("Count Down");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}