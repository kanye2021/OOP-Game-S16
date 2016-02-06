package views;

import controllers.AvatarCreationViewController;
import controllers.CreateNewGameViewController;
import controllers.StartMenuViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class AvatarCreationView extends View {

	private final String AVATAR_CREATE_TEXT = "Please select an Occupation";




	public AvatarCreationView(){
		super();
		this.viewController = new AvatarCreationViewController(this);
	}

	@Override
	public void render(Graphics g) {
		clear(g); // clear the background
		

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 100);

		// Text
		Font small = new Font("Helvetica", Font.ITALIC, 14);
		Font title = new Font("Helvetica", Font.BOLD, 20);
		FontMetrics fm = g.getFontMetrics(title);
		g.setColor(Color.white);
		g.setFont(title);
		Rectangle2D r1 = fm.getStringBounds(AVATAR_CREATE_TEXT, g);
		int x = (View.B_WIDTH - (int)r1.getWidth())/2;
		int y = (View.B_HEIGHT - (int)r1.getHeight())/7 + fm.getAscent();
		g.drawString(AVATAR_CREATE_TEXT, x, y);


		// Paint Occupation Titles + Descriptions
		int stringX = 100;
		int arrow_x_offset = stringX;
		int stringY = View.B_HEIGHT/3;
		int stringY2 = 0;
		int prevY = 0;
		for (AvatarCreationViewController.OccupationOptions occupation : AvatarCreationViewController.OccupationOptions.values()) {

			fm = g.getFontMetrics(title);
			Rectangle2D rectangle1 = fm.getStringBounds(occupation.getText(), g);
			fm = g.getFontMetrics(small);
			Rectangle2D rectangle2 = fm.getStringBounds(occupation.getDescription(), g);



			stringY = prevY != 0 ? prevY + ((int)rectangle2.getHeight()) + 50 : stringY;
			stringY2 = ((int) (rectangle1.getHeight()) + fm.getAscent()) + stringY;

			Color primaryColor = Color.white;

			if (occupation == ((AvatarCreationViewController) viewController).getSelectedOccupation()) {
				// Drawing Arrow next to selection
				ImageIcon ii = new ImageIcon("./src/res/arrow.png");
				Image arrow = ii.getImage();
				arrow_x_offset-= arrow.getWidth(null) + 10;
				g.drawImage(arrow, arrow_x_offset, stringY, Display.getInstance());
			}

			g.setColor(primaryColor);
			g.setFont(title);
			g.drawString(occupation.getText(), stringX, stringY);
			g.setFont(small);
			g.drawString(occupation.getDescription(), stringX, stringY2);

			prevY = stringY2;


		}

		Toolkit.getDefaultToolkit().sync();
		
	}

}
