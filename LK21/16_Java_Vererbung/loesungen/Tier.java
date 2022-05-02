import java.awt.Graphics;

public class Tier {
    private boolean weiblich;
    
    public Tier(boolean weiblich) {
    	this.weiblich = weiblich;
    }
    
    public boolean getWeiblich(){
    	return weiblich;
    }
    
    public void zeichnen (Graphics g, int x, int y) {
    	// Hier wird ein Tier gezeichnet ...

    }
}
