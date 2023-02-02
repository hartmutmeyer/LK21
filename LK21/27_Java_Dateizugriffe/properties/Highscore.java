package dateizugriffe.highscore;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Highscore extends JFrame {
	// globale Variablen
	private static final int WIDTH = 240;
	private static final int HEIGHT = 130;
	private JLabel lblName = new JLabel();
	private JTextField tfName = new JTextField();
	private JLabel lblPunkte = new JLabel();
	private JTextField tfPunkte = new JTextField();
	private JButton btSpeichern = new JButton();
	private JButton btLaden = new JButton();
	private URL url = getClass().getResource("highscore.properties");

	public Highscore(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(3, 2, 10, 10));
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setContentPane(contentPane);
		lblName.setText("Name:");
		contentPane.add(lblName);
		tfName.setText("");
		contentPane.add(tfName);
		lblPunkte.setText("Punktzahl:");
		contentPane.add(lblPunkte);
		tfPunkte.setText("");
		contentPane.add(tfPunkte);
		btSpeichern.setText("speichern");
		contentPane.add(btSpeichern);
		btSpeichern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				speichern();
			}
		});
		btLaden.setText("laden");
		contentPane.add(btLaden);
		btLaden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				laden();
			}
		});
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	// Anfang Ereignisprozeduren
	public void speichern() {
		if (url == null) {
			System.out.println("Fehler beim Schreiben: Die Datei existiert nicht!");
			return;
		}
		try (OutputStream os = new FileOutputStream(url.getFile());
				OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			Properties properties = new Properties();
			String name = tfName.getText();
			String punkte = tfPunkte.getText();
			properties.setProperty("Name", name);
			properties.setProperty("Punkte", punkte);
			properties.store(out, null);
			out.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void laden() {
		if (url == null) {
			System.out.println("Fehler beim Lesen: Die Datei existiert nicht!");
			return;
		}
		try (InputStream is = new FileInputStream(url.getFile());
				InputStreamReader in = new InputStreamReader(is, "UTF-8")) {
			Properties properties = new Properties();	
			properties.load(in);
			String name = properties.getProperty("Name");
			String punkte = properties.getProperty("Punkte");
			tfName.setText(name);
			tfPunkte.setText(punkte);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	// Ende Ereignisprozeduren

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Highscore("Highscore");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}