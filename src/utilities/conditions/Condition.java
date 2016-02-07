package utilities.conditions;

/**
 * Created by denzel on 2/6/16.
 */

import models.Entity;
import models.Inventory;
import models.items.TakeableItem;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */

//TODO: Maybe consider refactoring this to be only Avatar conditions
public class Condition {

    //Item conditions
    public enum ItemComparison {
        AT_LEAST() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count <= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        EXACTLY() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count == bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }

            }
        },
        AT_MOST() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count >= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }

            }
        };

        //Function to compare
        protected abstract boolean compare(int count, Entity entity,ItemConditions bag, TakeableItem.Items item);

        //Returns the ordinal for the Enum
        public int getID() {
            return ordinal();
        }
    }

    public enum EntityComparison{
        AT_LEAST() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count <= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        EXACTLY() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count == bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        },
        AT_MOST() {
            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
                if (count >= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public enum ItemConditions{
        INVENTORY(){
            @Override
            protected Inventory getBackpack(Entity entity) {
                return entity.getInventory();
                }
            };

        //TODO Get Equipped Items after Austin refactors it

        //Function to grab the "backpack" of the Entity
        protected abstract Inventory getBackpack(Entity entity);
    }

    public enum StatConditions{
        STRENGTH(){},
        AGILITY(){},
        INTELLECT(){};

        //Function to grab the stats of the Entity
    }

    //TODO: Get the Entity Conditions


    //Properties of the Condition object
    private Entity entity;
    private ItemComparison comparison;

    //TODO: Need to abstract this into ItemConditions & MapConditions
    private int count;
    private TakeableItem.Items item;
    private ItemConditions bag;


    //Constructor that passes in reference to the entity calling it
    public Condition(Entity entity){this.entity = entity;}

    //Adding conditions
    //condition.addConditions(AT_LEAST,2,Takeable.Items.WOOD_SWORD,ItemConditions.INVENTORY)
    public void addConditions(ItemComparison comparison, int count, TakeableItem.Items item, ItemConditions itemConditions){
        this.comparison = comparison;
        this.count = count;
        this.item = item;
        this.bag = itemConditions;
    }

    //Check the conditions
    public boolean checkConditions(Condition condition){
        if(this.comparison.compare(this.count,this.entity,this.bag,this.item)){
            return true;
        }else{
            return false;
        }
    }

}

