package controllers;


import models.Entity;
import models.Inventory;
import models.ItemStatsAssociation;
import models.Map;
import models.items.TakeableItem;
import utilities.IOMediator;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by aseber
 * on 2/2/16.
 */
public class InventoryViewController extends ViewController {

    public static final int ITEMS_PER_ROW = 10;
    private int position = 0;
    public Map map;
    public Entity entity;
    public Inventory inventory;

    public InventoryViewController(View view, Map map, Entity entity) {
        super(view);
        this.map = map;
        this.entity = entity;
        this.inventory = entity.getInventory();
    }

    public int getActiveItem() {
        return position;
    }

    public Inventory getInventory() {

        return inventory;

    }

    public ItemStatsAssociation getAvatarItemStats() {
        return entity.getAvatarItemStats();
    }


    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_LEFT) {
            moveSelectedLeft();
        } else if (key == KeyEvent.VK_RIGHT) {
            moveSelectedRight();
        } else if (key == KeyEvent.VK_UP) {
            moveSelectedUp();
        } else if (key == KeyEvent.VK_DOWN) {
            moveSelectedDown();
        } else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_I) {
            ((GameView) IOMediator.Views.GAME.getView()).setShowInventory(false);
        } else if (key == KeyEvent.VK_R) {
            ((GameView) IOMediator.Views.GAME.getView()).setShowInventory(false);
            ((GameView) IOMediator.Views.GAME.getView()).setShowEquippedItems(true);
        } else if (key == KeyEvent.VK_ENTER) {
            if (inventory.isThereAnItemAt(getPosition())) {
                useItem(inventory.getItemAt(getPosition()));
            }
        } else if (key == KeyEvent.VK_D) {
            if (getInventory().isThereAnItemAt(getPosition())) {
                dropItem(inventory.getItemAt(getPosition()));
            }
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }

    private void dropItem(TakeableItem item) {

        if (inventory.removeItem(item)) {

            map.insertItemAtLocation(entity.getLocation().y, entity.getLocation().x, item);

        }

    }

    private void moveSelectedLeft() {

        position--;

        if (position % ITEMS_PER_ROW == ITEMS_PER_ROW - 1 || position == -1) {

            position = position + ITEMS_PER_ROW;

        }

    }

    private void moveSelectedRight() {

        position++;

        if (position % ITEMS_PER_ROW == 0) {


            position = position - ITEMS_PER_ROW;

        }

    }

    private void moveSelectedUp() {

        position -= ITEMS_PER_ROW;

        if (position < 0) {

            position = getInventory().getSize() - Math.abs(position);

        }

    }

    private void moveSelectedDown() {

        position += ITEMS_PER_ROW;

        if (position >= getInventory().getSize()) {

            position = position - getInventory().getSize();

        }

    }

    // Uses/Equips item in inventory
    private void useItem(TakeableItem item) {

        getAvatarItemStats().useFromInventory(item);

    }

    public int getPosition() {

        return position;

    }


}