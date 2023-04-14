import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient extends JFrame {

	private JPanel contentPane;
	private JTextField tfAddress;
	private JTextField tfHostname;
	private JTextField tfIPnumber;
	private JTextField tfNachricht;

	public SimpleClient() {
		super("Simple Client");
		createGUI();
	}
	
	public void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdresse = new JLabel("Adresse: ");
		lblAdresse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdresse.setBounds(12, 12, 71, 15);
		contentPane.add(lblAdresse);
		
		tfAddress = new JTextField();
		tfAddress.setText("");
		tfAddress.setBounds(101, 10, 114, 19);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		JLabel lblHostname = new JLabel("Name: ");
		lblHostname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHostname.setBounds(12, 39, 71, 15);
		contentPane.add(lblHostname);
		
		tfHostname = new JTextField();
		tfHostname.setEditable(false);
		tfHostname.setBounds(101, 37, 114, 19);
		contentPane.add(tfHostname);
		tfHostname.setColumns(10);
		
		JLabel lblIpnummer = new JLabel("IP-Nummer: ");
		lblIpnummer.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIpnummer.setBounds(12, 66, 71, 15);
		contentPane.add(lblIpnummer);
		
		tfIPnumber = new JTextField();
		tfIPnumber.setEditable(false);
		tfIPnumber.setBounds(101, 64, 114, 19);
		contentPane.add(tfIPnumber);
		tfIPnumber.setColumns(10);
		
		JLabel lblNachricht = new JLabel("Nachricht: ");
		lblNachricht.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNachricht.setBounds(12, 90, 71, 15);
		contentPane.add(lblNachricht);
		
		tfNachricht = new JTextField();
		tfNachricht.setEditable(false);
		tfNachricht.setBounds(101, 88, 283, 19);
		contentPane.add(tfNachricht);
		tfNachricht.setColumns(10);
		
		JButton btnVerbinden = new JButton("verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setBounds(286, 7, 98, 25);
		contentPane.add(btnVerbinden);
		setResizable(false);
	}
	
	public void verbinden() {
		int zeichen;

		try {
			String address = tfAddress.getText();
			InetAddress inetaddr = InetAddress.getByName(address);
			tfHostname.setText(inetaddr.getHostName());
			tfIPnumber.setText(inetaddr.getHostAddress());

			String text = "";
			Socket sock = new Socket(inetaddr, 11111);
			InputStreamReader in = new InputStreamReader(sock.getInputStream(), "UTF-8");
			while ((zeichen = in.read()) != -1) {
				text += (char) zeichen;
			}
			sock.close();
			tfNachricht.setText(text);
		} catch (UnknownHostException exc) {
			tfHostname.setText("Fehler: " + exc.getMessage());
			tfIPnumber.setText("Fehler");
		} catch (IOException exc) {
			tfNachricht.setText("Fehler: " + exc.getMessage());
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleClient frame = new SimpleClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
