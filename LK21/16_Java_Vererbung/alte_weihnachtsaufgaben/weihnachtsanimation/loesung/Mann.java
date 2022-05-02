import java.awt.*;
import java.awt.event.*;
import hilfe.*;

public class Mann
{

   protected WeihnachtsAnimation main;
   protected final int YSTART = 70;
   protected int zeile = 0;
   protected int x=-80;
   protected int y=YSTART;
   protected int xSpeed = 5;

   public Mann(WeihnachtsAnimation main)
   {
      this.main = main;
   }

   public void zeichnen (Graphics g)
   {
        x=x+xSpeed;
        if (x>200+zeile*50)
        {
          switch (zeile)
          {
            case 0: main.h1.leuchtend = !main.h1.leuchtend;
                    break;
            case 1: main.h2.leuchtend = !main.h2.leuchtend;
                    break;
            case 2: main.h3.leuchtend = !main.h3.leuchtend;
                    break;
            case 3: main.h4.leuchtend = !main.h4.leuchtend;
                    break;
            case 4: main.h5.leuchtend = !main.h5.leuchtend;
                    break;
          }
          zeile++;
          if (zeile>4)
            zeile = 0;
          x=-80;
          y=YSTART+zeile*100;
        }
        g.drawImage(main.weihnachtsmann, x, y, main);
        
   }
}
