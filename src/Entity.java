/**
 * Created by Bradley on 2/1/16.
 */
public class Entity extends java.util.Observable {
    private final int START_X = 10;
    private final int START_Y = 5;
    private String lastAtemptedDirection;
    private String occupation;
    private Stats entityStats;

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
    public void setOccupation(String type) {
        this.occupation = type;
    }
    public void updateOrientation(String orientation){
        lastAtemptedDirection = orientation;
    }
    /* ------End of Getters and Setters -----*/

    //All this is going to do is update orientation of the entity

    public void moveTo(int x, int y) {
        location[0] = x;
        location[1] = y;

        /* These functions exist in the Observable class */

        setChanged();
        notifyObservers();  //Observers are the views
    }



}
