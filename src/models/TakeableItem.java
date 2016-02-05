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
		STEEL_SWORD("Steel Sword", "A sword made of steel", "takeable-item.png");
		
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
    public TakeableItem(int id) {
    	super(id, Item.Type.TAKEABLE);
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
    public String getImageName(){
        return pathToPicture;
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
