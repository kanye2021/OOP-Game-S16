package utilities;

import models.Entity;
import models.Map;
import models.Terrain;
import models.area_effects.AreaEffect;
import models.items.Item;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bradley on 2/1/16.
 */
public class NavigationMediator {
    private Map map;
    private Entity entity;
    private Timer timer;
    boolean canMove;


    public NavigationMediator(Map map, Entity entity) {
        this.map = map;
        this.entity = entity;
        timer = new Timer();
        canMove = true;
    }

    // Directions take the following form:
    // N, NE, E, SE, S SW, W, NW
    public void requestMovement(String direction) {

        // If the player has moved too recently do not allow it
        if (!canMove) {
            return;
        }

        // Assumes the avatar has a location of the form int[] = {x, y}
        Point currentLocation = entity.getLocation();

        // Update the avatar's orientation;
        entity.updateOrientation(direction);


        //-------------------------------Determine where we are trying to move---------------------------------------

        // The location desired to be moved to will be initialized to the current location.
        int desiredX = currentLocation.y;
        int desiredY = currentLocation.x;

        switch (direction) {
            case "N":
                desiredY -= 1;
                break;
            case "S":
                desiredY += 1;
                break;
            case "E":
                desiredX += 1;
                break;
            case "W":
                desiredX -= 1;
                break;
            case "NE":
                desiredY -= 1;
                desiredX += 1;
                break;
            case "SE":
                desiredY += 1;
                desiredX += 1;
                break;
            case "SW":
                desiredY += 1;
                desiredX -= 1;
                break;
            case "NW":
                desiredY -= 1;
                desiredX -= 1;
                break;
        }


        //-----------------First check to see if it is possible to move here.-------------------------------------

        // The user is trying to move outside the range of the map, DO NOT ALLOW THIS (DUH).
        if (desiredX < 0 || desiredX >= map.getMapWidth() || desiredY < 0 || desiredY >= map.getMapHeight()) {
            return;
        }


        // Check to see if the terrain is passable. For this iteration, we can only pass through grass.
        Terrain terrain = map.getTerrainAtLocation(desiredX, desiredY);
        if (!terrain.getType().equals("grass")) {
            return;
        }


        // Check to see if there is another avatar blocking the path.
        Entity entityOnTile = map.getEntityAtLocation(desiredX, desiredY);
        if (entityOnTile != null) {
            return;
        }


        // Check to see if there is an obstacle
        Item item = map.getItemAtLocation(desiredX, desiredY);

        if (item != null) {

            if (item.getType().equals(Item.Type.OBSTACLE)) {

                return;

            }

            if (item.equalsType(Item.Type.INTERACTIVE)) {

                if (!item.onTouch(entity)) {

                    return;

                } else {

                    System.out.println("KILL ME");
                    map.removeItemFromLocation(currentLocation.x, currentLocation.y);

                }

            }

        }
        //----------------------If we got here we are okay to move so lets do it!----------------------------------

        // Remove the avatar from it's current location and put it in the new one.
        map.removeEntityFromLocation(currentLocation.y, currentLocation.x);
        map.insertEntityAtLocation(desiredX, desiredY, entity);

        entity.moveTo(desiredX, desiredY, direction);
        canMove = false;

        // Set a timer to determine when the avatar can move again.
        // The delay is inversely proportional to the avatar's movement stats
        int delay = 100 - (entity.getStats().getMovement() / 5);

        // If the delay is less than 0, the avatar defaults to the fastest movement of 5ms.
        delay = delay > 0 ? delay : 5;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canMove = true;
            }
        }, delay);


        //----------------------------Take care of anything that resulted from the move.---------------------------

        // Trigger any area effects
        AreaEffect areaEffect = map.getAreaEffectAtLocation(desiredX, desiredY);
        if (areaEffect != null) {
            areaEffect.onTouch(entity);
        }

        // Take care of activating any items that have been encountered.
        if (item != null) {
            boolean questFinished = item.onTouch(entity);
            if (questFinished) {
                map.removeItemFromLocation(currentLocation.y, currentLocation.x);
            }
        }

    }

    public void returnItemToMap(int x, int y, Item item) {
        map.insertItemAtLocation(x, y, item);
    }

}
