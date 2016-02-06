package models.items;

import models.Entity;

/**
 * Created by denzel on 2/5/16.
 */
public class InteractiveItem extends Item {

    //Needs an enum

    public enum Quests{
        /**
         * have an enum where the ID will denote the
         * name of the quest, description of the quest, items to look for, number of those items to get, picture
         *
         */

        SAVE_JORGE("saveJorge","you have to kill jorge", new TakeableItem.Items[]{TakeableItem.Items.IRON_SWORD}, new int[]{1},"take-able.png"),
        DAVID_SQUARED("davidSquared","David^2 needs you for something", new TakeableItem.Items[]{TakeableItem.Items.IRON_SWORD,TakeableItem.Items.STEEL_SWORD}, new int[]{1,2},"take-able.png"),
        BRAGIO("saveJorge","you have to kill jorge", new TakeableItem.Items[]{TakeableItem.Items.IRON_SWORD}, new int[]{1},"take-able.png");

        private String name;
        private String description;
        private String pathToPicture;
        private TakeableItem.Items items[];
        private int numOfItems[];

        //Enum constructor
        private Quests(String name, String description, TakeableItem.Items items[], int numOfItems[],String pathToPicture) {
            this.name = name;
            this.description = description;
            this.items = items;
            this.numOfItems = numOfItems;
            this.pathToPicture = pathToPicture;
        }

        public int getID() {
            return ordinal();
        }

        public String getName() {
            return name;
        }

        public String getDescription(){
            return description;
        }

        public TakeableItem[] getRequiredItems(){
            TakeableItem items[] = new TakeableItem[this.items.length];
            TakeableItem stuff;
            for(int i=0;i<this.items.length;i++){
                stuff = new TakeableItem(Quests.values()[getID()].items[i]);
                items[i] = stuff;
            }
            return items;
        }

        public int[] getNumItems(){
            return numOfItems;
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

        System.out.println(Quests.values()[getID()].getRequiredItems());


        //Check conditions and see if can activate items
       


        return false;
    }


}
