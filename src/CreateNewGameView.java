import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/2/16.
 */
public class CreateNewGameView extends View {

    private final String NEW_GAME_TEXT = "Create New Game";




    public CreateNewGameView(){
        super();
        this.viewController = new CreateNewGameViewController(this);
    }

    @Override
    void render(Graphics g) {
        // Text in the rectangles
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = g.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        g.drawString(NEW_GAME_TEXT, B_WIDTH/2, B_HEIGHT/2);

        Toolkit.getDefaultToolkit().sync();

    }

    public void testMethod() {
        System.out.println("calling something from create view");
    }



}
