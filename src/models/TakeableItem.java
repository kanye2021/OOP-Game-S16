/**
 * Created by denzel on 2/3/16.
 */
package models;

import models.Entity;
import models.Item;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {

    //Constructor
    public TakeableItem(String name, String type, String description, int id) {
        super(name, type, description, id);
    }

    /**
     *Returns true if it should be removed from the map
     */
    @Override
    public boolean onTouch(Entity entity) {
        entity.getInventory().addItem(this);
        return true;
    }
}
