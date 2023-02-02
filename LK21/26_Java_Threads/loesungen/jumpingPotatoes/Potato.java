import java.awt.event.*;

class Potato extends MouseAdapter {
	private PotatoAnwendung main;
	private int yOriginal;
	protected int x;
	protected int y;
	
	public Potato(PotatoAnwendung main, int x, int y) {
		this.main = main;
		this.x = x;
		this.y = y;
		yOriginal = y;
		main.zeichenflaeche.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		if (mY > y && mY < y + 100 && mX > x && mX < x + 74) {
			System.out.println(e.getButton());
			if (e.getButton() == MouseEvent.BUTTON1) {
				y -= 80;
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				y -= 160;
			}
			main.repaint();
			PotatoTimer timer = new PotatoTimer(this);
			timer.start();
		}
	}

	public void timeout() {
		y = yOriginal;
		main.repaint();
	}
}
