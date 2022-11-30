import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Initialen extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfInitialen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initialen frame = new Initialen();
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
	public Initialen() {
		setTitle("Initialen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 144);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(10, 11, 250, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfInitialen = new JTextField();
		tfInitialen.setEditable(false);
		tfInitialen.setBounds(10, 58, 86, 20);
		contentPane.add(tfInitialen);
		tfInitialen.setColumns(10);
		
		JButton btnAuswerten = new JButton("auswerten");
		btnAuswerten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auswerten();
			}
		});
		btnAuswerten.setBounds(285, 10, 127, 23);
		contentPane.add(btnAuswerten);
	}
	
	void auswerten() {
		String name = tfName.getText();
		String initialen = "";
		
		for (int i = 0; i < name.length(); i++) {
			if (Character.isUpperCase(name.charAt(i))) {
				initialen += name.charAt(i);
			}
		}
		tfInitialen.setText(initialen);		
	}
}
