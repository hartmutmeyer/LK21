package tmpQ1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EchoClient extends JFrame {

	private JPanel contentPane;
	private JTextField tfServer;
	private JTextField tfSenden;
	private JButton btnPlus;
	private JTextArea textArea;
	private InputStreamReader in;
	private OutputStreamWriter out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EchoClient frame = new EchoClient();
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
	public EchoClient() {
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfServer = new JTextField();
		tfServer.setBounds(10, 11, 249, 20);
		contentPane.add(tfServer);
		tfServer.setColumns(10);
		
		JButton btnVerbinden = new JButton(" Verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setBounds(288, 10, 136, 23);
		contentPane.add(btnVerbinden);
		
		tfSenden = new JTextField();
		tfSenden.setBounds(10, 42, 249, 20);
		contentPane.add(tfSenden);
		tfSenden.setColumns(10);
		
		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plus();
			}
		});
		btnPlus.setBounds(288, 41, 41, 23);
		contentPane.add(btnPlus);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 73, 414, 164);
		contentPane.add(textArea);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minus();
			}
		});
		btnMinus.setBounds(345, 41, 41, 23);
		contentPane.add(btnMinus);
		
		JButton btnErgebnis = new JButton("=");
		btnErgebnis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ergebnisAnfordern();
			}
		});
		btnErgebnis.setBounds(396, 41, 89, 23);
		contentPane.add(btnErgebnis);
	}

	protected void plus() {
		try {
			out.write('+' + tfSenden.getText() + '$');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void minus() {
		try {
			out.write('-' + tfSenden.getText() + '$');
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void ergebnisAnfordern() {
		try {
			out.write("!$");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void verbinden() {
		try {
			Socket s = new Socket (tfServer.getText(), 22222);
			in = new InputStreamReader(s.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
			LeseThread lt = new LeseThread(in, textArea);
			lt.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
