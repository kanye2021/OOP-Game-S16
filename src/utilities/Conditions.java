package utilities;

import java.util.ArrayList;

/**
 * Created by denzel on 2/6/16.
 */
public class Conditions extends ArrayList<Condition>{

    //Constructor that puts all the conditions for each item
    public Conditions(Condition...conditionsList){
        for(Condition conditions : conditionsList){
            add(conditions);
        }
    }

    //Overrides ArrayList method to add condition to the Conditions object
    @Override
    public boolean add(Condition condition) {
        return super.add(condition);
    }
}


//package models;
//
//import models.items.TakeableItem;
//
///**
// * Created by denzel on 2/5/16.
// */
//public class Conditions {
//
//
//    //Boolean Enums
//    public enum ItemComparison{
//        AT_LEAST(){protected boolean compare(int count, Entity entity, TakeableItem.Items item){
//            if(count >= entity.getInventory().getItemCount(item)){
//                return true;
//            }else{
//                return false;
//            }
//        }},
//        EXACTLY(){protected boolean compare(int count, Entity entity, TakeableItem.Items item){
//            if(count == entity.getInventory().getItemCount(item)){
//                return true;
//            }else{
//                return false;
//            }
//
//        }},
//        AT_MOST(){protected boolean compare(int count, Entity entity, TakeableItem.Items item){
//            if(count <= entity.getInventory().getItemCount(item)){
//                return true;
//            }else{
//                return false;
//            }
//
//        }};
//
//        //Function to compare
//        protected abstract boolean compare(int count, Entity entity, TakeableItem.Items item);
//
//        public int getID() {
//            return ordinal();
//        }
//    }
//
//    public enum EntityLocation {
//        INVENTORY,
//        EQUIPPED;
//    }
//
//    //Properties of the condition object
//    private ItemComparison comparison;
//    private Entity entity;
//    private int count;
//    private TakeableItem item;
//
//
//    //Conditions conditions;
//    //conditions.addItemCondition(entity, Conditions.ItemComparison.AT_LEAST, 2, TakeableItems.Items.IRON_SWORD, Conditions.Location.INVENTORY)
//    //conditions.addItemCondition(entity, Conditions.COMPARISON.AT_LEAST, 2, TakeableItems.Items.IRON_SWORD, Conditions.Location.EQUIPPED)
//    //conditions.addItemCondition(entity, Conditions.COMPARISON.AT_LEAST, 2, TakeableItems.Items.IRON_SWORD, Conditions.Location.INVENTORY)
//    //conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 50, Conditions.POINTS)
//    //conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 0.5, Conditions.PERCENTAGE)
//
//    //Default constructor for the condition object
//    public Conditions(){}
//
//    //modify the conditions required for the Item to satisfy
//    public void addItemConditions(Entity entity, ItemComparison comparison, int count, TakeableItem.Items item, EntityLocation location){
//        this.bool = bool;
//        this.entityEnum = entityEnum;
//        this.itemEnum = itemEnum;
//        this.count = count;
//        this.item = item;
//    };
//
//    //modify the conditions required for the Entity to satisfy
//    public void addEntityConditions(Entity entity, booleanEnum bool, EntityEnum entityEnum, ItemEnum item){
//
//    };
//
//    //be able to check the condition's attributes
//    public boolean checkComparision(){
//        //If conditions hold through
//        if(booleanEnum.AT_LEAST.compare(this.count,this.entity,this.item)){
//
//        }
//        return true;
//
//        //Else return false
//    }
//
//}
