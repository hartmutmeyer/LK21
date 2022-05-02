import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Frau
{
   protected WeihnachtsAnimation main;
   public int x;
   public int zeile;
   protected final int YSTART = 40;
   protected int y;

   public Frau(WeihnachtsAnimation main)
   {
      this.main = main;
      x = 50 + main.zufall.nextInt(100);
      zeile = main.zufall.nextInt(5);
      y=YSTART+zeile*100;
   }

   public void zeichnen (Graphics g)
   {
     g.drawImage(main.frau, x, y, main);
   }
}
