import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/2/16.
 */
public class CreateNewGameView extends View {

    private final String CREATE_GAME = "Create New Game";
    private final String LOAD_GAME = "Load Game";
    private final String EXIT = "Exit";
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;



    public CreateNewGameView(){
        super();
//        this.viewController
    }

    @Override
    void render(Graphics g) {


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

}
