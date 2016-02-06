/**
 * Created by denzel on 2/3/16.
 */
package models.items;

import models.Entity;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {
	
	public enum Items {


		WOOD_SWORD("Wood Sword", "A sword made of wood", "takeable-item.png"),
		IRON_SWORD("Iron Sword", "A sword made of iron", "takeable-item.png"),
		STEEL_SWORD("Steel Sword", "A sword made of steel", "takeable-item.png"),
		DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "takeable-item.png"),
		WOOD_SHIELD("Wood Shield", "A Shield made of wood", "takeable-item.png"),
		IRON_SHIELD("Iron Shield", "A Shield made of iron", "takeable-item.png"),
		STEEL_SHIELD("Steel Shield", "A Shield made of steel", "takeable-item.png"),
		DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "takeable-item.png");


		private String name;
		private String description;
		private String pathToPicture;
		
		
		//Conditions conditions;
		//conditions.addItemCondition(Conditions.AT_LEAST, 2, TakeableItems.Items.IRON_SWORD, Conditions.INVENTORY)
		//conditions.addItemCondition(TakeableItems.Items.DIAMOND_SWORD, 1, Conditions.AT_LEAST, Conditions.EQUIPPED)
		//conditions.addItemCondition(TakeableItems.Items.STEEL_SWORD, 2, Conditions.AT_LEAST, Conditions.INVENTORY)
		//conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 50, Conditions.POINTS)
		//conditions.addEntityCondition(Conditions.HEALTH, Conditions.AT_LEAST, 0.5, Conditions.PERCENTAGE)
		

		private Items(String name, String description, String pathToPicture) {
			this.name = name;
			this.description = description;
			this.pathToPicture = pathToPicture;
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
        return true;
    }

	@Override
	public String getName() {
		return Items.values()[getID()].name;
	}

	@Override
	public String getDescription() {
		return Items.values()[getID()].description;
	}

	@Override
	public String getPathToPicture() {
		return Items.values()[getID()].pathToPicture;
	}
}
