import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Caesar extends JFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 150;
	private JLabel lblKlartextDatei = new JLabel();
	private JTextField tfKlartextDatei = new JTextField();
	private JLabel lblVerschluesselteDatei = new JLabel();
	private JTextField tfVerschluesselteDatei = new JTextField();
	private JButton btnVerschluesseln = new JButton();
	private JButton btnEntschluesseln = new JButton();
	private JLabel lblSchluessel = new JLabel();
	private JTextField tfSchluessel = new JTextField();

	public Caesar(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(539, 150);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblKlartextDatei.setBounds(8, 24, 92, 16);
		lblKlartextDatei.setText("Klartext-Datei:");
		contentPane.add(lblKlartextDatei);
		tfKlartextDatei.setBounds(136, 21, 238, 24);
		tfKlartextDatei.setText("unverschluesselt.txt");
		contentPane.add(tfKlartextDatei);
		lblVerschluesselteDatei.setBounds(8, 56, 151, 16);
		lblVerschluesselteDatei.setText("verschlüsselte Datei:");
		contentPane.add(lblVerschluesselteDatei);
		tfVerschluesselteDatei.setBounds(136, 53, 238, 24);
		tfVerschluesselteDatei.setText("verschluesselt.txt");
		contentPane.add(tfVerschluesselteDatei);
		btnVerschluesseln.setBounds(386, 20, 130, 24);
		btnVerschluesseln.setText("Verschlüsseln");
		contentPane.add(btnVerschluesseln);
		btnVerschluesseln.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				verschluesseln();
			}
		});
		btnEntschluesseln.setBounds(386, 52, 130, 24);
		btnEntschluesseln.setText("Entschlüsseln");
		contentPane.add(btnEntschluesseln);
		btnEntschluesseln.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				entschluesseln();
			}
		});
		lblSchluessel.setBounds(8, 88, 61, 16);
		lblSchluessel.setText("Schlüssel:");
		contentPane.add(lblSchluessel);
		tfSchluessel.setBounds(136, 85, 145, 24);
		tfSchluessel.setText("12");
		contentPane.add(tfSchluessel);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void verschluesseln() {
		URL klartext = getClass().getResource(tfKlartextDatei.getText());
		URL verschluesselt = getClass().getResource(tfVerschluesselteDatei.getText());
		
		if (klartext == null) {
			JOptionPane.showMessageDialog(this, "Klartextdatei nicht gefunden!");
			return;
		}
		if (verschluesselt == null) {
			JOptionPane.showMessageDialog(this, "Verschlüsselte Datei nicht gefunden!");
			return;
		}
		try (InputStream is = new FileInputStream(klartext.getFile());
			InputStreamReader in = new InputStreamReader(is, "UTF-8");
			OutputStream os = new FileOutputStream(verschluesselt.getFile());
			OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			int zeichen;
			int key = Integer.parseInt(tfSchluessel.getText());
			while ((zeichen = in.read()) != -1) {
				char c = (char) zeichen;
				if (Character.isLetter(c)) {
					c = Character.toLowerCase(c);
					c = (char) (c + key);
					if (c > 'z') {
						c = (char) (c - 26);
					}
				}
				out.write(c);
			}
			out.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Fehler: " + e.getMessage());
		} 
	}

	public void entschluesseln() {
		URL klartext = getClass().getResource(tfKlartextDatei.getText());
		URL verschluesselt = getClass().getResource(tfVerschluesselteDatei.getText());
		
		if (klartext == null) {
			JOptionPane.showMessageDialog(this, "Klartextdatei nicht gefunden!");
			return;
		}
		if (verschluesselt == null) {
			JOptionPane.showMessageDialog(this, "Verschlüsselte Datei nicht gefunden!");
			return;
		}
		try (InputStream is = new FileInputStream(verschluesselt.getFile());
				InputStreamReader in = new InputStreamReader(is, "UTF-8");
				OutputStream os = new FileOutputStream(klartext.getFile());
				OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			int zeichen;
			int key = Integer.parseInt(tfSchluessel.getText());
			while ((zeichen = in.read()) != -1) {
				char c = (char) zeichen;
				if (Character.isLetter(c)) {
					c = Character.toLowerCase(c);
					c = (char) (c - key);
					if (c < 'a') {
						c = (char) (c + 26);
					}
				}
				out.write(c);
			}
			out.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Fehler: " + e.getMessage());
		} 
	}
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Caesar("Verschlüsselung: Caesar-Verfahren");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}