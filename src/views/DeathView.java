package views;

import controllers.DeathViewController;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Matthew on 2/6/2016.
 */
public class DeathView extends View{

    //----------View Design stuff --------
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final int START_POSITION = 100;

    public DeathView() {
        super();
        this.viewController = new DeathViewController(this);
    }

    @Override
    public void render(Graphics g) {

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        int itemOffset = 1;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, B_WIDTH - 21, B_HEIGHT - 21);

        String titleString = "You have ran out of lifes! Game over!";
        Rectangle2D titleRectangle = fm.getStringBounds(titleString, g);
        int titleStringX = (int) (B_WIDTH / 2 - titleRectangle.getWidth() / 2);
        int titleStringY = (int) (0 * 20 + titleRectangle.getHeight() / 2 + fm.getAscent() + 11);

        g.setColor(Color.WHITE);
        g.drawString(titleString, titleStringX, titleStringY);
        g.drawRect(0, 0, B_WIDTH, 20);

        int i = 0;
        for (DeathViewController.MenuOptions option : DeathViewController.MenuOptions.values()) {
            String message = option.toString();
                Rectangle2D rectangle = fm.getStringBounds(message, g);

                int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
                int boxY = BUTTON_HEIGHT * i + START_POSITION;
                int boxDX = BUTTON_WIDTH;
                int boxDY = BUTTON_HEIGHT;

                int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
                int stringY = i * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_POSITION;

                Color primaryColor;
                Color secondaryColor;

                if (option == ( ((DeathViewController)this.viewController).getActiveItem() ) ) {
                    primaryColor = Color.WHITE;
                    secondaryColor = Color.BLACK;

                } else {
                    primaryColor = Color.BLACK;
                    secondaryColor = Color.WHITE;

                }

                g.setColor(primaryColor);
                g.fillRect(boxX, boxY, boxDX, boxDY);
                g.setColor(secondaryColor);
                g.drawString(message, stringX, stringY);
            ++i;
        }
    }

}
