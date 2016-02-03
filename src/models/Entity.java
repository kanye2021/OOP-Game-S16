package models;

import utilities.Observable;

/**
 * Created by Bradley on 2/1/16.
 */

public class Entity extends Observable {
    
    private final int START_X = 10;
    private final int START_Y = 5;

    //Entity properties
    private String lastAtemptedDirection;
    private String occupation;
    private Stats entityStats;
    private Inventory inventory;

    private int[] location;

    public Entity(){
        location = new int[2];
        location[0] = START_X;
        location[1] = START_Y;

        lastAtemptedDirection = "N";
    }
    /*----------Get and Setters --------*/
    public int[] getLocation(){
        return location;
    }
    public String getOrientation(){
        return this.lastAtemptedDirection;
    }
    public String getOccupation(){
        return this.occupation;
    }
    public Stats getStats(){
        return this.entityStats;
    }
    public Inventory getInventory(){
        System.out.println("Inventory has been got, nigga");
        return this.inventory;
    }
    public void setOccupation(String type) {
        this.occupation = type;
    }
    public void updateOrientation(String orientation){
        lastAtemptedDirection = orientation;
    }
    /* ------End of Getters and Setters -----*/

    //All this is going to do is update orientation of the entity

    public void moveTo(int x, int y, String direction) {
        location[0] = x;
        location[1] = y;
        updateOrientation(direction);

        /* These functions exist in the utilities.Observable class */
        setChanged();
        notifyObservers();  //Observers are the views
    }



}
