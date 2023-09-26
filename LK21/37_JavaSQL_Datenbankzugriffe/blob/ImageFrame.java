import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.awt.Component;

public class ImageFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con;
	private Random random = new Random();

	public ImageFrame() {
		createGUI();
		con = this.getConnection();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBrowse.addActionListener(this);
		contentPane.add(btnBrowse, BorderLayout.NORTH);
		JPanel panelImage = new JPanel() {
			@Override
			public void paint(Graphics g) {
				int image_id = 0;
				try {
					String query = "SELECT COUNT(*) FROM image";
					java.sql.PreparedStatement stmt = con.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					rs.next();
					image_id = random.nextInt(rs.getInt(1)) + 1;
				} catch (Exception ex) {
					System.out.println("Fehler beim Ermitteln der Anzahl der Bilder: " + ex.getMessage());
				}
				BufferedImage img = getImageById(image_id); // pass valid img_id
				if (img != null) {
					g.drawImage(img, 0, 0, this);
				}
			}
		};
		contentPane.add(panelImage, BorderLayout.CENTER);
	}

	public Connection getConnection() {
		try {
			// Creating connection to DB
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/images";
			Connection c = DriverManager.getConnection(url, "root", "root");
			return c;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}

	public void imageWrite(File file) {
		try {

			FileInputStream io = new FileInputStream(file);
			String query = "INSERT INTO image (img) VALUES(?)";
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setBinaryStream(1, (InputStream) io, (int) file.length());
			stmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Fehler beim Einfügen: " + ex.getMessage());
		}
	}

	public BufferedImage getImageById(int id) {
		String query = "SELECT img FROM image WHERE img_id = ?";
		BufferedImage buffimg = null;
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			result.next();
			InputStream img = result.getBinaryStream(1); // reading image as InputStream
			buffimg = ImageIO.read(img);    // decoding the InputStream as BufferedImage

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return buffimg;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile(); // path to image
			this.imageWrite(file); // inserting image into database
			JOptionPane.showMessageDialog(this, "Image inserted.", "ImageDemo", JOptionPane.PLAIN_MESSAGE);
			this.repaint();

		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageFrame frame = new ImageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
