package models;

import utilities.NavigationMediator;

/**
 * Created by Bradley on 2/1/16.
 */

public class Entity {

    protected final int START_X = 24;
    protected final int START_Y = 35;

    //Entity properties
    protected String lastAtemptedDirection;
    protected String occupation;
    protected Stats stats;
    protected Inventory inventory;
    protected EquippedItems equippedItems;
    protected ItemStatsAssociation avatarItemStats;
    protected int[] location;
    protected NavigationMediator mediator;

    public Entity() {
        // Default to smasher
        initEntity("smasher");
    }

    public Entity(String occupation) {
        initEntity(occupation);
    }



    private void initEntity(String occupation) {
        this.location = new int[2];
        this.location[0] = START_X;
        this.location[1] = START_Y;
        this.lastAtemptedDirection = "N";
        this.occupation = occupation;
        this.inventory = new Inventory();
        this.equippedItems = new EquippedItems();
        this.stats = new Stats(occupation);
        this.avatarItemStats = new ItemStatsAssociation(this);
        //Avatar parsing

    }

    /*----------Get and Setters --------*/
    public int[] getLocation() {
        return location;
    }

    public String getOrientation() {
        return this.lastAtemptedDirection;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public NavigationMediator getNavigationMediator() {

        return mediator;

    }

    public Stats getStats() {
        return this.stats;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public EquippedItems getEquippedItems() {
        return this.equippedItems;
    }

    public ItemStatsAssociation getAvatarItemStats() {
        return this.avatarItemStats;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void updateLocation(int x, int y) {
        this.location[0] = x;
        this.location[1] = y;
    }

    // Each "type" (subclass) of entity will override this method to return its type.
    public String getType() {
        return "entity";
    }

    public void updateOrientation(String orientation) {
        lastAtemptedDirection = orientation;
    }

    public String getImageName() {
        return "entity-" + getOccupation() + "-" + lastAtemptedDirection + ".png";
    }
    /* ------End of Getters and Setters -----*/

    //All this is going to do is update orientation/location of the entity

    public void moveTo(int x, int y, String direction) {
        location[0] = x;
        location[1] = y;
        updateOrientation(direction);


    }

    public int getID() {

        return 666; // cancer

    }

}
