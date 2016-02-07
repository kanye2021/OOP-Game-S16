package models;

import models.EquippedItems;
import models.Stats;
import models.items.TakeableItem;
/**
 * Created by david on 2/4/16.
 */
public class ItemStatsAssociation {
    //Class Variables
    Entity avatar;
    EquippedItems avatarEquippedItems;
    Inventory avatarInventory;
    Stats avatarStats;

    //Constructor
     public ItemStatsAssociation(Entity avatar){
        this.avatar = avatar;
        avatarEquippedItems = avatar.getEquippedItems();
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }



    //That applies the modifiers of items whether one time or equippable;
    public void useFromInventory(TakeableItem usedItem){

    	EquippedItems.ArmorComponent componentType = usedItem.getComponent();
    	
        //Apply and remove item from inventory
        usedItem.modifyStats(avatar);
        avatarInventory.removeItem(usedItem);

        // /if it is equippable (otherwise it would just be used)
        
        if (usedItem.getEquippable()) {
        	
        	TakeableItem oldItem = componentType.getCurrentEquippedItem(avatar);
        	
        	if (oldItem != null) {
        		
        		oldItem.modifyStatsReverse(avatar);
        		componentType.unequipComponent(avatar);
        		
        	}
        	
        	componentType.equipComponent(avatar, usedItem);
        	
        }

    }

}