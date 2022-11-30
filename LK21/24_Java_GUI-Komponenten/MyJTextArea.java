import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyJTextArea extends JFrame {

	private JPanel contentPane;
	String zeilenumbruch = System.getProperty("line.separator");
	String handtuch = "aber du wirst doch wohl nicht etwa dein handtuch vergessen haben" + zeilenumbruch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyJTextArea frame = new MyJTextArea();
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
	public MyJTextArea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea taText = new JTextArea();
		scrollPane.setViewportView(taText);
		
		taText.setText(handtuch);
		taText.append(handtuch);
		taText.append(handtuch);
		taText.append(handtuch);
		taText.append(handtuch);
		taText.append(handtuch);
		taText.append("              doch                      handtuch vergessen");

		String ergebnis = taText.getText();
		System.out.println(ergebnis);
	}

}
