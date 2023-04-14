import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 400;
	private JTextField tfServer;
	private JTextField tfStatus;
	private JTextField tfEingabe;
	private JButton btnVerbinden, btnTrennen, btnSenden;
	JTextArea textAreaAusgabe;
	private boolean verbunden = false;
	Socket socket;
	InputStreamReader in;
	private OutputStreamWriter out;
	private LeseThread thread;

	public Client(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 26, 322, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblServer = new JLabel("Server:");
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.anchor = GridBagConstraints.EAST;
		gbc_lblServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer.gridx = 0;
		gbc_lblServer.gridy = 0;
		contentPane.add(lblServer, gbc_lblServer);

		tfServer = new JTextField();
		GridBagConstraints gbc_tfServer = new GridBagConstraints();
		gbc_tfServer.insets = new Insets(0, 0, 5, 5);
		gbc_tfServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfServer.gridx = 1;
		gbc_tfServer.gridy = 0;
		contentPane.add(tfServer, gbc_tfServer);
		tfServer.setColumns(10);

		btnVerbinden = new JButton("verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		GridBagConstraints gbc_btnVerbinden = new GridBagConstraints();
		gbc_btnVerbinden.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerbinden.gridx = 2;
		gbc_btnVerbinden.gridy = 0;
		contentPane.add(btnVerbinden, gbc_btnVerbinden);

		JLabel lblStatus = new JLabel("Status:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 1;
		contentPane.add(lblStatus, gbc_lblStatus);

		tfStatus = new JTextField();
		tfStatus.setEditable(false);
		GridBagConstraints gbc_tfStatus = new GridBagConstraints();
		gbc_tfStatus.insets = new Insets(0, 0, 5, 5);
		gbc_tfStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfStatus.gridx = 1;
		gbc_tfStatus.gridy = 1;
		contentPane.add(tfStatus, gbc_tfStatus);
		tfStatus.setColumns(10);

		btnTrennen = new JButton("trennen");
		btnTrennen.setEnabled(false);
		btnTrennen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trennen();
			}
		});
		GridBagConstraints gbc_btnTrennen = new GridBagConstraints();
		gbc_btnTrennen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTrennen.insets = new Insets(0, 0, 5, 0);
		gbc_btnTrennen.gridx = 2;
		gbc_btnTrennen.gridy = 1;
		contentPane.add(btnTrennen, gbc_btnTrennen);

		JLabel lblEingabe = new JLabel("Eingabe:");
		GridBagConstraints gbc_lblEingabe = new GridBagConstraints();
		gbc_lblEingabe.anchor = GridBagConstraints.EAST;
		gbc_lblEingabe.insets = new Insets(0, 0, 5, 5);
		gbc_lblEingabe.gridx = 0;
		gbc_lblEingabe.gridy = 2;
		contentPane.add(lblEingabe, gbc_lblEingabe);

		tfEingabe = new JTextField();
		GridBagConstraints gbc_tfEingabe = new GridBagConstraints();
		gbc_tfEingabe.insets = new Insets(0, 0, 5, 5);
		gbc_tfEingabe.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfEingabe.gridx = 1;
		gbc_tfEingabe.gridy = 2;
		contentPane.add(tfEingabe, gbc_tfEingabe);
		tfEingabe.setColumns(10);

		btnSenden = new JButton("senden");
		btnSenden.setEnabled(false);
		btnSenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				senden();
			}
		});
		GridBagConstraints gbc_btnSenden = new GridBagConstraints();
		gbc_btnSenden.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSenden.insets = new Insets(0, 0, 5, 0);
		gbc_btnSenden.gridx = 2;
		gbc_btnSenden.gridy = 2;
		contentPane.add(btnSenden, gbc_btnSenden);

		JLabel lblAusgabe = new JLabel("Ausgabe:");
		GridBagConstraints gbc_lblAusgabe = new GridBagConstraints();
		gbc_lblAusgabe.anchor = GridBagConstraints.EAST;
		gbc_lblAusgabe.insets = new Insets(0, 0, 5, 5);
		gbc_lblAusgabe.gridx = 0;
		gbc_lblAusgabe.gridy = 3;
		contentPane.add(lblAusgabe, gbc_lblAusgabe);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);

		textAreaAusgabe = new JTextArea();
		scrollPane.setViewportView(textAreaAusgabe);

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void verbinden() {
		// Die Verbindung zum Server wird aufgebaut.
		// InputStream und OutputStream des Sockets werden
		// geholt.
		// Anschließend wird der InputStream an den Lesethread
		// übergeben und dieser gestartet.

		try {
			if (!verbunden) {
				verbunden = true;
				String serverName = tfServer.getText();
				socket = new Socket(serverName, 22222);
				System.out.println("Verbindung hergestellt!");
				in = new InputStreamReader(socket.getInputStream(), "UTF-8");
				out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				thread = new LeseThread(this, in);
				thread.start();
				tfServer.setEnabled(false);
				btnVerbinden.setEnabled(false);
				btnTrennen.setEnabled(true);
				btnSenden.setEnabled(true);
				tfStatus.setText("verbunden");
			}
		} catch (Exception e) {
			tfStatus.setText("Fehler: " + e.getMessage());
			tfServer.setEnabled(true);
            btnVerbinden.setEnabled(true);
            btnTrennen.setEnabled(false);
            btnSenden.setEnabled(false);
            tfStatus.setText("getrennt");
            verbunden = false;
		}
	}

	public void senden() {
		if (verbunden) {
			try {
				// Was hier gesendet wird (und auch wodurch das Senden
				// ausgelöst wird) wird durch das Protokoll definiert!
				//
				// ACHTUNG: Je nach Definition des Protokolls kann das
				// Senden auch unabhängig von Benutzereingaben geschehen.
				// Etwa als automatisierte Reaktion auf eine Anfrage vom
				// Server. In diesem Fall würde dies vermutlich im Lesethread
				// geschehen. Dieser müsste dann neben dem InputStreamReader
				// auch das OutputStreamWriter-Objekt als Parameter 
				// übergeben bekommen.
			} catch (Exception exc) {
				textAreaAusgabe.append("Fehler: " + exc.getMessage());
			}
		}
	}

	public void trennen() {
		System.out.println("trennen");
		try {
			socket.close();
			tfStatus.setText("getrennt");
			tfServer.setEnabled(true);
			btnVerbinden.setEnabled(true);
			btnTrennen.setEnabled(false);
			btnSenden.setEnabled(false);
			verbunden = false;
		} catch (Exception exc) {
			tfStatus.setText("Fehler: " + exc.getMessage());
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Client("Client");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}