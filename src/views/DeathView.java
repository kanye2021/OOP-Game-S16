package views;

import controllers.DeathViewController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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

        Font f = new Font("Courier New", 1, 55);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics(f);

        int itemOffset = 1;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH , B_HEIGHT);

        String titleString = "Game over!";
        Rectangle2D titleRectangle = fm.getStringBounds(titleString, g);

        int titleStringX = (int) (B_WIDTH / 2 - titleRectangle.getWidth() / 2);
        int titleStringY = (int) (20 + titleRectangle.getHeight() / 2 + fm.getAscent() + 11);

        g.setColor(Color.WHITE);
        g.drawString(titleString, titleStringX, titleStringY);


        Font smallF = new Font("Courier New", 1, 18);
        g.setFont(smallF);
        FontMetrics fm2 = g.getFontMetrics(smallF);
        int i = 0;
        for (DeathViewController.MenuOptions option : DeathViewController.MenuOptions.values()) {
            String message = option.toString();
                Rectangle2D rectangle = fm2.getStringBounds(message, g);

                int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
                int boxY = BUTTON_HEIGHT * i + titleStringY + 50;
                int boxDX = BUTTON_WIDTH;
                int boxDY = BUTTON_HEIGHT;

                int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
                int stringY = i * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm2.getAscent() + titleStringY + 50;

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
