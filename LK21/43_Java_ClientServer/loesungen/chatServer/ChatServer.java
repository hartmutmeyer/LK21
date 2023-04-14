import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChatServer extends JFrame {

	private JPanel contentPane;
	JTextField tfAnzahlClients;
	WarteThread thread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer frame = new ChatServer();
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
	public ChatServer() {
		super("Chat Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 223, 64);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblAnzahlClients = new JLabel("Anzahl verbundene Clients:  ");
		contentPane.add(lblAnzahlClients, BorderLayout.WEST);
		
		tfAnzahlClients = new JTextField("0");
		tfAnzahlClients.setEditable(false);
		contentPane.add(tfAnzahlClients, BorderLayout.CENTER);
		tfAnzahlClients.setColumns(10);
		
		thread = new WarteThread(this);
		thread.start();
	}

}
