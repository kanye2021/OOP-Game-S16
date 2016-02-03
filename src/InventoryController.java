import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class InventoryController extends ViewController {

    public InventoryController() {
        super();
    }

    public InventoryController(View view) {
        super(view);
    }

    @Override
    public void handleKeyPress(int key) {

        IOMediator ioMediator = IOMediator.getInstance();

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM IVC");
        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM IVC");
        }

        //else if (key == KeyEvent.VK_ENTER && selectedOption == Selected.CREATE_GAME) {
        //    System.out.println("Enter pressed FROM SMVC");
        //    ioMediator.setActiveView(IOMediator.Views.PAUSE);
        //
    }

    @Override
    public void handleKeyRelease(int key) {

    }
}
