package models;

/**
 * Created by sergiopuleri on 2/4/16.
 */
public class Avatar extends Entity {

    public Avatar(){

    }
    public Avatar(String occupation) {
        super(occupation);
    }

    public void dropItem(Item item) {
        this.inventory.removeItem(item);
        // TODO: Drop item on the map..
        // Need to be able to insert item on map...
        // Singleton??
        // Map.insertItemAtLocation(location[0], location[1], item);

    }

    @Override
    public String getType() {
        return "avatar";
    }

    public void equipItem(Item item){
        //TODO: equipped items
    }
}
