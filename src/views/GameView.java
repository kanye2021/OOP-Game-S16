package views;

import controllers.GameViewController;
import controllers.ViewController;
import models.Entity;
import models.Map;
import utilities.Load_Save;
import utilities.NavigationMediator;

import java.awt.*;

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
    private static boolean showEquippedItems;
    private static boolean showInventory;

    private static GameView gameView = new GameView();
    private GameView() {}


    public static void init(Map map, Entity avatar){
        NavigationMediator mediator = new NavigationMediator(map, avatar);
        areaViewport.init(map,avatar);
        //gameViewController = new GameViewController(mediator);
//        viewController = gameViewController;
        //statusViewport = new StatusViewport(avatar);


        EquippedItemsView.init();
        //equippedItemsViewController = equippedItemsView.viewController;
        //inventoryView = new InventoryView(map, avatar);
        //inventoryViewController = inventoryView.getViewController();
        showEquippedItems = false;
        showInventory = false;


        // Set the Load_Save Singleton to the current map and avatar
        Load_Save.getInstance().setAvatar(avatar);
        Load_Save.getInstance().setGameMap(map);
    }


    public static void render(Graphics g) {
        areaViewport.render(g);
        //statusViewport.render(g);

        if (showEquippedItems) {
//            viewController = equippedItemsViewController;
            EquippedItemsView.render(g);
        } else if (showInventory) {
//            viewController = inventoryViewController;
           // inventoryView.render(g);
        } else {
//            viewController = gameViewController;
        }
    }

    public boolean getShowEquppiedItems() {
        return showEquippedItems;
    }

    public void setShowEquippedItems(boolean b) {
        showEquippedItems = b;
    }

    public void setShowInventory(boolean b) {
        showInventory = b;
    }

    public boolean getShowInventory() {
        return showInventory;
    }

}
