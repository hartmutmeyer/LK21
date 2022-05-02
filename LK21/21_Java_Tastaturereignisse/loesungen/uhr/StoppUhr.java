import java.awt.event.*;

public class StoppUhr extends Uhr implements KeyListener {

	private boolean running = false;

	public StoppUhr(int x, int y) {
		super(x, y);
	}

	@Override
	void sekunde() {
		if (running)
			super.sekunde();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			running = true;
			break;
		case KeyEvent.VK_E:
			running = false;
			break;
		case KeyEvent.VK_R:
			reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void reset() {
		z1.reset();
		z2.reset();
		z3.reset();
		z4.reset();
	}
}
