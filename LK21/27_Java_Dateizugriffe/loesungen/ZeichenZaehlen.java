import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ZeichenZaehlen extends JFrame {
	// globale Variablen
	private static final int WIDTH = 585;
	private static final int HEIGHT = 235;
	private JLabel lblDateiname = new JLabel();
	private JTextField tfDateiname = new JTextField();
	private JButton btnZaehlen = new JButton();
	private JButton btnFileChooser = new JButton();
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel lblAuswertung = new JLabel();
	private DefaultListModel<String> auswertung = new DefaultListModel<String>();
	private JList<String> listAuswertung = new JList<String>(auswertung);

	public ZeichenZaehlen() {
		createGUI();
	}
	
	private void createGUI() {
		setTitle("Zeichen Zählen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblDateiname.setBounds(16, 24, 91, 16);
		lblDateiname.setText("Dateiname:");
		contentPane.add(lblDateiname);
		tfDateiname.setBounds(125, 16, 300, 24);
		tfDateiname.setText("");
		contentPane.add(tfDateiname);
		btnZaehlen.setBounds(479, 16, 84, 25);
		btnZaehlen.setText("zählen");
		contentPane.add(btnZaehlen);
		btnZaehlen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				zaehlen();
			}
		});

		lblAuswertung.setBounds(16, 56, 91, 16);
		lblAuswertung.setText("Auswertung:");
		contentPane.add(lblAuswertung);
		listAuswertung.setBounds(16, 80, 547, 105);
		contentPane.add(listAuswertung);

		btnFileChooser.setText("...");
		btnFileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dateiWaehlen();
			}
		});
		btnFileChooser.setBounds(438, 17, 23, 23);
		contentPane.add(btnFileChooser);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void dateiWaehlen() {
		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String chosenFile = file.getPath();
			tfDateiname.setText(chosenFile);
		} else {
			System.out.println("Du hast den Dateiauswahl-Dialog abgebrochen!");
		}
	}

	public void zaehlen() {
		URL url = getClass().getResource(tfDateiname.getText());
		if (url != null) {
			try {
				tfDateiname.setText(URLDecoder.decode(url.getFile(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try (InputStream is = new FileInputStream(tfDateiname.getText());
				InputStreamReader in = new InputStreamReader(is, "UTF-8")) {
			int kleinbuchstabe = 0;
			int trennzeichen = 0;
			int ziffer = 0;
			int grossbuchstabe = 0;
			int sonstiges = 0;
			int zeichen;
			listAuswertung.removeAll();
			
			while ((zeichen = in.read()) != -1) {
				char c = (char) zeichen;
				if (Character.isDigit(c)) {
					ziffer++;
				} else {
					if (Character.isLowerCase(c)) {
						kleinbuchstabe++;
					} else {
						if (Character.isUpperCase(c)) {
							grossbuchstabe++;
						} else {
							if (Character.isWhitespace(c)) {
								trennzeichen++;
							} else {
								sonstiges++;
							}
						}
					}
				}
			}
			auswertung.clear();
			auswertung.addElement("Anzahl Kleinbuchstaben: " + kleinbuchstabe);
			auswertung.addElement("Anzahl Großbuchstaben: " + grossbuchstabe);
			auswertung.addElement("Anzahl Ziffern: " + ziffer);
			auswertung.addElement("Anzahl Trennzeichen: " + trennzeichen);
			auswertung.addElement("Anzahl sonstige Zeichen: " + sonstiges);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ZeichenZaehlen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}