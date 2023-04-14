import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NameService extends JFrame implements ActionListener {
	// globale Variablen
    JTextField tfAddressInput, tfAusgabeFQDN, tfAusgabeIP;

	public NameService(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{51, 224, 95, 0};
        gbl_contentPane.rowHeights = new int[]{25, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        JLabel lblAddressInput = new JLabel ("Adresse:");
        GridBagConstraints gbc_lblAddressInput = new GridBagConstraints();
        gbc_lblAddressInput.anchor = GridBagConstraints.WEST;
        gbc_lblAddressInput.insets = new Insets(0, 0, 5, 5);
        gbc_lblAddressInput.gridx = 0;
        gbc_lblAddressInput.gridy = 0;
        contentPane.add(lblAddressInput, gbc_lblAddressInput);
        tfAddressInput = new JTextField(20);
        GridBagConstraints gbc_tfAddressInput = new GridBagConstraints();
        gbc_tfAddressInput.anchor = GridBagConstraints.WEST;
        gbc_tfAddressInput.insets = new Insets(0, 0, 5, 5);
        gbc_tfAddressInput.gridx = 1;
        gbc_tfAddressInput.gridy = 0;
        contentPane.add(tfAddressInput, gbc_tfAddressInput);
        JButton btnAuswerten = new JButton("auswerten");
        GridBagConstraints gbc_btnAuswerten = new GridBagConstraints();
        gbc_btnAuswerten.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnAuswerten.insets = new Insets(0, 0, 5, 0);
        gbc_btnAuswerten.gridx = 2;
        gbc_btnAuswerten.gridy = 0;
        contentPane.add(btnAuswerten, gbc_btnAuswerten);
        btnAuswerten.addActionListener(this);
        JLabel lblAusgabeFQDN = new JLabel("Name:");
        GridBagConstraints gbc_lblAusgabeFQDN = new GridBagConstraints();
        gbc_lblAusgabeFQDN.anchor = GridBagConstraints.WEST;
        gbc_lblAusgabeFQDN.insets = new Insets(0, 0, 5, 5);
        gbc_lblAusgabeFQDN.gridx = 0;
        gbc_lblAusgabeFQDN.gridy = 1;
        contentPane.add(lblAusgabeFQDN, gbc_lblAusgabeFQDN);
        tfAusgabeFQDN = new JTextField(20);
        tfAusgabeFQDN.setEditable(false);
        GridBagConstraints gbc_tfAusgabeFQDN = new GridBagConstraints();
        gbc_tfAusgabeFQDN.anchor = GridBagConstraints.WEST;
        gbc_tfAusgabeFQDN.insets = new Insets(0, 0, 5, 5);
        gbc_tfAusgabeFQDN.gridx = 1;
        gbc_tfAusgabeFQDN.gridy = 1;
        contentPane.add(tfAusgabeFQDN, gbc_tfAusgabeFQDN);
        JLabel lblAusgabeIP = new JLabel("IP-Nummer:");
        GridBagConstraints gbc_lblAusgabeIP = new GridBagConstraints();
        gbc_lblAusgabeIP.anchor = GridBagConstraints.WEST;
        gbc_lblAusgabeIP.insets = new Insets(0, 0, 0, 5);
        gbc_lblAusgabeIP.gridx = 0;
        gbc_lblAusgabeIP.gridy = 2;
        contentPane.add(lblAusgabeIP, gbc_lblAusgabeIP);
        tfAusgabeIP = new JTextField(20);
        tfAusgabeIP.setEditable(false);
        GridBagConstraints gbc_tfAusgabeIP = new GridBagConstraints();
        gbc_tfAusgabeIP.insets = new Insets(0, 0, 0, 5);
        gbc_tfAusgabeIP.anchor = GridBagConstraints.WEST;
        gbc_tfAusgabeIP.gridx = 1;
        gbc_tfAusgabeIP.gridy = 2;
        contentPane.add(tfAusgabeIP, gbc_tfAusgabeIP);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
    public void actionPerformed(ActionEvent e)
    {
          try{
              String text = tfAddressInput.getText();
              InetAddress inet = InetAddress.getByName(text);
              tfAusgabeFQDN.setText(inet.getHostName());
              tfAusgabeIP.setText(inet.getHostAddress());
          }catch (Exception exc)
          {
              tfAusgabeFQDN.setText("Fehler: " + exc.getMessage());
              tfAusgabeIP.setText("Fehler");
          }
    }
    
    public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new NameService("Domain Name Service");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}