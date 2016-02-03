import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created by Austin on 2/1/16.
 */
public class PauseView extends View {

	public PauseView() {
	    super();
        this.viewController = new StartMenuController(this);
	}
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(B_WIDTH, B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = overImage.getGraphics();
		
		
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
		
		g2.setColor(new Color(150, 150, 150));
		g2.fillRect(B_WIDTH, B_HEIGHT, B_WIDTH, B_HEIGHT);
		
		g.drawImage(overImage, (int) (B_WIDTH * 0.1), (int) (B_HEIGHT * 0.1), (int) (B_WIDTH * 0.8), (int) (B_HEIGHT * 0.8), null);
		
	}

}
