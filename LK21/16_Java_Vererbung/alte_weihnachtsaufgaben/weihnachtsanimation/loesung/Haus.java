import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Haus
{
   private WeihnachtsAnimation main;
   public int x;
   public int y;
   public boolean leuchtend = true;

   public Haus(WeihnachtsAnimation main, int x, int y)
   {
      this.main = main;
      this.x = x;
      this.y = y;
   }
   
   public void zeichnen (Graphics g)
   {
     //g.drawString(""+leuchtend, 100,y+50);
     if (!leuchtend)
        g.drawImage(main.haus1, x, y, main);
     else
        g.drawImage(main.haus2, x, y, main);
   }
}
