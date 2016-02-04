package views;

import controllers.ViewController;

import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public abstract class View {

    // Constants
    public static int B_WIDTH = 700;
    public static int B_HEIGHT = 600;

    protected ViewController viewController;

    public ViewController getViewController(){
        return viewController;
    }

    public View() { }

    // Subclasses need to implement to render
    // e.g.

    public abstract void render(Graphics g);

    public final void clear(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    }
    
}