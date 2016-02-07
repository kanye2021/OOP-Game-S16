package models.items;

import models.Entity;

/**
 * Created by denzel on 2/5/16.
 */
public class Obstacle extends Item {

    //Enum of Obstacles
    public enum Obstacles{
        GRAVE("grave.png"),
        STATUE("statue.png"),
        Jorge("octopus.png");


        private String pathToPicture;

        public int getID() {
            return ordinal();
        }

        public String getPathToPicture() {

            return this.pathToPicture;

        }
        //Enum Constructor
        Obstacles(String filePath){
            this.pathToPicture = filePath;
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
        return Obstacles.values()[getID()].getPathToPicture();
    }

}
