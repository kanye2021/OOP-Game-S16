package models;

import models.items.TakeableItem;

/**
 * Created by denzel on 2/5/16.
 */
public class Conditions {


    //Boolean Enums
    public enum Boolean{
        AT_LEAST(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count >= entity.getInventory().getItemNode(item).amount){
                return true;
            }else{
                return false;
            }
        }},
        AT_MOST(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count == entity.getInventory().getItemNode(item).amount){
                return true;
            }else{
                return false;
            }

        }},
        EXACTLY(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count <= entity.getInventory().getItemNode(item).amount){
                return true;
            }else{
                return false;
            }

        }};

        //Function to compare
        protected abstract boolean compare(int count, Entity entity, TakeableItem item);
    }

    //Entity Enums
    public enum EntityEnum{
        STRENGTH(){protected void checkStat(){

        }},
        AGILITY(){protected void checkStat(){

        }},
        INTELLECT(){protected void checkStat(){

        }};

        //Function to checkStat
        protected abstract void checkStat();
    }

    //Item Enums
    public enum ItemEnum{
        ARMOR(){protected void checkComponent(){

        }},
        WEAPON(){protected void checkComponent(){

        }};

        //Function to check components
        protected abstract void checkComponent();
    }

    //Properties of the condition object
    private Boolean bool;
    private EntityEnum entityEnum;
    private ItemEnum itemEnum;

    //Default constructor for the condition object
    public Conditions(){}

    //modify the conditions required for the Item to satisfy
    private void addItemConditions(Boolean bool,EntityEnum entityEnum, ItemEnum item){

    };

    //modify the conditions required for the Entity to satisfy
    private void addEntityConditions(Boolean bool, EntityEnum entityEnum, ItemEnum item){

    };

    //be able to check the condition's attributes
    private boolean checkComparision(Boolean bool, EntityEnum entityEnum, ItemEnum item){
        //If conditions hold through
        return true;

        //Else return false
    }

}
