package models;

import models.items.TakeableItem;

/**
 * Created by david
 * on 2/4/16.
 */
public class ItemStatsAssociation {
    //Class Variables
    Entity avatar;
    EquippedItems avatarEquippedItems;
    Inventory avatarInventory;
    Stats avatarStats;

    //Constructor
    public ItemStatsAssociation(Entity avatar) {
        this.avatar = avatar;
        avatarEquippedItems = avatar.getEquippedItems();
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }


    //That applies the modifiers of items whether one time or equippable;
    public void useFromInventory(TakeableItem usedItem) {


        //Apply and remove item from inventory

        // /if it is equippable (otherwise it would just be used)

        if (usedItem.getItemType() == TakeableItem.ItemType.EQUIPPABLE) {
            EquippedItems.ArmorComponent componentType = usedItem.getComponent();
            usedItem.modifyStats(avatar);
            avatarInventory.removeItem(usedItem);

            TakeableItem oldItem = componentType.getCurrentEquippedItem(avatar);

            if (oldItem != null) {

                oldItem.modifyStatsReverse(avatar);
                componentType.unequipComponent(avatar);
                avatarInventory.addItem(oldItem);

            }

            componentType.equipComponent(avatar, usedItem);

        }
        // Item is use-able/consumable
        else if (usedItem.getItemType() == TakeableItem.ItemType.CONSUMABLE){

            usedItem.modifyStats(avatar);
            avatarInventory.removeItem(usedItem);

        } else {
            //TODO: consume the item or s/t
        }

    }

}