/**
 * Created by denzel on 2/3/16.
 */
package models.items;

import utilities.StatModification;
import utilities.StatModifications;
import models.Entity;
import models.Stats;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */



public class OneShotItem extends Item {

    public enum Effects {

    	GAIN_HEALTH("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.HEALTH, 5, StatModification.NumberType.POINT)
		)),
		LOSE_HEALTH("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.HEALTH, -5, StatModification.NumberType.POINT)
		)),
		GAIN_MANA("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MANA_LEFT, 5, StatModification.NumberType.POINT)
		)),
		LOSE_MANA("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MANA_LEFT, -5, StatModification.NumberType.POINT)
		)),
		GAIN_STRENGTH("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.STRENGTH, 5, StatModification.NumberType.POINT)
		)),
		LOSE_STRENGTH("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.STRENGTH, -5, StatModification.NumberType.POINT)
		)),
		GAIN_AGILITY("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.AGILITY, 5, StatModification.NumberType.POINT)
		)),
		LOSE_AGILITY("takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.AGILITY, -5, StatModification.NumberType.POINT)
		));

        //DO_OTHER_STUFF("notStats","na","Filler","Filler.png");

        private String pathToPicture;
        private StatModifications modifications;
        
        //Enum constructor
        private Effects(String pathToPicture, StatModifications modifications) {
            this.pathToPicture = pathToPicture;
            this.modifications = modifications;
        }

        public int getID() {
            return ordinal();
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
        modifyStats(entity);
        return true;
    }

    public void modifyStats(Entity entity) {
		
		Effects.values()[getID()].modifications.modifyStats(entity, StatModification.DIRECTION.FORWARD);
		
	}
	
	/*public void modifyStatsReverse(Entity entity) {
		
		Effects.values()[getID()].modifications.modifyStats(entity, StatModification.DIRECTION.REVERSE);
		
	}*/

    public String getPathToPicture() {
        return Effects.values()[getID()].pathToPicture;
    }
}
