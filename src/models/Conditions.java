package models;

import models.items.TakeableItem;

/**
 * Created by denzel on 2/5/16.
 */
public class Conditions {


    //Boolean Enums
    public enum booleanEnum{
        AT_LEAST(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count >= entity.getInventory().countItem(item)){
                return true;
            }else{
                return false;
            }
        }},
        EXACTLY(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count == entity.getInventory().countItem(item)){
                return true;
            }else{
                return false;
            }

        }},
        AT_MOST(){protected boolean compare(int count, Entity entity, TakeableItem item){
            if(count <= entity.getInventory().countItem(item)){
                return true;
            }else{
                return false;
            }

        }};

        //Function to compare
        protected abstract boolean compare(int count, Entity entity, TakeableItem item);

        public int getID() {
            return ordinal();
        }
    }

    //Entity Enums
    public enum EntityEnum{
        STRENGTH(){protected int checkStat(Entity entity){
            return entity.getStats().getStrength();
        }},
        AGILITY(){protected int checkStat(Entity entity){
            return entity.getStats().getAgility();
        }},
        INTELLECT(){protected int checkStat(Entity entity){
            return entity.getStats().getIntellect();
        }};

        //Function to checkStat
        protected abstract int checkStat(Entity entity);

        public int getID() {
            return ordinal();
        }
    }

    //Item Enums

    public enum ItemEnum{
        ARMOR(){protected void checkComponent(){
            //TODO: Need to get the components in for takeable
        }},
        WEAPON(){protected void checkComponent(){
            //TODO: Need to get the components in for takeable
        }};

        //Function to check components
        protected abstract void checkComponent();

        public int getID() {
            return ordinal();
        }
    }

    //Properties of the condition object
    private booleanEnum bool;
    private EntityEnum entityEnum;
    private ItemEnum itemEnum;
    private Entity entity;
    private int count;
    private TakeableItem item;

    //Default constructor for the condition object
    public Conditions(){}

    //modify the conditions required for the Item to satisfy
    public void addItemConditions(Entity entity, int count, booleanEnum bool,EntityEnum entityEnum, ItemEnum itemEnum,TakeableItem item){
        this.bool = bool;
        this.entityEnum = entityEnum;
        this.itemEnum = itemEnum;
        this.count = count;
        this.item = item;
    };

    //modify the conditions required for the Entity to satisfy
    public void addEntityConditions(Entity entity, booleanEnum bool, EntityEnum entityEnum, ItemEnum item){

    };

    //be able to check the condition's attributes
    public boolean checkComparision(){
        //If conditions hold through
        if(booleanEnum.AT_LEAST.compare(this.count,this.entity,this.item)){

        }
        return true;

        //Else return false
    }

}
