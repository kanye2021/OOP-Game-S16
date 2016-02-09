package models;

import java.awt.*;

/**
 * Created by Bradley on 2/1/16.
 */

public class Entity {

    protected final int START_X = 35;
    protected final int START_Y = 24;

    //Entity properties
    protected String lastAtemptedDirection;
    protected String occupation;
    protected Stats stats;
    protected Inventory inventory;
    protected EquippedItems equippedItems;
    protected ItemStatsAssociation avatarItemStats;
    protected Point location;

    public Entity() {
        // Default to smasher
        initEntity("smasher");
    }

    public Entity(String occupation) {
        initEntity(occupation);
    }

    private void initEntity(String occupation) {
        location = new Point(START_X, START_Y);
        this.lastAtemptedDirection = "N";
        this.occupation = occupation;
        this.inventory = new Inventory();
        this.equippedItems = new EquippedItems();
        this.stats = new Stats(occupation);
        this.avatarItemStats = new ItemStatsAssociation(this);
        //Avatar parsing

    }

    /*----------Get and Setters --------*/
    public Point getLocation() {
        return location;
    }

    public String getOrientation() {
        return this.lastAtemptedDirection;
    }

    public String getOccupation() {
        return this.occupation;
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

    public void updateLocation(int y, int x) {
        location.setLocation(x, y);
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

    public void moveTo(int y, int x, String direction) {
        location.setLocation(x, y);
        updateOrientation(direction);


    }

    public int getID() {

        return 666; // cancer

    }

}
