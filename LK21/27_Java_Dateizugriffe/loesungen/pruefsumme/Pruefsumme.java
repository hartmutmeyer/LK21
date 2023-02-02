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

public class Pruefsumme extends JFrame {
	// globale Variablen
	private static final int WIDTH = 465;
	private static final int HEIGHT = 300;
	private JLabel lblEingabeDatei = new JLabel();
	private JLabel lblAusgabeDatei = new JLabel();
	private JTextField tfEingabeDatei = new JTextField();
	private JTextField tfAusgabeDatei = new JTextField();
	private JButton btnPruefsummeErzeugen = new JButton();
	private JButton btnUeberpruefen = new JButton();

	public Pruefsumme(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblEingabeDatei.setBounds(24, 48, 84, 16);
		lblEingabeDatei.setText("Eingabedatei:");
		contentPane.add(lblEingabeDatei);
		lblAusgabeDatei.setBounds(24, 96, 88, 16);
		lblAusgabeDatei.setText("Ausgabedatei:");
		contentPane.add(lblAusgabeDatei);
		tfEingabeDatei.setBounds(120, 40, 233, 24);
		contentPane.add(tfEingabeDatei);
		tfAusgabeDatei.setBounds(120, 88, 233, 24);
		contentPane.add(tfAusgabeDatei);
		btnPruefsummeErzeugen.setBounds(24, 144, 185, 25);
		btnPruefsummeErzeugen.setText("Prüfsumme generieren");
		contentPane.add(btnPruefsummeErzeugen);
		btnPruefsummeErzeugen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				generieren();
			}
		});
		btnUeberpruefen.setBounds(221, 144, 217, 25);
		btnUeberpruefen.setText("Datei auf Korrektheit überprüfen");
		contentPane.add(btnUeberpruefen);
		btnUeberpruefen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				pruefen();
			}
		});
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		tfEingabeDatei.setText("test.txt");
		tfAusgabeDatei.setText("testP.txt");
	}

	public void generieren() {
		URL eingabe = getClass().getResource(tfEingabeDatei.getText());
		if (eingabe == null) {
			JOptionPane.showMessageDialog(this, "Eingabedatei nicht gefunden!");
			return;
		}
		URL ausgabe = getClass().getResource(tfAusgabeDatei.getText());
		if (ausgabe == null) {
			JOptionPane.showMessageDialog(this, "Ausgabedatei nicht gefunden!");
			return;
		}
		int pruefsumme = 0;
		int zeichen;
		try (InputStream is = new FileInputStream(eingabe.getFile());
				InputStreamReader in = new InputStreamReader(is, "UTF-8");
				OutputStream os = new FileOutputStream(ausgabe.getFile());
				OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			out.write('$');
			while ((zeichen = in.read()) != -1) {
				out.write(zeichen);
				pruefsumme ^= zeichen;
			}
			out.write(pruefsumme);
			out.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Fehler beim Dateizugriff!");
		} 
	}

	public void pruefen() {
		URL ausgabe = getClass().getResource(tfAusgabeDatei.getText());
		if (ausgabe == null) {
			JOptionPane.showMessageDialog(this, "Ausgabedatei nicht gefunden!");
			return;
		}
		try (InputStream is = new FileInputStream(ausgabe.getFile());
				InputStreamReader in = new InputStreamReader(is, "UTF-8")) {
			int zeichen = in.read();
			int pruefsumme = 0;
			String text = "";
			if (zeichen == '$') {
				while ((zeichen = in.read()) != -1) {
					text += (char) zeichen;
				}
				char pruefsummeInDatei = text.charAt(text.length() - 1);
				text = text.substring(0, text.length() - 1);
				for (int i = 0; i < text.length(); i++) {
					pruefsumme ^= text.charAt(i);
				}
				if (pruefsumme == pruefsummeInDatei) {
					JOptionPane.showMessageDialog(this,
							"Diese Datei wurde nicht verfälscht! ",
							"Prüfung erfolgreich",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"Diese Datei wurde verfälscht!", "Warnung",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Diese Datei enthält keine Prüfsumme!", "Fehler",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Fehler: " + e.getMessage());
		} 
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Pruefsumme("Prüfsumme");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}