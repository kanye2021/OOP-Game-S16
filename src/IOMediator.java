import java.awt.event.KeyEvent;



/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

    public enum Views {
        START_MENU_VIEW,
        CREATE_GAME_VIEW,
        AVATAR_CREATION_VIEW,
        GAME_VIEW,
        INVENTORY_VIEW
    }

    private static IOMediator ioMediator = new IOMediator();
    // If adding new views, add it as a static class property here.
    private static View activeView;
    private static View startMenuView;
    private static View createNewGame;
    private static View gameView;

    // A private Constructor prevents any other
    // class from instantiating.
    // If no view passed in,
    // Default view is StartMenuView
    private IOMediator() {
        // Init all views below
        startMenuView = new StartMenuView();
        createNewGame = new CreateNewGameView();

        activeView = startMenuView;

        // TODO: REMOVE HACKY SHIT
        Map map = new Map();
        Entity ent = new Entity();
        gameView = new GameView(map, ent);
    }

    // Static 'instance' method
    public static IOMediator getInstance() {
        return ioMediator;
    }


    // Other methods protected by singleton-ness
    protected static void setActiveView(Views view) {
        switch (view) {
            case START_MENU_VIEW:
                activeView = startMenuView;
                break;
            case CREATE_GAME_VIEW:
                activeView = createNewGame;
                break;
            case AVATAR_CREATION_VIEW:
                activeView = createNewGame;
                break;
            case GAME_VIEW:
                activeView = gameView;
                break;
            case INVENTORY_VIEW:
                activeView = createNewGame;
                break;
            default:
                System.out.println("Bad enum in IOMediator");
        }
    }
    protected static View getActiveView() {
        return activeView;
    }

    protected static void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.viewController.handleKeyPress(key);
        }

        else {
            System.out.println("no active view lol?");
        }
    }


    public void keyReleased(KeyEvent e) {

        //TODO: Send keyRelease to activeView.viewController
        int key = e.getKeyCode();

//        if (key == KeyEvent.VK_LEFT) {
//            System.out.println("Left released");
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            System.out.println("Right released");
//
//        }
//
//        if (key == KeyEvent.VK_UP) {
//            System.out.println("Up released");
//
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            System.out.println("Down released");
//
//        }
    }
}
