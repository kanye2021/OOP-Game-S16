package models;

/**
 * Created by david on 2/4/16.
 */
public class InventoryStatsAssociation {
    Inventory avatarInventory;
    Stats avatarStats;

    InventoryStatsAssociation(Entity avatar){
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }

    public void updateStats(Item usedItem){
        usedItem.statModifier();
    }
}
