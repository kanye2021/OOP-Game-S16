import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class StartMenuView extends View {

    private final String CREATE_GAME = "Create New Game";
    private final String LOAD_GAME = "Load Game";
    private final String EXIT = "Exit";
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;



    public StartMenuView(){
        super();
        this.viewController = new StartMenuController(this);
    }

    @Override
    void render(Graphics g) {

        // Rectangles
        // All buttons have the same X position.
        // TODO: Someone come up with better maths to get y positions lol.
        int button_x_pos = B_WIDTH/2 - BUTTON_WIDTH/2;
        int button1_y_pos = B_HEIGHT/3 - BUTTON_HEIGHT/2;
        int button2_y_pos = B_HEIGHT/2 - BUTTON_HEIGHT/2;
        int button3_y_pos = (int)((double)B_HEIGHT/1.5) - BUTTON_HEIGHT/2;

        // Text in the rectangles
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        // Centering text in rectangle.
        // getting coord's to do so
        Rectangle2D r1 = fm.getStringBounds(CREATE_GAME, g);
        Rectangle2D r2 = fm.getStringBounds(LOAD_GAME, g);
        Rectangle2D r3 = fm.getStringBounds(EXIT, g);
        int x2 = (B_WIDTH - (int)r1.getWidth())/2;
        int y2 = (B_HEIGHT - (int)r1.getHeight())/2 + fm.getAscent();
        int x1 = (B_WIDTH - (int)r2.getWidth())/2;
        int y1 = button1_y_pos + (int)r1.getHeight() + fm.getAscent();
        int x3 = (B_WIDTH - (int)r3.getWidth())/2;
        int y3 = button3_y_pos + (int)r3.getHeight() + fm.getAscent();

        if (((StartMenuController)viewController).getSelected() == StartMenuController.Selected.LOAD_GAME) {
            g.setColor(Color.WHITE);
            g.fillRect(button_x_pos, button1_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.setColor(Color.BLACK);
            g.drawString(LOAD_GAME, x1, y1);
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(button_x_pos, button1_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.drawString(LOAD_GAME, x1, y1);
        }
        if (((StartMenuController)viewController).getSelected() == StartMenuController.Selected.CREATE_GAME) {
            g.setColor(Color.WHITE);
            g.fillRect(button_x_pos, button2_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.setColor(Color.BLACK);
            g.drawString(CREATE_GAME, x2, y2);
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(button_x_pos, button2_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.drawString(CREATE_GAME, x2, y2);
        }
        if (((StartMenuController)viewController).getSelected() == StartMenuController.Selected.EXIT) {
            g.setColor(Color.WHITE);
            g.fillRect(button_x_pos, button3_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.setColor(Color.BLACK);
            g.drawString(EXIT, x3, y3);
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(button_x_pos, button3_y_pos , BUTTON_WIDTH, BUTTON_HEIGHT );
            g.drawString(EXIT, x3, y3);
        }

        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }


}
