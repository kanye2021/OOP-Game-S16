package utilities;

/**
 * Created by denzel on 2/6/16.
 */

import models.Entity;
import models.items.InteractiveItem;
import models.items.TakeableItem;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */
public class Condition {

    //Boolean Enums
    public enum Comparison {
//        AT_LEAST() {
//            protected boolean compare(int count, Entity entity, TakeableItem.Items item) {
//                if (count >= entity.getInventory().getItemCount(item)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        },
//        EXACTLY() {
//            protected boolean compare(int count, Entity entity, TakeableItem.Items item) {
//                if (count == entity.getInventory().getItemCount(item)) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//            }
//        },
        AT_MOST() {
            protected boolean compare(int count, Entity entity, TakeableItem.Items item) {
                if (count <= entity.getInventory().getItemCount(item)) {
                    return true;
                } else {
                    return false;
                }

            }
        };

        //Constructor

        //Function to compare
        protected abstract boolean compare(int count, Entity entity, TakeableItem.Items item);

        public int getID() {
            return ordinal();
        }
    }

    public enum ItemConditions{
        INVENTORY,
        EQUIPPED;
    }

    //TODO: Get the Map Conditions


    //Properties of the Condition object
    private Entity entity;
    private Comparison comparison;

    //TODO: Need to abstract this into ItemConditions & MapConditions
    private int count;
    private TakeableItem.Items item;
    private ItemConditions conditions;


    //Constructor that passes in reference to the entity calling it
    public Condition(Entity entity){this.entity = entity;}

    //Adding conditions
    //condition.addConditions(AT_LEAST,2,Takeable.Items.WOOD_SWORD,ItemConditions.INVENTORY)
    public void addConditions(Comparison comparison, int count, TakeableItem.Items item, ItemConditions itemConditions){
        this.comparison = comparison;
        this.count = count;
        this.item = item;
        this.conditions = itemConditions;
    }

    //Check the conditions
    public boolean checkConditions(Condition condition){
        if(this.comparison.compare(1,this.entity,this.item)){
            return true;
        }else{
            return false;
        }
    }

}

