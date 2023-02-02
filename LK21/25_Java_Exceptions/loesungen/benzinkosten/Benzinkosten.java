import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Benzinkosten extends JFrame {
	// globale Variablen
	private static final int WIDTH = 320;
	private static final int HEIGHT = 230;
	private JTextField tfStrecke = new JTextField();
	private JTextField tfVerbrauch = new JTextField();
	private JTextField tfBenzinpreis = new JTextField();
	private JTextField tfBenzinkosten = new JTextField();

	public Benzinkosten(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblStrecke = new JLabel();
		lblStrecke.setBounds(16, 16, 49, 16);
	    lblStrecke.setText("Strecke:");
	    contentPane.add(lblStrecke);
		tfStrecke.setHorizontalAlignment(SwingConstants.RIGHT);
		tfStrecke.setBounds(104, 8, 89, 24);
	    contentPane.add(tfStrecke);
		JLabel lblkm = new JLabel();
		lblkm.setBounds(200, 16, 34, 16);
	    lblkm.setText("km");
	    contentPane.add(lblkm);
		JLabel lblVerbrauch = new JLabel();
		lblVerbrauch.setBounds(16, 48, 65, 16);
	    lblVerbrauch.setText("Verbrauch:");
	    contentPane.add(lblVerbrauch);
		tfVerbrauch.setHorizontalAlignment(SwingConstants.RIGHT);
		tfVerbrauch.setBounds(104, 40, 89, 24);
	    contentPane.add(tfVerbrauch);
		JLabel lblLiterAuf100km = new JLabel();
		lblLiterAuf100km.setBounds(200, 48, 107, 16);
	    lblLiterAuf100km.setText("Liter auf 100 km");
	    contentPane.add(lblLiterAuf100km);
		JLabel lblBenzinpreis = new JLabel();
		lblBenzinpreis.setBounds(16, 80, 73, 16);
	    lblBenzinpreis.setText("Benzinpreis:");
	    contentPane.add(lblBenzinpreis);
		tfBenzinpreis.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBenzinpreis.setBounds(104, 72, 89, 24);
	    contentPane.add(tfBenzinpreis);
		JLabel lblEuroProLiter = new JLabel();
		lblEuroProLiter.setBounds(200, 80, 95, 16);
	    lblEuroProLiter.setText("Euro pro Liter");
	    contentPane.add(lblEuroProLiter);
		JButton btnBerechnen = new JButton();
		btnBerechnen.setBounds(16, 112, 177, 33);
	    btnBerechnen.setText("Preis berechnen");
	    contentPane.add(btnBerechnen);
	    btnBerechnen.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		        button1ActionPerformed(evt);
		      }
		    });
		JLabel lblBenzinkosten = new JLabel();
		lblBenzinkosten.setBounds(16, 176, 83, 16);
	    lblBenzinkosten.setText("Benzinkosten:");
	    contentPane.add(lblBenzinkosten);
		tfBenzinkosten.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBenzinkosten.setBounds(104, 168, 89, 24);
	    tfBenzinkosten.setEditable(false);
	    contentPane.add(tfBenzinkosten);
		JLabel lblEuro = new JLabel();
		lblEuro.setBounds(200, 176, 28, 16);
	    lblEuro.setText("Euro");
	    contentPane.add(lblEuro);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	// Anfang Ereignisprozeduren
	public void button1ActionPerformed(ActionEvent evt) {
		double strecke = 0.0;
		try {
			strecke = Double.parseDouble(tfStrecke.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Die Strecke ist nicht als Zahl zu lesen. Evtl. Komma statt Dezimalpunkt verwendet?");
		}
		double verbrauch = 0.0;
		try {
			verbrauch = Double.parseDouble(tfVerbrauch.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Der Verbrauch ist nicht als Zahl zu lesen. Evtl. Komma statt Dezimalpunkt verwendet?");
		}
		double preis = 0.0;
		try {
			preis = Double.parseDouble(tfBenzinpreis.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Der Preis ist nicht als Zahl zu lesen. Evtl. Komma statt Dezimalpunkt verwendet?");
		}
		double kosten = strecke / 100 * verbrauch * preis;
		tfBenzinkosten.setText("" + kosten);
	}
	// Ende Ereignisprozeduren

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Benzinkosten("Benzinkosten");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
