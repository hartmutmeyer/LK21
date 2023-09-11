import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ZeichenExtrahieren extends JFrame {

	private JPanel contentPane;
	private JTextField tfEingabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZeichenExtrahieren frame = new ZeichenExtrahieren();
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
	public ZeichenExtrahieren() {
		setTitle("Aufgabe 2: Zeichen Extrahieren");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 117);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfEingabe = new JTextField();
		tfEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeichenExtrahieren();
			}
		});
		tfEingabe.setBounds(12, 12, 418, 19);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		JButton btnZeichenExtrahieren = new JButton("Zeichen Extrahieren");
		btnZeichenExtrahieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeichenExtrahieren();
			}
		});
		btnZeichenExtrahieren.setBounds(12, 43, 418, 25);
		contentPane.add(btnZeichenExtrahieren);
	}

	private void zeichenExtrahieren() {
		String eingabe = tfEingabe.getText();
		int posDollarSign = eingabe.indexOf('$');
		int posOfCharacter = 0;
		if (posDollarSign == -1) {
			JOptionPane.showMessageDialog(this, "Das Dollarzeichen fehlt");
			return;
		}
		try {
			posOfCharacter = Integer.parseInt(eingabe.substring(0, posDollarSign));
			if (posOfCharacter <= 0) {
				JOptionPane.showMessageDialog(this, "Die Zahl muss positiv und größer als Null sein.");
				return;
			}
			JOptionPane.showMessageDialog(this, "'" + eingabe.charAt(posDollarSign + posOfCharacter) + "'");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Die Zeichenkette muss mit einer Zahl beginnen.");
		} catch (StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(this, "Das angegebene Zeichen existiert nicht.");
		}
	}

}
