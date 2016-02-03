/**
 * Created by Bradley on 2/1/16.
 */
public class NavigationMediator {
    private Map map;
    private Entity entity;

    public NavigationMediator(Map map, Entity entity){
        this.map = map;
        this.entity = entity;
    }

    // Directions take the following form:
    // N, NE, E, SE, S SW, W, NW
    public void requestMovement(String direction){
        System.out.println("Player is currently at (" + entity.getLocation()[0] + ", " + entity.getLocation()[1] + ")");
        System.out.println("DIRECTION: " + direction);
        // Assumes the entity has a location of the form int[] = {x, y}
        int[] currentLocation = entity.getLocation();

        // Update the entity's orientation;
        entity.updateOrientation(direction);


        //-------------------------------Determine where we are trying to move---------------------------------------

        // The location desired to be moved to will be initialized to the current location.
        int desiredX = currentLocation[0];
        int desiredY = currentLocation[1];

        switch(direction){
            case "N": desiredY -=1;
                break;
            case "S": desiredY +=1;
                break;
            case "E": desiredX +=1;
                break;
            case "W": desiredX -=1;
                break;
            case "NE":
                desiredY-=1;
                desiredX+=1;
                break;
            case "SE":
                desiredY+=1;
                desiredX+=1;
                break;
            case "SW":
                desiredY+=1;
                desiredX-=1;
                break;
            case "NW":
                desiredY-=1;
                desiredX +=1;
                break;
        }


        //-----------------First check to see if it is possible to move here.-------------------------------------

        // The user is trying to move outside the range of the map, DO NOT ALLOW THIS (DUH).
        if(desiredX < 0 || desiredX >= map.getMapWidth() || desiredY < 0 || desiredY >= map.getMapHeight()){
            return;
        }


        // Check to see if the terrain is passable. For this iteration, we can only pass through grass.
        Terrain terrain = map.getTerrainAtLocation(desiredX, desiredY);
        if(!terrain.getType().equals("grass")){
            return;
        }


        // Check to see if there is another entity blocking the path.
        Entity entityOnTile = map.getEntityAtLocation(desiredX, desiredY);
        if(entityOnTile!=null){
            return;
        }


        // Check to see if there is an obstacle
        Item item = map.getItemAtLocation(desiredX, desiredY);
        if(item!=null && item.getType() == "obstacle"){
            return;
        }

        //----------------------If we got here we are okay to move so lets do it!----------------------------------

        // Remove the entity from it's current location and put it in the new one.
        map.removeEntityFromLocation(currentLocation[0], currentLocation[1]);
        map.insertEntityAtLocation(desiredX, desiredY, entity);

        entity.moveTo(desiredX, desiredY, direction);



        //----------------------------Take care of anything that resulted from the move.---------------------------

        // Trigger any area effects
        AreaEffect areaEffect = map.getAreaEffectAtLocation(desiredX, desiredY);
        if(areaEffect!=null){
            areaEffect.onTouch(entity);
        }

        // Take care of activating any items that have been encountered.
        if(item!=null){
            boolean removeItem = item.onTouch(entity);
            if(removeItem){
                map.removeItemFromLocation(currentLocation[0], currentLocation[1]);
            }
        }

    }

    public void returnItemToMap(int x, int y, Item item){
        map.insertItemAtLocation(x, y, item);
    }

    /*-------------------Testing Load and Save ------------ */
    private Load_Save ls;
    public void save(){
        ls = new Load_Save(); // Should be moved to the constructor of wherever it is placed

        ls.save(entity, map); //calls the save function on the class Load_Save
    }
}
