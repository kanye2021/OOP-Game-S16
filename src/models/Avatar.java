package models;

import models.items.Item;
import models.items.TakeableItem;
import utilities.conditions.Condition;

/**
 * Created by sergiopuleri on 2/4/16.
 */
public class Avatar extends Entity {

    public Avatar() {
        super(Occupation.SMASHER);
    }

    public Avatar(Entity.Occupation occupation) {
        super(occupation);
    }

    public void dropItem(TakeableItem item) {
        this.inventory.removeItem(item);
        // TODO: Drop item on the map..
        // Need to be able to insert item on map...
        // Singleton??
        // Map.insertItemAtLocation(location[0], location[1], item);

    }

    @Override
    public String getType() {
        return "avatar";
    }

    public void equipItem(Item item) {
        //TODO: equipped items
    }

    public int getID() {

        return 1000;

    }

}