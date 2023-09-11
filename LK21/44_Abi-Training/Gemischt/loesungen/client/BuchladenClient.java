import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.ListSelectionModel;

public class BuchladenClient extends JFrame {

	private JPanel contentPane;
	private JTextField tfServer;
	private JTextField tfKundenNr;
	private JTextField tfStueckzahl;
	private Socket socket;
	private OutputStream out;
	JButton btnStornieren;
	JButton btnBestellen;
	JButton btnVerbinden;
	DefaultListModel<String> buecher = new DefaultListModel<String>();
	JList<String> buecherliste = new JList<String>(buecher);

	public BuchladenClient() {
		super("Buchladen Client");
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setBounds(12, 12, 55, 15);
		contentPane.add(lblServer);
		
		tfServer = new JTextField();
		tfServer.setBounds(85, 10, 114, 19);
		contentPane.add(tfServer);
		tfServer.setColumns(10);
		
		btnVerbinden = new JButton("verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setBounds(211, 7, 98, 25);
		contentPane.add(btnVerbinden);
		
		JLabel lblKundennr = new JLabel("KundenNr:");
		lblKundennr.setBounds(12, 39, 70, 15);
		contentPane.add(lblKundennr);
		
		tfKundenNr = new JTextField();
		tfKundenNr.setBounds(85, 37, 114, 19);
		contentPane.add(tfKundenNr);
		tfKundenNr.setColumns(10);
		
		JLabel lblBuecherliste = new JLabel("Bücherliste:");
		lblBuecherliste.setBounds(12, 85, 90, 15);
		contentPane.add(lblBuecherliste);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 112, 491, 227);
		contentPane.add(scrollPane);
		buecherliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(buecherliste);
		
		JLabel lblStueckzahl = new JLabel("Stückzahl:");
		lblStueckzahl.setBounds(12, 353, 70, 15);
		contentPane.add(lblStueckzahl);
		
		tfStueckzahl = new JTextField();
		tfStueckzahl.setBounds(85, 351, 114, 19);
		contentPane.add(tfStueckzahl);
		tfStueckzahl.setColumns(10);
		
		btnBestellen = new JButton("markiertes Buch bestellen");
		btnBestellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bestellen();
			}
		});
		btnBestellen.setEnabled(false);
		btnBestellen.setBounds(211, 348, 292, 25);
		contentPane.add(btnBestellen);
		
		btnStornieren = new JButton("Bestellung des markierten Buches stornieren");
		btnStornieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stornieren();
			}
		});
		btnStornieren.setEnabled(false);
		btnStornieren.setBounds(211, 385, 292, 25);
		contentPane.add(btnStornieren);
	}
	
	private void verbinden() {
		try {
			String servername = tfServer.getText();
			socket = new Socket(servername, 44444);
			ClientLeseThread thread = new ClientLeseThread(this, socket.getInputStream());
			thread.start();
			out = socket.getOutputStream();
			btnVerbinden.setEnabled(false);
			btnBestellen.setEnabled(true);
			btnStornieren.setEnabled(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void bestellen() {
		String kundennr = tfKundenNr.getText();
		if (kundennr.length() == 0) {
			JOptionPane.showMessageDialog(this, "Bitte geben Sie Ihre Kundennummer an!");
			return;
		}
		String markiert = buecherliste.getSelectedValue();
		if (markiert == null) {
			JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Buch aus der Liste aus!");
			return;
		}
		int leerzeichen = markiert.indexOf(' ');
		String isbn = markiert.substring(0, leerzeichen);
		String anzahl = tfStueckzahl.getText();
		int zahl;
		try {
			zahl = Integer.parseInt(anzahl);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Bitte geben Sie ein, wie viele Bücher Sie bestellen möchten.");
			return;
		}
		if (zahl <= 0) {
			JOptionPane.showMessageDialog(this, "Bitte geben Sie ein, wie viele Bücher Sie bestellen möchten.");
			return;
		}
		String bestellung = "B" + kundennr + "$" + isbn + "§" + anzahl + "%";
		try {
			out.write(bestellung.getBytes());
			System.out.println("Bestellung abgeschickt: " + bestellung);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void stornieren() {
		String kundennr = tfKundenNr.getText();
		if (kundennr.length() == 0) {
			JOptionPane.showMessageDialog(this, "Bitte geben Sie Ihre Kundennummer an!");
			return;
		}
		String markiert = buecherliste.getSelectedValue();
		if (markiert == null) {
			JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Buch aus der Liste aus!");
			return;
		}
		int leerzeichen = markiert.indexOf(' ');
		String isbn = markiert.substring(0, leerzeichen);

		String stornierung = "S" + kundennr + "$" + isbn + "§";
		try {
			out.write(stornierung.getBytes());
			System.out.println("Stornierung abgeschickt: " + stornierung);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuchladenClient frame = new BuchladenClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
