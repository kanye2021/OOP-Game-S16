import javax.swing.*;
import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public abstract class View {

    // Constants
    public static int B_WIDTH = 700;
    public static int B_HEIGHT = 600;

    protected ViewController viewController;
    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public View() { }

    // Subclasses need to implement to render
    // e.g.

    abstract void render(Graphics g);



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
