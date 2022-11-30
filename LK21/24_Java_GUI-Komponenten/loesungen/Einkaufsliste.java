import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Einkaufsliste extends JFrame {

	private JPanel contentPane;
	private JTextField tfNeuerEintrag;
	private DefaultListModel<String> einkaufsliste = new DefaultListModel<String>();
	private JList<String> listEinkaeufe = new JList<String>(einkaufsliste);

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
	}

	private void createGUI() {
		setTitle("Einkaufliste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 348);
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
		tfNeuerEintrag.setBounds(10, 11, 305, 20);
		contentPane.add(tfNeuerEintrag);
		tfNeuerEintrag.setColumns(10);
		
		JButton btnHinzufuegen = new JButton("Hinzufügen");
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hinzufuegen();
			}
		});
		btnHinzufuegen.setBounds(327, 9, 158, 23);
		contentPane.add(btnHinzufuegen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 475, 230);
		contentPane.add(scrollPane);
		listEinkaeufe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(listEinkaeufe);
		
		JButton btnEintragLoeschen = new JButton("Eintrag Löschen");
		btnEintragLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loeschen();
			}
		});
		btnEintragLoeschen.setBounds(327, 284, 158, 23);
		contentPane.add(btnEintragLoeschen);
		setResizable(false);
	}

	private void loeschen() {
		einkaufsliste.remove(listEinkaeufe.getSelectedIndex());
	}

	private void hinzufuegen() {
		einkaufsliste.addElement(tfNeuerEintrag.getText());
		tfNeuerEintrag.setText("");
		tfNeuerEintrag.requestFocusInWindow();
	}
}
