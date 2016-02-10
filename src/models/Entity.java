package models;

import utilities.StatModification;
import utilities.StatModifications;

import java.awt.*;

/**
 * Created by Bradley
 * on 2/1/16.
 */

public abstract class Entity {

    public enum Occupation {

        SMASHER("Smasher", new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        SUMMONER("Summoner", new StatModifications(
                new StatModification(Stats.Type.AGILITY, -5, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.INTELLECT, 10, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        SNEAK("Sneak", new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 5, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.AGILITY, 10, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.HARDINESS, -5, StatModification.NumberType.POINT)
        ));

        private String s;
        private StatModifications modifications;

        private Occupation(String s, StatModifications modifications) {

            this.s = s;
            this.modifications = modifications;

        }

        public String getType() {

            return s;

        }

        public StatModifications getStatModifications() {

            return modifications;

        }

    }

    protected final StatModifications initialStats = new StatModifications(
            new StatModification(Stats.Type.LIVES, 3, StatModification.NumberType.POINT), // Should be three but something is decrementing this
            new StatModification(Stats.Type.LEVEL, 1, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.AGILITY, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.INTELLECT, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
    );

    protected final int START_X = 35;
    protected final int START_Y = 24;

    //Entity properties
    protected String lastAtemptedDirection;
    protected Occupation occupation;
    protected Stats stats;
    protected Inventory inventory;
    protected EquippedItems equippedItems;
    protected ItemStatsAssociation avatarItemStats;
    protected Point location;

   // public Entity() {
        // Default to smasher
    //    initEntity("smasher");
    //}

    public Entity(Occupation occupation) {

        location = new Point(START_X, START_Y);

        this.lastAtemptedDirection = "N";

        this.occupation = occupation;

        this.inventory = new Inventory();
        this.equippedItems = new EquippedItems();

        this.stats = new Stats();
        this.avatarItemStats = new ItemStatsAssociation(this);

        initialStats.modifyStats(this, StatModification.Direction.FORWARD);
        occupation.modifications.modifyStats(this, StatModification.Direction.FORWARD);

    }

    /*----------Get and Setters --------*/
    public Point getLocation() {
        return location;
    }

    public String getOrientation() {
        return this.lastAtemptedDirection;
    }

    public Occupation getOccupation() {
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

    public void setOccupation(Occupation occupation) {

        if (occupation != null) {

            occupation.getStatModifications().modifyStats(this, StatModification.Direction.REVERSE);

        }

        this.occupation = occupation;
        occupation.getStatModifications().modifyStats(this, StatModification.Direction.FORWARD);

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
