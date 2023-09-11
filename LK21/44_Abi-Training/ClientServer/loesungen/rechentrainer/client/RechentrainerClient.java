import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RechentrainerClient extends JFrame {

	private JPanel contentPane;
	private JTextField tfServer;
	private JTextField tfName;
	JTextField tfAufgabe;
	JTextField tfLoesung;
	JButton btnStarten;
	JButton btnLoesungSenden;
	private Socket socket;
	private OutputStreamWriter out;

	public RechentrainerClient() {
		super("Rechentrainer Client");
		createGUI();
	}
	
	public void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServer = new JLabel("Server: ");
		lblServer.setBounds(12, 12, 55, 15);
		contentPane.add(lblServer);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(12, 39, 55, 15);
		contentPane.add(lblName);
		
		JLabel lblAufgabe = new JLabel("Aufgabe: ");
		lblAufgabe.setBounds(12, 99, 55, 15);
		contentPane.add(lblAufgabe);
		
		JLabel lblLoesung = new JLabel("Lösung: ");
		lblLoesung.setBounds(12, 126, 55, 15);
		contentPane.add(lblLoesung);
		
		tfServer = new JTextField();
		tfServer.setText("localhost");
		tfServer.setBounds(85, 10, 114, 19);
		contentPane.add(tfServer);
		tfServer.setColumns(10);
		
		tfName = new JTextField();
		tfName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		tfName.setBounds(85, 37, 114, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfAufgabe = new JTextField();
		tfAufgabe.setEditable(false);
		tfAufgabe.setBounds(85, 97, 114, 19);
		contentPane.add(tfAufgabe);
		tfAufgabe.setColumns(10);
		
		tfLoesung = new JTextField();
		tfLoesung.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loesungSenden();
			}
		});
		tfLoesung.setBounds(85, 124, 114, 19);
		contentPane.add(tfLoesung);
		tfLoesung.setColumns(10);
		
		btnStarten = new JButton("starten");
		btnStarten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnStarten.setBounds(238, 34, 121, 25);
		contentPane.add(btnStarten);
		
		btnLoesungSenden = new JButton("Lösung senden");
		btnLoesungSenden.setEnabled(false);
		btnLoesungSenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loesungSenden();
			}
		});
		btnLoesungSenden.setBounds(238, 121, 121, 25);
		contentPane.add(btnLoesungSenden);

		setResizable(false);
	}
	
	public void verbinden() {
		// Verbinden + Name schicken
		String host = tfServer.getText();
		String name = tfName.getText();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(this, "Bitte gib deinen Namen ein!");
			return;
		}
		try {
			socket = new Socket(host, 33333);
			out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			name += '$';
			out.write(name);
			out.flush();
			LeseThread thread = new LeseThread(this, socket);
			thread.start();
			btnStarten.setEnabled(false);
			btnLoesungSenden.setEnabled(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Die Verbindung zum Server konnte nicht aufgebaut werden."
							+ e.getMessage());
		}
	}
	
	public void loesungSenden() {
		try {
			String loesung = tfLoesung.getText();
			loesung += '$';
			out.write(loesung);
			out.flush();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RechentrainerClient frame = new RechentrainerClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
