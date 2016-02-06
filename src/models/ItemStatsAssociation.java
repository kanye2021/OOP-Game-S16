package models;

/**
 * Created by david on 2/4/16.
 */
public class ItemStatsAssociation {
    //Class Variables
    EquippedItems avatarEquippedItems;
    Inventory avatarInventory;
    Stats avatarStats;

    //Constructor
    ItemStatsAssociation(Entity avatar){
        avatarEquippedItems = avatar.getEquippedItems();
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }



    //That applies the modifiers of items whether one time or equippable;
    public void useFromInv(TakeableItem usedItem){

        //Apply and remove item from inventory
        avatarStats.modifyArmorRating(usedItem.getArmorRating());
        avatarStats.modifyOffensiveRating(usedItem.getOffensiveRating());
        avatarInventory.removeItem(usedItem);

        // /if it is equippable (otherwise it would just be used)
        if( TakeableItem.Items.values()[usedItem.getID()].getIsEquippable() ) {

            //If you already have the type of item equipped, unequip current item, add unequippedItem to Inv and equip new Item)
            if(avatarEquippedItems.checkItem(usedItem) != NULL) {
                unequippedItem = avatarEquippedItems.checkItem(usedItem);
                unequipItemStats(unequippedItem);
                avatarEquippedItems.equipItem(usedItem);
            }

            //If type of item is not equipped equip it
            else{
                avatarEquippedItems.equipItem(usedItem);
            }
        }

    }

    //Unequip item and undo the stats it provided
    public void unequipItemStats(Item unequippedItem){

        //Undo stat changes from previously equipped item
        avatarStats.modifyArmorRating(-(unequippedItem.getArmorRating()));
        avatarStats.modifyOffensiveRating(-(unequippedItem.getOffensiveRating()));

        //Unequip and add to inventory
        avatarEquippedItems.unequipItem(unequippedItem);
        avatarInventory.addItem(unequippedItem);
    }
}