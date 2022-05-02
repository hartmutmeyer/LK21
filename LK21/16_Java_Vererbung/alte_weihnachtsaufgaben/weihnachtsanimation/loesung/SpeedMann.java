import java.awt.*;
import java.awt.event.*;

public class SpeedMann extends Mann
{
   protected int counter=0;

   public SpeedMann(WeihnachtsAnimation main)
   {
      super(main);
   }
   
   public void zeichnen (Graphics g)
   {
     counter++;
     if (counter==10)
     {
          xSpeed = main.zufall.nextInt(10);
          counter = 0;
     }
     super.zeichnen(g);
   }
   
}

