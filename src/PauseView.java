import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Austin on 2/1/16.
 */
public class PauseView extends View {

	private final int itemHeight = 50;
	
	public PauseView() {
	    super();
        this.viewController = new PauseViewController(this);
	}
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(B_WIDTH, B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = overImage.getGraphics();
		
		Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = g.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
		
		for (PauseViewController.MenuOptions option : PauseViewController.MenuOptions.values()) {
			
			Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
			int stringX = B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
			int stringY = option.ordinal() * itemHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent();
			
			Color primaryColor;
			Color secondaryColor;
			
			if (option == ((PauseViewController) viewController).getActiveItem()) {
			
				primaryColor = new Color(150, 150, 150);
				secondaryColor = new Color(0, 0, 0);
			
			} else {
				
				primaryColor = new Color(0, 0, 0);
				secondaryColor = new Color(150, 150, 150);
				
			}
			
			g2.setColor(primaryColor);
			g2.fillRect(0, option.ordinal() * itemHeight, B_WIDTH - 1, itemHeight);
			g2.setColor(secondaryColor);
			g2.drawString(option.toString(), stringX, stringY);
			g2.drawRect(0, option.ordinal() * itemHeight, B_WIDTH - 1, itemHeight);
			
		}
		
		g.drawImage(overImage, (int) (B_WIDTH * 0.1), (int) (B_HEIGHT * 0.1), (int) (B_WIDTH * 0.8), (int) (B_HEIGHT * 0.8), null);
		
	}

}
