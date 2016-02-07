package models.items;

import models.Stats;
import models.Entity;
import utilities.conditions.Condition;
import utilities.conditions.Conditions;
import utilities.conditions.InventoryCondition;
import utilities.conditions.StatCondition;

/**
 * Created by denzel on 2/5/16.
 */
public class InteractiveItem extends Item {

    //Needs an enum

    public enum Quests{

        SAVE_JORGE(new Conditions(new Condition() {
        })"takeable-item.png"),
        DAVID_SQUARED("teddy_bear.png"),
        BRAGIO("teddy_bear.png");


        private Conditions conditions;
        private String pathToPicture;

        //Enum constructor
        Quests(Conditions conditions, String pathToPicture) {
            this.conditions = conditions
            this.pathToPicture = pathToPicture;
        }

        public int getID() {
            return ordinal();
        }

        public String getPathToPicture() {
            return pathToPicture;
        }

    }

    //Class Constructor
    public InteractiveItem(Quests quests){
        super(quests.ordinal(), Type.INTERACTIVE);
    }

    @Override
    public String getPathToPicture() {
        return null;
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public boolean onTouch(Entity entity) {
        //ArrayList of condition to add onto
        Conditions conditions = new Conditions(
                new InventoryCondition(entity, InventoryCondition.ItemComparison.AT_LEAST,1, TakeableItem.Items.IRON_SWORD),
                new StatCondition(entity, StatCondition.StatsComparison.AT_LEAST,20, Stats.Type.STRENGTH)
                //new InventoryCondition(entity, InventoryCondition.ItemComparison.AT_LEAST,1, TakeableItem.Items.WOOD_SWORD)
        );

        if(conditions.checkCondition()){
            System.out.println("You did the thing!");
        }
        return false;

    }


}