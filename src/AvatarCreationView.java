import java.awt.Color;
import java.awt.Graphics;


public class AvatarCreationView extends View {

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 100);
		
	}

}
