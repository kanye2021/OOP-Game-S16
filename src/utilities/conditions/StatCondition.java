//package utilities.conditions;
//
//import models.Entity;
//import models.items.TakeableItem;
//
///**
// * Created by denzel on 2/6/16.
// */
//public class StatCondition {
//    public enum EntityComparison{
//        AT_LEAST() {
//            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
//                if (count <= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        },
//        EXACTLY() {
//            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
//                if (count == bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        },
//        AT_MOST() {
//            protected boolean compare(int count, Entity entity, ItemConditions bag, TakeableItem.Items item) {
//                if (count >= bag.INVENTORY.getBackpack(entity).getItemCount(item)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        };
//    }
//    public enum StatConditions{
//        STRENGTH(){},
//        AGILITY(){},
//        INTELLECT(){};
//
//        //Function to grab the stats of the Entity
//    }
//}
