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
public class TakeableItem extends Item {
	
	public enum Items {

		//EXAMPLE_TAKEABLE_ITEM("name", "description", "pathToFile", <StatModifications>)
		// Can be read as EXAMPLE_TAKEABLE_ITEM has "name", "description", "pathToFile", and StatModifications
		
		WOOD_SWORD("Wood Sword", "A sword made of wood", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		IRON_SWORD("Iron Sword", "A sword made of iron", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		STEEL_SWORD("Steel Sword", "A sword made of steel", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		WOOD_SHIELD("Wood Shield", "A Shield made of wood", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		IRON_SHIELD("Iron Shield", "A Shield made of iron", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		STEEL_SHIELD("Steel Shield", "A Shield made of steel", "takeable-item.png",	new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "takeable-item.png", new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		));
		

		private String name;
		private String description;
		private String pathToPicture;
		private StatModifications modifications;
		
		
		//Conditions conditions;
		//conditions.addItemCondition(Conditions.AT_LEAST, 2, TakeableItems.Items.IRON_SWORD, Conditions.INVENTORY)
		//conditions.addItemCondition(TakeableItems.Items.DIAMOND_SWORD, 1, Conditions.AT_LEAST, Conditions.EQUIPPED)
		//conditions.addItemCondition(TakeableItems.Items.STEEL_SWORD, 2, Conditions.AT_LEAST, Conditions.INVENTORY)
		//conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 50, Conditions.POINTS)
		//conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 0.5, Conditions.PERCENTAGE)
		

		private Items(String name, String description, String pathToPicture, StatModifications modifications) {

			this.name = name;
			this.description = description;
			this.pathToPicture = pathToPicture;
			this.modifications = modifications;
			
		}

		public int getID() {

			return ordinal();

		}
		
		public String getName() {

			return name;

		}
		
		public String getDescription() {

			return description;

		}
		
		public String getPathToPicture() {

			return pathToPicture;

		}
		
	}
		
    //Constructor
    public TakeableItem(Items item) {
    	super(item.ordinal(), Item.Type.TAKEABLE);
    }




    @Override
    public final boolean onTouch(Entity entity) {
        entity.getInventory().addItem(this);
        modifyStats(entity);
        return true;
    }

	public String getName() {
		return Items.values()[getID()].name;
	}

	public String getDescription() {
		return Items.values()[getID()].description;
	}

	@Override
	public String getPathToPicture() {
		return Items.values()[getID()].pathToPicture;
	}
	
	public void modifyStats(Entity entity) {
		
		Items.values()[getID()].modifications.modifyStats(entity, StatModification.DIRECTION.FORWARD);
		
	}
	
	public void modifyStatsReverse(Entity entity) {
		
		Items.values()[getID()].modifications.modifyStats(entity, StatModification.DIRECTION.REVERSE);
		
	}
	
}
