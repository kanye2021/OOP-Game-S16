package views;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class AvatarCreationView extends View {

	private final String AVATAR_CREATE_TEXT = "Please select an Occupation";

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 100);

		// Text
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = g.getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		Rectangle2D r1 = fm.getStringBounds(AVATAR_CREATE_TEXT, g);
		int x = (View.B_WIDTH - (int)r1.getWidth())/2;
		int y = (View.B_HEIGHT - (int)r1.getHeight())/2 + fm.getAscent();
		g.drawString(AVATAR_CREATE_TEXT, x, y);
		
	}

}
