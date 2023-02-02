import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

public class Notizbuch extends JFrame {

	private JPanel contentPane;
	private JTextField tfNeueZeile;
	private DefaultListModel<String> notizen = new DefaultListModel<String>();
	private JList<String> listNotizen = new JList<String>(notizen);
	private URL url = getClass().getResource("notizen.txt");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Notizbuch frame = new Notizbuch();
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
	public Notizbuch() {
		erzeugeGUI();
		notizenLesen();
	}
	
	public void erzeugeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNotizbuch = new JLabel("Notizbuch");
		lblNotizbuch.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotizbuch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotizbuch.setBounds(10, 11, 572, 28);
		contentPane.add(lblNotizbuch);
		
		JLabel lblInhalt = new JLabel("Inhalt:");
		lblInhalt.setBounds(10, 50, 46, 14);
		contentPane.add(lblInhalt);
		
		JScrollPane scrollPaneNotizen = new JScrollPane();
		scrollPaneNotizen.setBounds(10, 75, 572, 225);
		contentPane.add(scrollPaneNotizen);
		
		listNotizen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneNotizen.setViewportView(listNotizen);
		
		JButton btnMarkierteZeileLschen = new JButton("markierte Zeile löschen");
		btnMarkierteZeileLschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				zeileLoeschen();
			}
		});
		btnMarkierteZeileLschen.setBounds(232, 311, 170, 23);
		contentPane.add(btnMarkierteZeileLschen);
		
		JButton btnNotizbuchLschen = new JButton("Notizbuch löschen");
		btnNotizbuchLschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notizbuchLoeschen();
			}
		});
		btnNotizbuchLschen.setBounds(412, 311, 170, 23);
		contentPane.add(btnNotizbuchLschen);
		
		JLabel lblHinzufgen = new JLabel("Hinzufügen:");
		lblHinzufgen.setBounds(10, 348, 80, 14);
		contentPane.add(lblHinzufgen);
		
		tfNeueZeile = new JTextField();
		tfNeueZeile.setBounds(100, 345, 381, 20);
		contentPane.add(tfNeueZeile);
		tfNeueZeile.setColumns(10);
		
		JButton btnSpeichern = new JButton("speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notizHinzufuegen();
			}
		});
		btnSpeichern.setBounds(491, 344, 91, 23);
		contentPane.add(btnSpeichern);
	}
	
	public void notizenLesen() {
		int zeichen;
		String zeile = "";

		notizen.clear();
		if (url == null) {
			JOptionPane.showMessageDialog(this, "Keine Notiz-Datei gefunden!");
			return;
		}
		try (InputStream is = new FileInputStream(url.getFile());
				InputStreamReader in = new InputStreamReader(is, "UTF-8")) {
			while ((zeichen = in.read()) != -1) {
				if (((char) zeichen) != '$') {
					zeile += (char) zeichen;
				} else {
					notizen.addElement(zeile);
					zeile = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void notizbuchSchreiben() {
		if (url == null) {
			JOptionPane.showMessageDialog(this, "Keine Notiz-Datei gefunden!");
			return;
		}
		try (OutputStream os = new FileOutputStream(url.getFile(), false);
			OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			for (int i=0; i < notizen.getSize(); i++) {
				out.write(notizen.get(i) + "$");
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		notizenLesen();
	}
	
	public void notizHinzufuegen() {
		String neueZeile = tfNeueZeile.getText();
		if (!neueZeile.isEmpty()) {
			if (url == null) {
				JOptionPane.showMessageDialog(this, "Keine Notiz-Datei gefunden!");
				return;
			}
			try (OutputStream os = new FileOutputStream(url.getFile(), true);
					OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
				out.write(neueZeile + "$");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			JOptionPane.showMessageDialog(null, "Es wurde kein Text eingegeben - die Datei wurde nicht verändert!");
		}
		tfNeueZeile.setText("");
		notizenLesen();
	}
	
	public void zeileLoeschen() {
		int gewaehlteZeile = listNotizen.getSelectedIndex();
		if (gewaehlteZeile != -1) {
			notizen.remove(gewaehlteZeile);
			notizbuchSchreiben();
		} else {
			JOptionPane.showMessageDialog(null, "Es wurde kein Eintrag zum Löschen ausgewählt ...");
		}
	}
	
	public void notizbuchLoeschen() {
		if (url == null) {
			JOptionPane.showMessageDialog(this, "Keine Notiz-Datei gefunden!");
			return;
		}
		try (OutputStream os = new FileOutputStream(url.getFile(), false);
				OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8")) {
			// Nichts tun: Datei wird leer überschrieben!
		} catch (IOException e) {
			e.printStackTrace();
		} 
		notizenLesen();
	}
}
