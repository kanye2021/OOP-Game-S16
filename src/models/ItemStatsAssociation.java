package models;

/**
 * Created by david on 2/4/16.
 */
public class ItemStatsAssociation {
    Inventory avatarInventory;
    Stats avatarStats;

    ItemStatsAssociation(Entity avatar){
        avatarInventory = avatar.getInventory();
        avatarStats = avatar.getStats();
    }

    public void updateStats(Item usedItem){
        usedItem.statModifier();
    }
}
