/**
 * Created by denzel on 2/3/16.
 */
package models;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */



public class OneShotItem extends Item {

    public enum Effects {

        GAIN_HEALTH("stats","health", "5", "takeable-item.png"),
        LOSE_HEALTH("stats","health","-5", "takeable-item.png"),
        GAIN_MANA("stats","mana", "5", "takeable-item.png"),
        LOSE_MANA("stats","mana","-5", "takeable-item.png"),
        GAIN_STRENGTH("stats","strength", "5", "takeable-item.png"),
        LOSE_STRENGTH("stats","strength","-5", "takeable-item.png"),
        GAIN_AGILITY("stats","agility", "5", "takeable-item.png"),
        LOSE_AGILITY("stats","agility","-5", "takeable-item.png"),

        DO_OTHER_STUFF("notStats","na","Filler","Filler.png");

        private String modifier;
        private String stat;
        private String amount;
        private String pathToPicture;

        //Enum constructor
        private Effects(String modifier, String stat, String amount, String pathToPicture) {
            this.modifier = modifier;
            this.stat = stat;
            this.amount = amount;
            this.pathToPicture = pathToPicture;
        }

        public int getID() {
            return ordinal();
        }

        public String getModifier() {
            return modifier;
        }

        public String getStat(){
            return stat;
        }

        public String getAmount() {
            return amount;
        }

        public String getPathToPicture() {
            return pathToPicture;
        }
    }


    //Class Constructor
    public OneShotItem(Effects effects){
        super(effects.ordinal(), Type.ONE_SHOT);
    }



    /**
     *Returns true if it should be removed from the map
     */
    @Override
    public final boolean onTouch(Entity entity) {
        System.out.println("Modifying stats");
        entity.getStats().modifyLifeLeft(Integer.parseInt(this.getDescription()));
        return true;
    }

    @Override
    public String getName() {
        return Effects.values()[getID()].modifier;
    }

    @Override
    public String getDescription() {
        return Effects.values()[getID()].amount;
    }

    @Override
    public String getPathToPicture() {
        return Effects.values()[getID()].pathToPicture;
    }
}
