import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends JFrame {

	private JPanel contentPane;
	JTextField tfServer;
	JTextField tfNick;
	JButton btnVerbinden;
	JButton btnSenden;
	JTextArea taChat;
	JTextField tfNachricht;
	static final boolean VERBUNDEN = true;
	static final boolean GETRENNT = false;
	boolean verbindungsstatus = GETRENNT;
	private Socket socket;
	private OutputStreamWriter out;


	public ChatClient() {
		createGUI();
	}
	
	private void verbinden() {
		if (verbindungsstatus == GETRENNT) {
			if (tfNick.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Ohne Nick kannst du dem Chat nicht beitreten!");
			} else {
				if (tfServer.getText().isEmpty()) {
					tfServer.setText("localhost");
				}
				String server = tfServer.getText();
				try {
					socket = new Socket(server, 13131);
					System.out.println("Verbindung hergestellt!");
					InputStreamReader in = new InputStreamReader(socket.getInputStream(), "UTF-8");
					out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
					LeseThread thread = new LeseThread(this, in);
					thread.start();
					out.write(tfNick.getText() + " ist dem Chat beigetreten." + System.lineSeparator());
					out.flush();
					tfServer.setEditable(false);
					tfNick.setEditable(false);
					btnVerbinden.setText("trennen");
					btnSenden.setEnabled(true);
					tfNachricht.setEditable(true);
					verbindungsstatus = VERBUNDEN;
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(this, "Unbekannter Server!");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Verbindungsaufbau abgelehnt!");
				}
			}
		} else { // trennen ...
			try {
				out.write(tfNick.getText() + " hat den Chat verlassen." + System.lineSeparator());
				out.flush();
				socket.close();
				btnVerbinden.setText("verbinden");
				btnSenden.setEnabled(false);
				tfNachricht.setEditable(false);
				tfServer.setEditable(true);
				tfNick.setEditable(true);
				verbindungsstatus = GETRENNT;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void senden() {
		if (!tfNachricht.getText().isEmpty()) {
			try {
				out.write(tfNick.getText() + ": " + tfNachricht.getText() + System.lineSeparator());
				out.flush();
				System.out.println("ChatClient: senden(): " + tfNick.getText() + ": " + tfNachricht.getText());
				tfNachricht.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createGUI() {
		setTitle("Chat Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServer.setBounds(12, 12, 55, 15);
		contentPane.add(lblServer);
		
		tfServer = new JTextField();
		tfServer.setBounds(85, 10, 200, 19);
		contentPane.add(tfServer);
		tfServer.setColumns(10);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNick.setBounds(12, 39, 55, 15);
		contentPane.add(lblNick);
		
		tfNick = new JTextField();
		tfNick.setBounds(85, 37, 114, 19);
		contentPane.add(tfNick);
		tfNick.setColumns(10);
		
		btnVerbinden = new JButton("verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setEnabled(true);
		btnVerbinden.setBounds(468, 7, 98, 25);
		contentPane.add(btnVerbinden);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 66, 554, 481);
		contentPane.add(scrollPane);
		
		taChat = new JTextArea();
		taChat.setLineWrap(true);
		taChat.setEditable(false);
		scrollPane.setViewportView(taChat);
		
		btnSenden = new JButton("senden");
		btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senden();
			}
		});
		btnSenden.setEnabled(false);
		btnSenden.setBounds(468, 580, 98, 25);
		contentPane.add(btnSenden);
		
		tfNachricht = new JTextField();
		tfNachricht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				senden();
			}
		});
		tfNachricht.setEditable(false);
		tfNachricht.setBounds(12, 586, 444, 19);
		contentPane.add(tfNachricht);
		tfNachricht.setColumns(10);
		
		JLabel lblNeueNachricht = new JLabel("neue Nachricht:");
		lblNeueNachricht.setBounds(12, 559, 104, 15);
		contentPane.add(lblNeueNachricht);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClient frame = new ChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
