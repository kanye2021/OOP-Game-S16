/**
 * Created by denzel on 2/3/16.
 */
package models;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {

	public static enum Items {
		
		WOOD_SWORD("Wood Sword", "A sword made of wood", "takeable-item.png"),
		IRON_SWORD("Iron Sword", "A sword made of iron", "takeable-item.png"),
		STEEL_SWORD("Steel Sword", "A sword made of steel", "takeable-item.png"),
		DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "takeable-item.png"),
		WOOD_SHIELD("Wood Shield", "A Shield made of wood", "takeable-item.png"),
		IRON_SHIELD("Iron Shield", "A Shield made of iron", "takeable-item.png"),
		STEEL_SHIELD("Steel Shield", "A Shield made of steel", "takeable-item.png"),
		DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "takeable-item.png");
		
		//a mini skirt for jorge in the jungle | jorge in the jungle figurine
		
		private String name;
		private String description;
		private String pathToPicture;
		
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
