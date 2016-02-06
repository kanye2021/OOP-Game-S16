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
    public void useFromInv(TakeableItem usedItem){

        //Apply and remove item from inventory
        usedItem.modifyStatsReverse(avatar);
        avatarInventory.removeItem(usedItem);

        // /if it is equippable (otherwise it would just be used)
        if(TakeableItem.Items.values()[usedItem.getID()].getIsEquippable()) {

            //If you already have the type of item equipped, unequip current item, add unequippedItem to Inv and equip new Item)
            if(avatarEquippedItems.checkItem(usedItem) != null) {
                TakeableItem unequippedItem = avatarEquippedItems.checkItem(usedItem);
                unequipItemStats(unequippedItem);
                avatarEquippedItems.equipItems(usedItem);
                avatarInventory.addItem(unequippedItem);
            }

            //If type of item is not equipped equip it
            else{
                avatarEquippedItems.equipItems(usedItem);
            }
        }

    }

    //Unequip item and undo the stats it provided
    public void unequipItemStats(TakeableItem unequippedItem){
        //Undo stats from to be unequipped item and update
        unequippedItem.modifyStats(avatar);

        //Unequip and add to inventory
        avatarEquippedItems.unequipItems(unequippedItem);
        avatarInventory.addItem(unequippedItem);
    }
}
