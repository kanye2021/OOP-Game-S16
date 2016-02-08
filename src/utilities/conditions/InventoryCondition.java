package utilities.conditions;

import models.Entity;
import models.Inventory;
import models.items.TakeableItem;

/**
 * Created by denzel on 2/6/16.
 */
public class InventoryCondition extends Condition {

    //Properties of InventoryCondition
    private Condition.Entities entity;
    private Condition.Comparison itemComparison;
    private int count;
    private TakeableItem.Items item;

    public InventoryCondition(Condition.Entities entity, Condition.Comparison itemComparison, int count, TakeableItem.Items item){
        this.entity = entity;
        this.itemComparison = itemComparison;
        this.count = count;
        this.item = item;
    }


    @Override
    protected boolean checkCondition() {
        int requiredCount = this.count;
        int inventoryCount = entity.getEntity().getInventory().getItemCount(item);

        return (itemComparison.isValid(inventoryCount, requiredCount));
    }

}
