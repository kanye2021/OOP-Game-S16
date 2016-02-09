package utilities.conditions;

import models.items.TakeableItem;

/**
 * Created by aseber
 * on 2/6/16.
 */
public class EquippedCondition extends Condition {

    //Properties of InventoryCondition
    private Condition.Entities entity;
    private TakeableItem.Items item;

    public EquippedCondition(Condition.Entities entity, TakeableItem.Items item) {

        this.entity = entity;
        this.item = item;

    }

    public boolean checkCondition() {

        TakeableItem equippedItem = item.getComponent().getCurrentEquippedItem(entity.getEntity());

        if (equippedItem != null) {

            return equippedItem.getID() == item.getID();

        }

        return false;

    }

}
