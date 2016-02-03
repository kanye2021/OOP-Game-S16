import java.awt.Color;
import java.awt.Graphics;


public class PauseView extends View {

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
		
		g.setColor(new Color(150, 150, 150));
		g.fillRect((int) (B_WIDTH * 0.1), (int) (B_HEIGHT * 0.1), (int) (B_WIDTH * 0.8), (int) (B_HEIGHT * 0.8));
		
	}

}
