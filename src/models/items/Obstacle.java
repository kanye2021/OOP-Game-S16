package models.items;

import models.Entity;

/**
 * Created by denzel on 2/5/16.
 */
public class Obstacle extends Item {

    //Enum of Obstacles
    public enum Obstacles{
        Rock,
        Wall,
        Jorge;

        public int getID() {
            return ordinal();
        }
    }


    //Constructor
    public Obstacle(Obstacles obstacles){
        super(obstacles.ordinal(), Type.OBSTACLE);
    }


    //Never gets removed but it should not allow
    //the avatar to even on the same tile
    @Override
    public boolean onTouch(Entity entity) {
        return false;
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public String getPathToPicture() {
        return null;
    }

}
