/**
 * Created by Bradley on 2/1/16.
 */
public class Entity {
    private final int START_X = 10;
    private final int START_Y = 5;
    private String lastAtemptedDirection;

    private int[] location;

    public Entity(){
        location = new int[2];
        location[0] = START_X;
        location[1] = START_Y;

        lastAtemptedDirection = "N";
    }

    public int[] getLocation(){
        return location;
    }

    public void moveTo(int x, int y){
        location[0] = x;
        location[1] = y;
    }

    public void updateOrientation(String orientation){
        lastAtemptedDirection = orientation;
    }

}
