import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

    public enum Views {
        startMenu,
        CreateNewGame;
    }

    private static IOMediator ioMediator = new IOMediator();

    private static View activeView;
    private static View startMenu;
    private static  View createNewGame;

    // A private Constructor prevents any other
    // class from instantiating.
    // If no view passed in,
    // Default view is StartMenuView
    private IOMediator() {
        System.out.println("IN IOMediator constructor");
        startMenu = new StartMenuView();
        createNewGame = new CreateNewGameView();
        activeView = startMenu;
    }

    // Static 'instance' method
    public static IOMediator getInstance() {
        return ioMediator;
    }

    // Other methods protected by singleton-ness

    protected static void setActiveView(Views view) {
        switch (view) {
            case startMenu:
                activeView = startMenu;
                break;

            case CreateNewGame:
                activeView = createNewGame;
                break;

            default:
                System.out.println("Bad enum in IOMediator");
        }
    }
    protected static View getActiveView() {
        return activeView;
    }

//    public IOMediator(View activeView) {
//        this.activeView = activeView;
//
//    }



    protected static void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.viewController.handleKeyPress(key);
        }

        else {
//            if (key == KeyEvent.VK_SPACE) {
//                System.out.println("Space pressed");
//            }
//
//            if (key == KeyEvent.VK_LEFT) {
//                System.out.println("Left pressed");
//            }
//
//            if (key == KeyEvent.VK_RIGHT) {
//                System.out.println("Right pressed");
//            }
//
//            if (key == KeyEvent.VK_UP) {
//                System.out.println("Up pressed");
//            }
//
//            if (key == KeyEvent.VK_DOWN) {
//                System.out.println("Down pressed");
//            }
        }
    }


    public void keyReleased(KeyEvent e) {

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
