package controllers;

import models.Inventory;
import utilities.IOMediator;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryViewController extends ViewController {

	private int position = 0;

    public InventoryViewController(View view) {
        super(view);
    }

    public int getActiveItem() {
    	
    	return position;
    	
    }
    
    public Inventory getInventory() {
    	
    	return IOMediator.entity.getInventory();
    	
    }
    
    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM IVC");
            previousItem();
        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM IVC");
            nextItem();
        }

        else if (key == KeyEvent.VK_ESCAPE) {
        	IOMediator.setActiveView(IOMediator.Views.GAME);
        }
        
    }
    
    @Override
    public void handleKeyRelease(int key) {

    }
    
    private void previousItem() {
    	
    	position--;
    	
    	if (position < 0) {
    		
    		position = getInventory().getItems().size() - 1;
    		
    	}
    	
    }
    
    private void nextItem() {
    	
    	position++;
    	
    	if (position > getInventory().getItems().size() - 1) {
    		
    		
    		position = 0;
    	
    	}
    	
    }
    
}
