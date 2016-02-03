package utilities;

import models.Entity;
import models.Inventory;
import models.Item;
import models.Map;
import views.*;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

	static Map map = new Map();
	static Entity entity = new Entity();
	
	// This represents all of the views that the utilities.IOMediator can see. the utilities.IOMediator acts as a MUX and goes through these
	// to modify the graphics and where the keyPresses go.
	
	// The code works as follows:
	// the single argument that the enum takes is a new view.
	// The view's render function is mapped to the enum's render such that START_MENU.render(g) -> views.StartMenuView->render(g)
	
    public static enum Views {
    	
        START_MENU(new StartMenuView()) {void render(Graphics g) {getView().render(g);}},
        CREATE_GAME(new CreateNewGameView()) {void render(Graphics g) {getView().render(g);}},
        AVATAR_CREATION(new AvatarCreationView()) {void render(Graphics g) {getView().render(g);}},
        PAUSE(new PauseView()) {void render(Graphics g) {getView().render(g);}},
        
        UNIMPLEMENTED(null) {void render(Graphics g) {getView().render(g);}},
        
        SAVE(null) {void render(Graphics g) {getView().render(g);}},
        LOAD(null) {void render(Graphics g) {getView().render(g);}},
        EXIT(null) {void render(Graphics g) {getView().render(g);}},
        
        // TODO: REMOVE HACKY SHIT
        GAME(new GameView(map, entity)) {void render(Graphics g) {getView().render(g);}},
        INVENTORY(new InventoryView(entity.getInventory())) {void render(Graphics g) {getView().render(g);}};
        
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
    // Default view is views.StartMenuView
    private IOMediator() {
        activeView = Views.START_MENU;


        // Put the entity (avatar) at its starting lcoation
        map.insertEntityAtLocation(entity.getLocation()[0], entity.getLocation()[1], entity);
        
    }

    // Static 'instance' method
    public static IOMediator getInstance() {
        return ioMediator;
    }


    // Other methods protected by singleton-ness
    public static void setActiveView(Views view) {
        activeView = view;
    }
    public static View getActiveView() {
        return activeView.getView();
    }

    public static void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.getView().getViewController().handleKeyPress(key);
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
