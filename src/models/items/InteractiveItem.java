package models.items;


import models.Entity;
import models.Inventory;
import models.Stats;
import utilities.conditions.Conditions;
import utilities.conditions.InventoryCondition;
import utilities.conditions.StatCondition;


/**
 * Created by denzel on 2/5/16.
 */
public class InteractiveItem extends Item {

    //Needs an enum
    public enum Quests{

        SAVE_JORGE("boomerang.png","Kill the Jorge",InventoryCondition.ItemComparison.AT_LEAST, 1, TakeableItem.Items.WOOD_SWORD),
        DAVID_SQUARED("umbrella.png","David Squared Has a Mission for you",InventoryCondition.ItemComparison.AT_MOST, 2, TakeableItem.Items.WOOD_SWORD),
        CHEN("roller.png","His name is Chen. Chen Ben.",InventoryCondition.ItemComparison.EXACTLY, 1, TakeableItem.Items.WOOD_SWORD),
        GATE_OF_KANYE("KanyeGate.png","The Legendary Gate of Kanye", InventoryCondition.ItemComparison.EXACTLY, 1, TakeableItem.Items.KEY_OF_KANYE);


        private InventoryCondition.ItemComparison comparison;
        private int amount;
        private String description;
        private TakeableItem.Items item;
        private String pathToPicture;

        //Enum constructor
        Quests(String pathToPicture,String description,InventoryCondition.ItemComparison comparison, int amount, TakeableItem.Items item) {
            this.pathToPicture = pathToPicture;
            this.description = description;
            this.comparison = comparison;
            this.amount = amount;
            this.item = item;
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

        return Quests.values()[getID()].getPathToPicture();
    }

    @Override
    public Type getType() {
        return super.getType();
    }


    //Getters
    public String getDescription(){return Quests.values()[getID()].description;}
    public InventoryCondition.ItemComparison getComparison(){ return Quests.values()[getID()].comparison;}
    public TakeableItem.Items getItem(){ return Quests.values()[getID()].item;}
    public int getAmount(){ return Quests.values()[getID()].amount;}


    @Override
    public boolean onTouch(Entity entity) {
        Conditions conditions = new Conditions(
                new InventoryCondition(entity, this.getComparison(), this.getAmount(), this.getItem()),
                new StatCondition(entity, StatCondition.StatsComparison.AT_LEAST,5, Stats.Type.LEVEL)
        );


        if (conditions.checkCondition()) {
            System.out.println("You did the thing!");
            return true;
        } else{
            System.out.println(this.getDescription());
            System.out.println("Item in bag count: " + entity.getInventory().getItemCount(this.getItem()));
            System.out.println("Required Count: " + this.getAmount());
            System.out.println("You had one job...");
        }
        return false;
    }

}