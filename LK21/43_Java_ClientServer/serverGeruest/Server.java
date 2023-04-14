import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
	// globale Variablen
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private HauptThread thread;

	public Server(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		thread = new HauptThread(this);
		thread.start();
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Server("Server");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}