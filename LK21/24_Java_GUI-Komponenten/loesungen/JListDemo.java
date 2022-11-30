import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class JListDemo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DefaultListModel<String> listenInhalt = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(listenInhalt);
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListDemo frame = new JListDemo();
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
	public JListDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 252, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("hinzufügen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				elementHinzufuegen();
			}
		});
		btnNewButton.setBounds(274, 10, 150, 23);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 414, 207);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnEintragLschen = new JButton("Eintrag löschen");
		btnEintragLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elementLoeschen();
			}
		});
		btnEintragLschen.setBounds(274, 262, 150, 25);
		contentPane.add(btnEintragLschen);
	}
	
	private void elementHinzufuegen() {
		listenInhalt.addElement(textField.getText());
		textField.setText("");
	}
	
	private void elementLoeschen() {
		int auswahl = list.getSelectedIndex();
		if (auswahl != -1) {
			listenInhalt.remove(auswahl);		
		}
	}
}
