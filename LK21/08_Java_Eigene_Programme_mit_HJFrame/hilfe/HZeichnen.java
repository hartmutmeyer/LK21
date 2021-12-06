package hilfe;

import java.awt.*;

public class HZeichnen {
	public static void drawDreieck(Graphics g, int x1, int y1, int x2, int y2,
			int x3, int y3) {
		int[] xxx = { x1, x2, x3 };
		int[] yyy = { y1, y2, y3 };
		g.drawPolygon(xxx, yyy, 3);
	}

	public static void fillDreieck(Graphics g, int x1, int y1, int x2, int y2,
			int x3, int y3) {
		int[] xxx = { x1, x2, x3 };
		int[] yyy = { y1, y2, y3 };
		g.fillPolygon(xxx, yyy, 3);
	}
}