package views;

import controllers.SaveGameController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameView extends View {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int Y_OFFSET = View.B_HEIGHT / 6;

    private static  SaveGameView saveGameView = new SaveGameView();
    private SaveGameView() {}


    public static void init(){

    }


    public static void render(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        clear(g);

        FontMetrics fm = g.getFontMetrics(small);
        g.setFont(small);


        int fraction = 1;
        for (SaveGameController.SaveOptions option : SaveGameController.SaveOptions.values()) {

            //Box Stuff
            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
            int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
            int boxY = Y_OFFSET + ((fraction) * View.B_HEIGHT / 6) - BUTTON_HEIGHT / 2;
            int boxDX = BUTTON_WIDTH;
            int boxDY = BUTTON_HEIGHT;

            // String stuff
            Rectangle2D r = fm.getStringBounds(option.getText(), g);
            int stringX = View.B_WIDTH / 2 - (int) (r.getWidth() / 2);
            int stringY = boxY + (int) (r.getHeight()) + fm.getAscent();

            Color primaryColor;
            Color secondaryColor;

            if (option == ((SaveGameController) viewController).getSelectedOption()) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;
            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;
            }

            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(option.getText(), stringX, stringY);
            g.drawRect(boxX, boxY, boxDX, boxDY);

            fraction += 1;

        }

        Toolkit.getDefaultToolkit().sync();

    }
}
