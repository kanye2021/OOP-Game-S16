import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/2/16.
 */
public class CreateNewGameViewController extends ViewController {

    public CreateNewGameViewController() {
        super();
    }

    public CreateNewGameViewController(View view) {
        super(view);
    }


    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM Create NEW GAME VC");

        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM Create New Game VC");
        }

        else if (key == KeyEvent.VK_ENTER) {
            System.out.println("Down pressed FROM Create new VC");

        }
        else {
            System.out.println("invalid key press FROM CREATE NEW VC");
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }
}


