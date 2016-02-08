package models.items;

import models.Entity;

/**
 * Created by Bradley on 2/1/16.
 * Modified by Austin on 2/2/16.
 */

public abstract class Item {

    public enum Type {
        TAKE_ABLE("take-able"),
        ONE_SHOT("one-shot"),
        INTERACTIVE("interactive"),
        OBSTACLE("obstacle");

        private String s;

        Type(String s) {
            this.s = s;
        }

        public String toString() {
            return s;
        }
    }

    //Attributes that all Items will have
    protected int id;
    protected Type type;

    //Constructor
    protected Item(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    //Getters
    public final int getID() {

        return id;

    }

    public final Type getType() {

        return type;

    }


    public abstract String getPathToPicture();

    public abstract boolean onTouch(Entity entity);

    //Do not touch
    @Override
    public final int hashCode() {

        return id;

    }

    //Do not touch
    @Override
    public final boolean equals(Object o) {

        if (o instanceof Item) {

            Item otherItem = (Item) o;

            if (this.getID() == otherItem.getID()) {

                return true;

            }

        }

        return false;

    }

    public final boolean equalsType(Item.Type type) {

        return type == this.getType();

    }

    public String toString() {
        return getType() + ": " + getID();
    }


}
