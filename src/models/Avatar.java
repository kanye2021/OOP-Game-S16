package models;

/**
 * Created by sergiopuleri on 2/4/16.
 */
public class Avatar extends Entity {

    public Avatar() {
        super();

    }
    public Avatar(String occupation) {
        super();
        this.occupation = occupation;
    }

    public void dropItem(Item item) {
        this.inventory.removeItem(item);
        // Drop item on the map..
        // Need to be able to insert item on map...
        // Singleton??
        // Map.insertItemAtLocation(location[0], location[1], item);

    }
    public void equipItem(Item item){
        //TODO: equipped items

    }
}
