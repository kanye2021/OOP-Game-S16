package views;

import controllers.ViewController;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/2/16.
 */

// Make an enum for these sub views

public class GameView extends View {
    private static AreaViewport areaViewport;
    // Two view controllers, because we want to switch off what handles input
    // depending on If Equipped items is showing.

    private static ViewController gameViewController;
    private static ViewController equippedItemsViewController;
    private static ViewController inventoryViewController;
    // Adding Equipped Items view as part of game View to Overlay the view
    //private static EquippedItemsView equippedItemsView;
    private static boolean showEquippedItems = false;
    private static boolean showInventory = false;

    private static GameView gameView = new GameView();
    private GameView() {}


    public static void init(Map map, Entity avatar){
        areaViewport.init(map,avatar);
        //gameViewController = new GameViewController(mediator);
//        viewController = gameViewController;
        //statusViewport = new StatusViewport(avatar);


        EquippedItemsView.init();
        //equippedItemsViewController = equippedItemsView.viewController;
        //inventoryView = new InventoryView(map, avatar);
        //inventoryViewController = inventoryView.getViewController();


        // Set the Load_Save Singleton to the current map and avatar
        Load_Save.getInstance().setAvatar(avatar);
        Load_Save.getInstance().setGameMap(map);
    }


    public static void render(Graphics g) {
     System.out.println("ha");
        areaViewport.render(g);
        //statusViewport.render(g);

        /*if (showEquippedItems) {
            viewController = equippedItemsViewController;
            EquippedItemsView.render(g);
        } else if (showInventory) {
            viewController = inventoryViewController;
            inventoryView.render(g);
        } else {
            viewController = gameViewController;
        }*/
    }

    public static boolean getShowEquppiedItems() {
        return showEquippedItems;
    }

    public static void setShowEquippedItems(boolean b) {
        showEquippedItems = b;
    }

    public static void setShowInventory(boolean b) {
        showInventory = b;
    }

    public static boolean getShowInventory() {
        return showInventory;
    }

    public static GameViewController getController() {

        return GameViewController.getInstance();

    }

    private static class GameViewController extends ViewController {

        private static GameViewController controller = new GameViewController();
        private static NavigationMediator navMediator = IOMediator.entity.getNavigationMediator();

        public static GameViewController getInstance() {

            return controller;

        }

        @Override
        public void handleKeyPress(int key) {
            switch (key) {
                case KeyEvent.VK_RIGHT:
                    navMediator.requestMovement("E");
                    break;
                case KeyEvent.VK_LEFT:
                    navMediator.requestMovement("W");
                    break;
                case KeyEvent.VK_UP:
                    navMediator.requestMovement("N");
                    break;
                case KeyEvent.VK_DOWN:
                    navMediator.requestMovement("S");
                    break;
                case KeyEvent.VK_Q:
                    navMediator.requestMovement("NW");
                    break;
                case KeyEvent.VK_E:
                    navMediator.requestMovement("NE");
                    break;
                case KeyEvent.VK_Z:
                    navMediator.requestMovement("SW");
                    break;
                case KeyEvent.VK_C:
                    navMediator.requestMovement("SE");
                    break;
                case KeyEvent.VK_R:
                    // Tell GameView to render EquippedItemsView over it
//                ((GameView) IOMediator.Views.GAME.getView()).setShowEquippedItems(true);
                    break;
                case KeyEvent.VK_I:
//                ((GameView) IOMediator.Views.GAME.getView()).setShowInventory(true);
                    break;
                case KeyEvent.VK_ESCAPE:
//                IOMediator.setActiveView(IOMediator.Views.PAUSE);
                    break;
//            case KeyEvent.VK_S:
//                navMediator.save();
//                break;

                case KeyEvent.VK_NUMPAD1:
                    navMediator.requestMovement("SW");
                    break;
                case KeyEvent.VK_NUMPAD2:
                    navMediator.requestMovement("S");
                    break;
                case KeyEvent.VK_NUMPAD3:
                    navMediator.requestMovement("SE");
                    break;
                case KeyEvent.VK_NUMPAD6:
                    navMediator.requestMovement("E");
                    break;
                case KeyEvent.VK_NUMPAD9:
                    navMediator.requestMovement("NE");
                    break;
                case KeyEvent.VK_NUMPAD8:
                    navMediator.requestMovement("N");
                    break;
                case KeyEvent.VK_NUMPAD7:
                    navMediator.requestMovement("NW");
                    break;
                case KeyEvent.VK_NUMPAD4:
                    navMediator.requestMovement("W");
                    break;

            }
        }

        @Override
        public void handleKeyRelease(int key) {

        }

    }

}
