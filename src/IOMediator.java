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



}
