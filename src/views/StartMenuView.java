package views;

import controllers.StartMenuViewController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class StartMenuView extends View {

    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;

    public StartMenuView(){
        super();
        this.viewController = new StartMenuViewController(this);
    }

    @Override
    public void render(Graphics g) {

    	clear(g);
    	
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);
        g.setFont(VIEW_FONT);

        
        for (StartMenuViewController.MenuOptions option : StartMenuViewController.MenuOptions.values()) {
			
			Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
			int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
			int boxY = BUTTON_HEIGHT * option.ordinal();
			int boxDX = BUTTON_WIDTH;
			int boxDY = BUTTON_HEIGHT;
			int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
			int stringY = option.ordinal() * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent();
			
			Color primaryColor;
			Color secondaryColor;
			
			if (option == ((StartMenuViewController) viewController).getActiveItem()) {
			
				primaryColor = Color.WHITE;
				secondaryColor = Color.BLACK;
			
			} else {
				
				primaryColor = Color.BLACK;
				secondaryColor = Color.WHITE;
				
			}
			
			g.setColor(primaryColor);
			g.fillRect(boxX, boxY, boxDX, boxDY);
			g.setColor(secondaryColor);
			g.drawString(option.toString(), stringX, stringY);
			//g.drawRect(boxX, boxY, boxDX, boxDY);
			
		}
        
        Toolkit.getDefaultToolkit().sync();

    }

}
