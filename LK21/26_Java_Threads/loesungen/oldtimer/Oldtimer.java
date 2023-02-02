import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Oldtimer extends JFrame {
	// globale Variablen
	private static final int WIDTH = 660;
	private static final int HEIGHT = 160;
	private static final Color BACKGROUND = Color.LIGHT_GRAY;
	private JPanel zeichenflaeche;
	private JButton btnStart = new JButton();
	private JButton btnStopp = new JButton();
	private OldtimerTimer timer;
	private int x = 250;
	private Image car;
	private final static int STEHT = 0;
	private final static int FAEHRT = 1;
	private int zustandAuto = STEHT;

	public Oldtimer(final String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(1, 6, 5, 5));
		pnlButtons.setBackground(Color.WHITE);
		contentPane.add(pnlButtons, BorderLayout.CENTER);		
		JLabel lblDummy1 = new JLabel();
		pnlButtons.add(lblDummy1);
		JLabel lblDummy2 = new JLabel();
		pnlButtons.add(lblDummy2);
		btnStart.setText("Start");
		pnlButtons.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				start();
			}
		});
		btnStopp.setText("Stopp");
		pnlButtons.add(btnStopp);
		btnStopp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				stopp();
			}
		});
		btnStopp.setEnabled(false);
		JLabel lblDummy3 = new JLabel();
		pnlButtons.add(lblDummy3);
		JLabel lblDummy4 = new JLabel();
		pnlButtons.add(lblDummy4);
		zeichenflaeche = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				myPaint(g);
			}
		};
		zeichenflaeche.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		zeichenflaeche.setOpaque(true);
		zeichenflaeche.setBackground(BACKGROUND);
		contentPane.add(zeichenflaeche, BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		car = getToolkit().getImage(getClass().getResource("car.gif"));
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(car, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
		}
		if (mt.isErrorAny()) {
			System.out.println("Problem beim Laden eines Bildes!");
		}
	}

	private void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		g.drawImage(car, x, 70, this);
		x -= 3;
		if (x < -150) {
			x = 625;
		}
	}

	// Anfang Ereignisprozeduren
	private void start() {
		timer = new OldtimerTimer(10, this);
		timer.start();
		zustandAuto = FAEHRT;
		setButtonState();
	}

	private void stopp() {
		timer.beenden();
		zustandAuto = STEHT;
		setButtonState();
	}
	// Ende Ereignisprozeduren
	
	private void setButtonState() {
		if (zustandAuto == FAEHRT) {
			btnStart.setEnabled(false);
			btnStopp.setEnabled(true);
		} else {
			btnStart.setEnabled(true);
			btnStopp.setEnabled(false);
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Oldtimer("Oldtimer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}