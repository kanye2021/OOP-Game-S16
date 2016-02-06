/**
 * Created by denzel on 2/3/16.
 */
package models.items;

import models.Entity;

import java.util.HashMap;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {
	public enum Items {
		

		WOOD_SWORD("Wood Sword", "A sword made of wood", "takeable-item.png", "equippable","primaryWeapon", "2", "0"),
		IRON_SWORD("Iron Sword", "A sword made of iron", "takeable-item.png", "equippable","primaryWeapon", "5", "0"),
		STEEL_SWORD("Steel Sword", "A sword made of steel", "takeable-item.png", "equippable","primaryWeapon", "7", ""),
		DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "takeable-item.png", "equippable","primaryWeapon", "10", "0"),
		WOOD_SHIELD("Wood Shield", "A Shield made of wood", "takeable-item.png", "equippable","secondaryWeapon", "0", "2"),
		IRON_SHIELD("Iron Shield", "A Shield made of iron", "takeable-item.png", "equippable","secondaryWeapon", "0", "5"),
		STEEL_SHIELD("Steel Shield", "A Shield made of steel", "takeable-item.png", "equippable","secondaryWeapon", "0", "7"),
		DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "takeable-item.png", "equippable","secondaryWeapon", "0", "10");

		//a mini skirt for jorge in the jungle | jorge in the jungle figurine
		
		private String name;
		private String description;
		private String pathToPicture;
        private String property;
        private String component;
        private String offensiveRating;
        private String armorRating;
        private boolean isEquippable;
        private boolean isUsable;


		Items(String name, String description, String pathToPicture, String property, String component, String offfensiveRating, String armorRating) {
			
			this.name = name;
			this.description = description;
			this.pathToPicture = pathToPicture;

            //Whatever the property(i.e. usable, equippable, etc.)
            this.property = property;

            //Set type of Item
            this.component = component;
			this.offensiveRating = offfensiveRating;
			this.armorRating = armorRating;

            //Check if equippable or usable
            if(this.property.compareTo("equippable") == 1){
                isEquippable = true;
                isUsable = false;
            }

            else if(this.property.compareTo("usable") == 1) {
                isEquippable = false;
                isUsable = true;
            }

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

        public String getComponent(){
            return component;
        }

        public int getOffensiveRating(){
            return Integer.parseInt(offensiveRating);
        }

        public int getArmorRating(){
            return Integer.parseInt(armorRating);
        }

        public boolean getIsEquippable(){
            return isEquippable;
        }

        public boolean getIsUsable() {
            return isUsable;
        }
    }
		
    //Constructor
    public TakeableItem(Items item) {
    	super(item.ordinal(), Item.Type.TAKEABLE);
    }

    /**
     *Returns true if it should be removed from the map
     */
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
