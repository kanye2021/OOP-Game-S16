import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class StartMenuController extends ViewController {

    public enum Selected{
        CREATE_GAME {
            @Override
            public Selected prev() {
                return values()[values().length-1]; // rollover to the last
            };
        },
        LOAD_GAME,
        EXIT {
            @Override
            public Selected next() {
                return values()[0]; // rollover to the first
            };
        };
        public Selected next() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() + 1];
        }

        public Selected prev() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() - 1];
        }
    }

    private Selected selectedOption;

    public StartMenuController() {
        super();
    }

    public StartMenuController(View view) {
        super(view);
        selectedOption = Selected.CREATE_GAME;
    }

    public Selected getSelected() {
        return selectedOption;
    }

    @Override
    public void handleKeyPress(int key) {

        IOMediator ioMediator = IOMediator.getInstance();

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM SMVC");
            selectedOption = selectedOption.next();

        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM SMVC");
            selectedOption = selectedOption.prev();
        }

        else if (key == KeyEvent.VK_ENTER && selectedOption == Selected.CREATE_GAME) {
            System.out.println("Enter pressed FROM SMVC");
            ioMediator.setActiveView(IOMediator.Views.CREATE_GAME);

        }
        //TODO: REMOVE HAKCY ASS SHIT HOE
        else if(key == KeyEvent.VK_Q){
            ioMediator.setActiveView(IOMediator.Views.GAME);
        }
        else {
            System.out.println("invalid key press FROM SMVC");
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }
}
