import javax.swing.*;
import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
abstract public class View extends JPanel {

    protected ViewController viewController;
    protected int width;
    protected int height;
    protected int x;
    protected int y;


    // Constants
    protected final int B_WIDTH = 700;
    protected final int B_HEIGHT = 600;

    public View() {
        initView();

    }

    private void initView() {
        setFocusable(true);
        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }

    // Subclasses need to implement to render
    // e.g.

    abstract void render(Graphics g);

//    protected void getImageDimensions() {
//
//        width = view.getWidth(null);
//        height = view.getHeight(null);
//    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
