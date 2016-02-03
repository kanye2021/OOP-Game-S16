import javax.swing.*;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

    public static enum Views {
    	
    	// Views.START_MENU_VIEW.render(g);
    	
        START_MENU(new StartMenuView()) {void render(Graphics g) {getView().render(g);}},
        CREATE_GAME(new CreateNewGameView()) {void render(Graphics g) {getView().render(g);}},
        AVATAR_CREATION(new AvatarCreationView()) {void render(Graphics g) {getView().render(g);}},
        PAUSE(new PauseView()) {void render(Graphics g) {getView().render(g);}},
        
        // TODO: REMOVE HACKY SHIT
        GAME(new GameView(new Map(), new Entity())) {void render(Graphics g) {getView().render(g);}},
        INVENTORY(new InventoryView(new Inventory())) {void render(Graphics g) {getView().render(g);}};
        
        abstract void render(Graphics g);
        
        private View view;
        
        public View getView() {
        	return view;
        }

        private Views(View view) {
        	this.view = view;
        }
        
    }

    private static IOMediator ioMediator = new IOMediator();
    // If adding new views, add it as a static class property here.
    private static Views activeView;

    // A private Constructor prevents any other
    // class from instantiating.
    // If no view passed in,
    // Default view is StartMenuView
    private IOMediator() {
        // Init all views below
        activeView = Views.START_MENU;
    }

    // Static 'instance' method
    public static IOMediator getInstance() {
        return ioMediator;
    }


    // Other methods protected by singleton-ness
    protected static void setActiveView(Views view) {
        activeView = view;
    }
    protected static View getActiveView() {
        return activeView.getView();
    }

    protected static void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.getView().viewController.handleKeyPress(key);
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
