import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class DateiinhalteSendenClient extends JFrame {

	private JPanel contentPane;
	private JLabel lblServer;
	private JTextField tfServer;
	JButton btnVerbinden;
	JButton btnTrennen;
	private JLabel lblStatus;
	JTextField tfVerbindungsstatus;
	private JLabel lblEingabe;
	JTextField tfEingabe;
	JButton btnSenden;
	private JLabel lblAusgabe;
	JTextArea taAusgabe;
	private JScrollPane scrollPane;
	private Socket sock;
	private InputStreamReader in;
	private OutputStreamWriter out;
	boolean verbunden = false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateiinhalteSendenClient frame = new DateiinhalteSendenClient();
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
	public DateiinhalteSendenClient() {
		createGUI();
	}

	private void createGUI() {
		setTitle("Dateiinhalte Senden Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblServer = new JLabel("Server:");
		lblServer.setBounds(12, 12, 55, 15);
		contentPane.add(lblServer);
		
		tfServer = new JTextField();
		tfServer.setBounds(69, 10, 141, 19);
		contentPane.add(tfServer);
		tfServer.setColumns(10);
		
		btnVerbinden = new JButton("Verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setBounds(222, 7, 98, 25);
		contentPane.add(btnVerbinden);
		
		btnTrennen = new JButton("Trennen");
		btnTrennen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trennen();
			}
		});
		btnTrennen.setEnabled(false);
		btnTrennen.setBounds(332, 7, 98, 25);
		contentPane.add(btnTrennen);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(12, 41, 55, 15);
		contentPane.add(lblStatus);
		
		tfVerbindungsstatus = new JTextField("getrennt");
		tfVerbindungsstatus.setEditable(false);
		tfVerbindungsstatus.setBounds(69, 39, 141, 19);
		contentPane.add(tfVerbindungsstatus);
		tfVerbindungsstatus.setColumns(10);
		
		lblEingabe = new JLabel("Eingabe:");
		lblEingabe.setBounds(12, 68, 55, 15);
		contentPane.add(lblEingabe);
		
		tfEingabe = new JTextField();
		tfEingabe.setEditable(false);
		tfEingabe.setBounds(69, 66, 141, 19);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		btnSenden = new JButton("Senden");
		btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				senden();
			}
		});
		btnSenden.setEnabled(false);
		btnSenden.setBounds(222, 63, 98, 25);
		contentPane.add(btnSenden);
		
		lblAusgabe = new JLabel("Ausgabe:");
		lblAusgabe.setBounds(12, 95, 55, 15);
		contentPane.add(lblAusgabe);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 122, 418, 133);
		contentPane.add(scrollPane);
		
		taAusgabe = new JTextArea();
		taAusgabe.setEditable(false);
		scrollPane.setViewportView(taAusgabe);
		
		setResizable(false);
	}

	private void verbinden() {
		try {
			String server = tfServer.getText();
			sock = new Socket(server, 6666);
			in = new InputStreamReader(sock.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(sock.getOutputStream(), "UTF-8");
			LeseThread thread = new LeseThread(this, in);
			thread.start();
			btnVerbinden.setEnabled(false);
			btnTrennen.setEnabled(true);
			tfEingabe.setEditable(true);
			btnSenden.setEnabled(true);
			verbunden = true;
			tfVerbindungsstatus.setText("verbunden");
		} catch (IOException e) {
			tfVerbindungsstatus.setText("Fehler: " + e.getMessage());
		}
	}

	private void trennen() {
		try {
			sock.close();
			btnVerbinden.setEnabled(true);
			btnTrennen.setEnabled(false);
			tfEingabe.setEditable(false);
			btnSenden.setEnabled(false);
			verbunden = false;
			tfVerbindungsstatus.setText("getrennt");
		} catch (IOException e) {
			tfVerbindungsstatus.setText("Fehler: " + e.getMessage());
		}
	}

	protected void senden() {
		try {
			String eingabe = tfEingabe.getText();
			out.write(eingabe);
			out.flush();
		} catch (IOException e) {
			taAusgabe.append("Fehler: " + e.getMessage());
		}
	}
}
