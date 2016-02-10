package views;

import controllers.ViewController;

import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public abstract class View {

    // Constants
    public static final int B_WIDTH = 1200;
    public static final int B_HEIGHT = 812;
    public static final int TILE_SIZE = 50;

    public static final Font VIEW_FONT = new Font("Helvetica", Font.BOLD, 14);


    // Subclasses need to implement to render
    // e.g.

    public static void render(Graphics g){
        clear(g);
    }

    public static final void clear(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    }

}
