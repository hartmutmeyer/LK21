import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EchoServer extends JFrame {

	private JPanel contentPane;
	JTextField tfStatus;
	WarteThread thread;
	int anzahlVerbindungen = 0;

	

	public EchoServer() {
		super("Echo Server");
		createGUI();
		thread = new WarteThread(this);
		thread.start();
	}

	public void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 70);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblStatus = new JLabel("Status: ");
		contentPane.add(lblStatus, BorderLayout.WEST);
		
		tfStatus = new JTextField();
		tfStatus.setEditable(false);
		contentPane.add(tfStatus, BorderLayout.CENTER);
		tfStatus.setColumns(30);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EchoServer frame = new EchoServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
