import java.awt.Color;
import java.awt.Graphics;


public class PauseView extends View {

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
		
		g.setColor(new Color(150, 150, 150));
		g.fillRect((int) (WIDTH * 0.1), (int) (HEIGHT * 0.1), (int) (WIDTH * 0.8), (int) (HEIGHT * 0.8));
		
	}

}
