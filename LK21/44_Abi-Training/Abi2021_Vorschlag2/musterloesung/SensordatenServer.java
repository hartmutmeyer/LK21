package lk18.vorschlag2.musterloesung;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class SensordatenServer extends JFrame {

	private JPanel contentPane;
	private HauptThread thread;
	private JLabel lblSensorDaten;
	private DefaultListModel<String> sensorDaten = new DefaultListModel<String>();
	private JList<String> listSensorDaten = new JList<String>(sensorDaten);
	private JScrollPane scrollPane;

	public SensordatenServer() {
		createGUI();
		thread = new HauptThread(this);
		thread.start();
	}

	public void createGUI() {
		setTitle("Sensordaten Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSensorDaten = new JLabel("Sensor-Daten:");
		lblSensorDaten.setBounds(12, 12, 195, 15);
		contentPane.add(lblSensorDaten);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 459, 390);
		contentPane.add(scrollPane);
		listSensorDaten.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listSensorDaten.setBackground(Color.WHITE);

		scrollPane.setViewportView(listSensorDaten);
	}

	void add2log(String logMessage) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				sensorDaten.addElement(logMessage);
				listSensorDaten.ensureIndexIsVisible(sensorDaten.size() - 1);
			}
		});
	}

	public void clearSensorData() {
		sensorDaten.clear();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SensordatenServer frame = new SensordatenServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
