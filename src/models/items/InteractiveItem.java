package models.items;


import models.Entity;
import models.Stats;
import utilities.conditions.Condition;
import utilities.conditions.Conditions;
import utilities.conditions.InventoryCondition;
import utilities.conditions.StatCondition;


/**
 * Created by denzel on 2/5/16.
 */
public class InteractiveItem extends Item {

    //Needs an enum
    public enum Quests {

        SAVE_JORGE("boomerang.png", "Kill the Jorge", new Conditions(
                new InventoryCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_LEAST, 1, TakeableItem.Items.WOOD_SWORD),
                new StatCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_LEAST, 5, Stats.Type.LEVEL)
        )),
        DAVID_SQUARED("umbrella.png", "David Squared Has a Mission for you", new Conditions(
                new InventoryCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_MOST, 2, TakeableItem.Items.WOOD_SWORD),
                new StatCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_LEAST, 5, Stats.Type.LEVEL)
        )),
        CHEN("roller.png", "His name is Chen. Chen Ben.", new Conditions(
                new InventoryCondition(Condition.Entities.AVATAR, Condition.Comparison.EXACTLY, 1, TakeableItem.Items.WOOD_SWORD),
                new StatCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_LEAST, 5, Stats.Type.LEVEL)
        )),
        GATE_OF_KANYE("KanyeGate.png", "The Legendary Gate of Kanye", new Conditions(
                new InventoryCondition(Condition.Entities.AVATAR, Condition.Comparison.EXACTLY, 1, TakeableItem.Items.KEY_OF_KANYE),
                new StatCondition(Condition.Entities.AVATAR, Condition.Comparison.AT_LEAST, 25, Stats.Type.LEVEL)
        ));

        private String pathToPicture;
        private String description;
        private Conditions conditions;

        //Enum constructor
        Quests(String pathToPicture, String description, Conditions conditions) {
            this.pathToPicture = pathToPicture;
            this.description = description;
            this.conditions = conditions;
        }

        public int getID() {
            return ordinal();
        }

        public String getPathToPicture() {
            return pathToPicture;
        }

    }

    //Class Constructor
    public InteractiveItem(Quests quests) {
        super(quests.ordinal(), Type.INTERACTIVE);
    }

    @Override
    public String getPathToPicture() {

        return Quests.values()[getID()].getPathToPicture();
    }

    //Getters
    public String getDescription() {
        return Quests.values()[getID()].description;
    }

    public boolean checkCondition() {
        return Quests.values()[getID()].conditions.checkCondition();
    }

    @Override
    public boolean onTouch(Entity entity) {

        boolean condition = checkCondition();

        if (condition) {
            System.out.println("You did the thing!");
            return true;
        } else {
            System.out.println(this.getDescription());
            System.out.println("You had one job...");
        }
        return false;
    }

}