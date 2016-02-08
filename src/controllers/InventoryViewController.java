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
 * Created by Austin on 2/2/16.
 */
public class InventoryViewController extends ViewController {

	private int position = 0;
    public Map map;
    public Entity entity;
    public Inventory inventory;
    //public ItemStatsAssociation avatarItemStats;


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

    public ItemStatsAssociation getAvatarItemStats(){
        return entity.getAvatarItemStats();
    }

    
    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT) {
            System.out.println("Up pressed FROM IVC");
            previousItem();
        }

        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_RIGHT) {
            System.out.println("Down pressed FROM IVC");
            nextItem();
        }

        else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_I ) {
            ((GameView)IOMediator.Views.GAME.getView()).setShowInventory(false);
        }

        else if (key == KeyEvent.VK_R) {
            ((GameView)IOMediator.Views.GAME.getView()).setShowInventory(false);
            ((GameView)IOMediator.Views.GAME.getView()).setShowEquippedItems(true);
        }


        else if(key == KeyEvent.VK_ENTER) {
            System.out.println("Enter pressed from IVC");
            if(inventory.isThereAnItemAt(getPosition())) {
                useItem(inventory.getItemAt(getPosition()));
            }
        }

        else if(key == KeyEvent.VK_D){
            if(getInventory().isThereAnItemAt(getPosition())){
                dropItem(inventory.getItemAt(getPosition()));
            }
        }
        
    }
    
    @Override
    public void handleKeyRelease(int key) {

    }

    private void dropItem(TakeableItem item){
        if(inventory.removeItem(item)){
            map.insertItemAtLocation(entity.getLocation()[0], entity.getLocation()[1], item);
        }
    }
    
    private void previousItem() {
    	
    	position--;
    	
    	if (position < 0) {
    		
    		position = getInventory().getSize() - 1;
    		
    	}
    	
    }
    
    private void nextItem() {
    	
    	position++;
    	
    	if (position > getInventory().getSize() - 1) {
    		
    		
    		position = 0;
    	
    	}
    	
    }

    // Uses/Equips item in inventory
    private void useItem(TakeableItem item) {
        
    	getAvatarItemStats().useFromInventory(item);
    
    }
   
    public int getPosition(){return position;}


}


