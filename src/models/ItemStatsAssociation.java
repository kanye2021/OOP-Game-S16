package models;

import models.EquippedItems;
import models.Stats;
import models.items.TakeableItem;
/**
 * Created by david on 2/4/16.
 */
public class ItemStatsAssociation {
    //Class Variables
    EquippedItems avatarEquippedItems;
    Inventory avatarInventory;
    Stats avatarStats;

    //Constructor
     public ItemStatsAssociation(Entity avatar){
        avatarEquippedItems = avatar.getEquippedItems();
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }



    //That applies the modifiers of items whether one time or equippable;
    public void useFromInv(TakeableItem usedItem){

        //Apply and remove item from inventory
        avatarStats.setWeaponModifier(avatarStats.getWeaponModifier()+TakeableItem.Items.values()[usedItem.getID()].getOffensiveRating());
        avatarStats.setArmorModifier(avatarStats.getArmorModifier()+TakeableItem.Items.values()[usedItem.getID()].getArmorRating());
        avatarStats.updateOffensiveRating();
        avatarStats.updateDefensiveRating();

        boolean canRemove = avatarInventory.removeItem(usedItem);

        // /if it is equippable (otherwise it would just be used)
        if(TakeableItem.Items.values()[usedItem.getID()].getIsEquippable()) {

            //If you already have the type of item equipped, unequip current item, add unequippedItem to Inv and equip new Item)
            if(avatarEquippedItems.checkItem(usedItem) != null) {
                TakeableItem unequippedItem = avatarEquippedItems.checkItem(usedItem);
                unequipItemStats(unequippedItem);
                avatarEquippedItems.equipItems(usedItem);
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
        avatarStats.setWeaponModifier(avatarStats.getWeaponModifier()-TakeableItem.Items.values()[unequippedItem.getID()].getOffensiveRating());
        avatarStats.setArmorModifier(avatarStats.getArmorModifier()-TakeableItem.Items.values()[unequippedItem.getID()].getArmorRating());
        avatarStats.updateOffensiveRating();
        avatarStats.updateDefensiveRating();

        //Unequip and add to inventory
        avatarEquippedItems.unequipItems(unequippedItem);
        boolean canAdd = avatarInventory.addItem(unequippedItem);
    }
}
