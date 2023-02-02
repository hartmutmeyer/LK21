import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ProgrammStart extends JFrame {
	private JPanel contentPane;
	private JTextField tfProgramm;
	private Runtime runtime;
	private Process programm;
	private JButton btnStopp;

	public ProgrammStart() {
		createGUI();
		runtime = Runtime.getRuntime();
	}

	/**
	 * Create the frame.
	 */
	private void createGUI() {
		setTitle("Programm Starten");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgramm = new JLabel("Programm:");
		lblProgramm.setBounds(12, 12, 96, 15);
		contentPane.add(lblProgramm);
		
		tfProgramm = new JTextField();
		tfProgramm.setBounds(12, 39, 258, 19);
		contentPane.add(tfProgramm);
		tfProgramm.setColumns(10);
		
		JButton btnStartMitWarten = new JButton("Start mit Warten");
		btnStartMitWarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startenMitWarten();
			}
		});
		btnStartMitWarten.setBounds(12, 70, 144, 25);
		contentPane.add(btnStartMitWarten);
		
		JButton btnStartOhneWarten = new JButton("Start ohne Warten");
		btnStartOhneWarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startenOhneWarten();
			}
		});
		btnStartOhneWarten.setBounds(12, 107, 144, 25);
		contentPane.add(btnStartOhneWarten);
		
		btnStopp = new JButton("Stopp");
		btnStopp.setEnabled(false);
		btnStopp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programmBeenden();
			}
		});
		btnStopp.setBounds(168, 107, 102, 25);
		contentPane.add(btnStopp);		
	}

	private void programmBeenden() {
		if (programm != null) {
			programm.destroy();	
			btnStopp.setEnabled(false);
		}
	}

	private void startenOhneWarten() {
		try {
			programm = runtime.exec(tfProgramm.getText());
			btnStopp.setEnabled(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Das Programm \"" + tfProgramm.getText() + "\" existiert nicht, bzw. wurde nicht gefunden.");
		}	
	}

	private void startenMitWarten() {
		try {
			programm = runtime.exec(tfProgramm.getText());
			programm.waitFor();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Das Programm \"" + tfProgramm.getText() + "\" existiert nicht, bzw. wurde nicht gefunden.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgrammStart frame = new ProgrammStart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
