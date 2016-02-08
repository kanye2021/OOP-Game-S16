package views;

import controllers.StartMenuViewController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class StartMenuView extends View {

    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 32;

    //title
    private final String TITLE = "Kanye 2020";
    private final Font TITLE_FONT = new Font("Brush Script MT", Font.BOLD, 100);

    private final int TITLE_BUTTON_MARGIN = (int) (B_HEIGHT * 0.15);


    public StartMenuView() {
        super();
        this.viewController = new StartMenuViewController(this);
    }

    @Override
    public void render(Graphics g) {

        renderBackground(g);

        renderTitle(g);

        renderButtons(g);

        Toolkit.getDefaultToolkit().sync();
    }


    private void renderBackground(Graphics g) {
        clear(g);
    }

    private void renderTitle(Graphics g) {
        g.setColor(new Color(255, 255, 255));

        g.setFont(TITLE_FONT);
        FontMetrics fm = g.getFontMetrics();

        g.drawString("Kanye 2020", View.B_WIDTH / 2 - fm.stringWidth(TITLE) / 2, fm.getHeight());
    }

    private void renderButtons(Graphics g) {

        int start = g.getFontMetrics(TITLE_FONT).getHeight() + TITLE_BUTTON_MARGIN;

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        for (StartMenuViewController.MenuOptions option : StartMenuViewController.MenuOptions.values()) {

            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);

            int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
            int boxY = BUTTON_HEIGHT * option.ordinal() + start;
            int boxDX = BUTTON_WIDTH;
            int boxDY = BUTTON_HEIGHT;

            int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = option.ordinal() * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + start;

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
        }
    }
}
