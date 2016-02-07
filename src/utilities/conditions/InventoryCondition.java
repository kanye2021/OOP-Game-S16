package utilities.conditions;

import models.Entity;
import models.Inventory;
import models.items.TakeableItem;

/**
 * Created by denzel on 2/6/16.
 */
public class InventoryCondition extends Condition {

    //The Item Comparison Enum
    public enum ItemComparison {
        AT_LEAST(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount >= requiredCount;
            }
        },
        EXACTLY(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount == requiredCount;
            }
        },
        AT_MOST(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount <= requiredCount;
            }
        };

        //Function to compare
        protected abstract boolean isValid(int inventoryCount, int count);

        //Returns the ordinal for the Enum
        public int getID() {
            return ordinal();
        }
    }

    //Properties of InvenctoryCondition
    private ItemComparison itemComparison;
    private int count;
    private TakeableItem.Items items;

    public InventoryCondition(Entity entity, ItemComparison itemComparison, int count, TakeableItem.Items items){
        this.entity = entity;
        this.itemComparison = itemComparison;
        this.count = count;
        this.items = items;
    }


    @Override
    protected boolean checkCondition() {
        int requiredCount = this.count;
        int inventoryCount = entity.getInventory().getItemCount(items);
        
        return (itemComparison.isValid(inventoryCount, requiredCount));
    }

}
