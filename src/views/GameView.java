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
    private AreaViewport areaViewport;
    private StatusViewport statusViewport;
    // Two view controllers, because we want to switch off what handles input
    // depending on If Equipped items is showing.

    private ViewController gameViewController;
    private ViewController equippedItemsViewController;
    private ViewController inventoryViewController;
    // Adding Equipped Items view as part of game View to Overlay the view
    private EquippedItemsView equippedItemsView;
    private InventoryView inventoryView;
    private boolean showEquippedItems;
    private boolean showInventory;

    public GameView(Map map, Entity avatar) {
        NavigationMediator mediator = new NavigationMediator(map, avatar);
        areaViewport = new AreaViewport(map, avatar);
        gameViewController = new GameViewController(this, mediator);
        viewController = gameViewController;
        statusViewport = new StatusViewport(avatar);

        equippedItemsView = new EquippedItemsView();
        equippedItemsViewController = equippedItemsView.viewController;
        inventoryView = new InventoryView(map, avatar);
        inventoryViewController = inventoryView.getViewController();
        showEquippedItems = false;
        showInventory = false;


        // Set the Load_Save Singleton to the current map and avatar
        Load_Save.getInstance().setAvatar(avatar);
        Load_Save.getInstance().setGameMap(map);

    }

    @Override
    public void render(Graphics g) {
        areaViewport.render(g);
        statusViewport.render(g);

        if (showEquippedItems) {
            viewController = equippedItemsViewController;
            equippedItemsView.render(g);
        } else if (showInventory) {
            viewController = inventoryViewController;
            inventoryView.render(g);
        } else {
            viewController = gameViewController;
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
