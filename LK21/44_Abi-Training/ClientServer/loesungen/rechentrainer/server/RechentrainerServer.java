import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

public class RechentrainerServer extends JFrame {

	private JPanel contentPane;
	double zeit;

	public RechentrainerServer() {
		super("Rechentrainer Server");
		createGUI();
		MainThread thread = new MainThread();
		thread.start();
	}
	
	public void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 97);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRechentrainerServer = new JLabel("Rechentrainer Server");
		lblRechentrainerServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRechentrainerServer.setFont(new Font("Dialog", Font.BOLD, 24));
		lblRechentrainerServer.setBounds(12, 12, 268, 42);
		contentPane.add(lblRechentrainerServer);
		setResizable(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RechentrainerServer frame = new RechentrainerServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
