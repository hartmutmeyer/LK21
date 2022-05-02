import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VerliebterMann extends SpeedMann
{
   protected int aussetzen=0;

   public VerliebterMann(WeihnachtsAnimation main)
   {
      super(main);
   }

   public void zeichnen (Graphics g)
   {
     if (aussetzen>0)
     {
        aussetzen--;
        g.drawImage(main.weihnachtsmann2, x, y, main);
     }
     else
     {
         super.zeichnen(g);
         if (zeile == main.f1.zeile && x+75 > main.f1.x && x+72 < main.f1.x+10)
         {
           aussetzen = 30;
         }
         if (zeile == main.f2.zeile && x+75 > main.f2.x && x+72 < main.f2.x+10)
         {
           aussetzen = 30;
         }
         if (zeile == main.f3.zeile && x+75 > main.f3.x && x+72 < main.f3.x+10)
         {
           aussetzen = 30;
         }
     }
   }

}

