package lk18.vorschlag2.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

public class SensorStation extends JFrame {

	private JPanel contentPane;
	private JButton btnLoeschen;
	JCheckBox cbFunkverbindungSimulieren;
	DefaultListModel<String> protokoll = new DefaultListModel<String>();
	JList<String> listProtokoll = new JList<String>(protokoll);
	private LeseThread leseThread; 
	private SensordatenSendeThread sendeThread;
	int id;
	boolean unzuverlaessigeVerbindung = false;
	private JLabel lblSensorstation;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SensorStation frame = new SensorStation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SensorStation() {
		createGUI();
		stationskennungAbfragen();
		connect();
	}

	public void createGUI() {
		setTitle("Sensorstation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSensorstation = new JLabel("Sensorstation");
		lblSensorstation.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSensorstation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSensorstation.setBounds(12, 12, 572, 31);
		contentPane.add(lblSensorstation);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(12, 55, 572, 1);
		contentPane.add(separator1);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(12, 121, 572, 1);
		contentPane.add(separator2);

		JLabel lblProtokoll = new JLabel("Protokoll:");
		lblProtokoll.setBounds(12, 134, 83, 15);
		contentPane.add(lblProtokoll);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 161, 572, 463);
		contentPane.add(scrollPane);
		listProtokoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(listProtokoll);

		btnLoeschen = new JButton("löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				protokoll.clear();
			}
		});
		btnLoeschen.setBounds(12, 636, 98, 25);
		contentPane.add(btnLoeschen);
		
		cbFunkverbindungSimulieren = new JCheckBox("Unzuverlässige Funkverbindung simulieren");
		cbFunkverbindungSimulieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funkverbindungSimulieren();
			}
		});
		cbFunkverbindungSimulieren.setBounds(63, 77, 463, 22);
		contentPane.add(cbFunkverbindungSimulieren);
	}
	
	private void stationskennungAbfragen() {
		String stationskennung = "";
		boolean idFestgelegt = false;
		while (!idFestgelegt) {
			stationskennung = JOptionPane.showInputDialog("Geben Sie die Stationskennung an");
			if (stationskennung == null) { // Abbruch 
				System.exit(0);
			}
			try {
				id = Integer.parseInt(stationskennung);
				if (id >= 0) {
					idFestgelegt = true;
					lblSensorstation.setText("Sensorstation " + stationskennung);
				}
			} catch (NumberFormatException e) {
				// Nichts tun.
			}
			if (!idFestgelegt) {
				JOptionPane.showMessageDialog(this, "Die Stationskennung muss eine positive ganze Zahl sein.");
			}
		}		
	}
	
	private void funkverbindungSimulieren() {
		if (cbFunkverbindungSimulieren.isSelected()) {
			unzuverlaessigeVerbindung = true;
		} else {
			unzuverlaessigeVerbindung = false;
		}
	}
	
	private void connect() {
		Socket s;
		try {
			s = new Socket ("localhost", 11111);
			InputStreamReader in = new InputStreamReader(s.getInputStream(), "UTF-8");
			OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
			leseThread = new LeseThread(this, in);
			leseThread.start();
			sendeThread = new SensordatenSendeThread(this, out);
			sendeThread.start();
			add2log("Erfolgreich mit Server verbunden!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void add2log(String logMessage) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				protokoll.addElement(logMessage);
				listProtokoll.ensureIndexIsVisible(protokoll.size() - 1);
			}
		});
	}

}
