import java.awt.EventQueue;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayMP3 extends JFrame {

	private MediaPlayer mediaPlayer;  // Muss als Attribut der Klasse deklariert werden, sonst
                                      // wird die Wiedergabe des Stücks nach ein paar Sekunden
                                      // abgebruchen, weil der Garbage Collector das MediaPlayer
                                      // Objekt abräumt ...
                                      // https://stackoverflow.com/questions/29870368/javafx-mediaplayer-music-stops-after-10-seconds

	public PlayMP3() {
		super("PlayMP3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		playClip();
	}

	public void playClip() {
		Media clip = new Media(getClass().getResource("Every_OS_Sucks.mp3").toString());  // Auch *.wav
		mediaPlayer = new MediaPlayer(clip);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(1);
		EventQueue.invokeLater(new Runnable() {
		    @Override
			public void run() {
		        new JFXPanel(); // initializes JavaFX environment
		        latch.countDown();
		    }
		});
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PlayMP3 frame = new PlayMP3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
