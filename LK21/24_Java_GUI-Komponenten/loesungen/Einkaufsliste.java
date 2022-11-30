import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Einkaufsliste extends JFrame {

	private JPanel contentPane;
	private JTextField tfNeuerEintrag;
	private DefaultListModel<String> einkaeufe = new DefaultListModel<String>();
	private JList<String> listEinkaeufe = new JList<String>(einkaeufe);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Einkaufsliste frame = new Einkaufsliste();
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
	public Einkaufsliste() {
		createGUI();
		testDatenErzeugen();
	}

	private void testDatenErzeugen() {
		einkaeufe.addElement("Conrad Elektronic: Fernseher");
		einkaeufe.addElement("Conrad Elektronic: Handy");
		einkaeufe.addElement("IKEA: Stuhl");
		einkaeufe.addElement("Dänisches Bettenlager: Matratze");
		einkaeufe.addElement("Conrad Elektronic: Drohne");
		einkaeufe.addElement("Kiosk: IKEA-Katalog");
		einkaeufe.addElement("IKEA: Spiegel");
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfNeuerEintrag = new JTextField();
		tfNeuerEintrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinzufuegen();
			}
		});
		tfNeuerEintrag.setBounds(10, 11, 315, 20);
		contentPane.add(tfNeuerEintrag);
		tfNeuerEintrag.setColumns(10);

		JButton btnHinzufügen = new JButton("Hinzufügen");
		btnHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinzufuegen();
			}
		});
		btnHinzufügen.setBounds(335, 10, 122, 23);
		contentPane.add(btnHinzufügen);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 447, 180);
		contentPane.add(scrollPane);

		listEinkaeufe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listEinkaeufe);

		JButton btnLoeschen = new JButton("Alle Einträge von diesem Geschäft entfernen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loeschen();
			}
		});
		btnLoeschen.setBounds(10, 227, 447, 23);
		contentPane.add(btnLoeschen);
	}

	private String getGeschaeft(String eintrag) {
		// System.out.println("Das Geschäft heißt Lidl");
		int indexOfDoppelpunkt = eintrag.indexOf(':');
		String geschaeft = eintrag.substring(0, indexOfDoppelpunkt);
		return geschaeft;
	}

	protected void hinzufuegen() {
		einkaeufe.addElement(tfNeuerEintrag.getText());
		tfNeuerEintrag.setText("");
	}

	protected void loeschen() {
		int index = listEinkaeufe.getSelectedIndex();
		if (index != -1) {
			String eintrag = listEinkaeufe.getSelectedValue();
			if (eintrag.indexOf(':') == -1) {
				einkaeufe.remove(listEinkaeufe.getSelectedIndex());
			} else {
				String geschaeft = getGeschaeft(eintrag);
				System.out.println("loeschen(): Ausgewählter Eintrag: " + eintrag);
				for (int i = 0; i < einkaeufe.size(); i++) {
					String element = einkaeufe.getElementAt(i);
					System.out.println("loeschen(): Element = " + element);
					if (getGeschaeft(element).equals(geschaeft)) {
						einkaeufe.remove(i);
						System.out.println("loeschen(): Eintrag gelöscht: " + i);
						i--;
					}
				}
			}
		}
	}

}
