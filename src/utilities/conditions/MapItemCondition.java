package utilities.conditions;

import models.Map;
import models.items.Item;

/**
 * Created by aseber
 * on 2/6/16.
 */

public class MapItemCondition extends MapCondition {

    Item item;
    MapCondition.Location location;
    int x;
    int y;
    Map map;


    // The map conditional statement. It only supports one point on the map at this time but it can be expanded to any number
    // in the future given functons in Map that return rectangles, circles etc.
    MapItemCondition(Item item, MapCondition.Location location, int x, int y, Map map) {

        this.item = item;
        this.location = location;
        this.x = x;
        this.y = y;
        this.map = map;

    }

    public boolean checkCondition() {

        Item itemOnMap = map.getItemAtLocation(x, y);

        if (itemOnMap != null) {

            int i1 = itemOnMap.getID();
            int i2 = item.getID();

            return location.checkLocation(i1, i2);

        }

        // A bit hacky, but it allows us to return true when the item isn't in that location, or false when it is.
        // Dependent on the equality checker!
        return location.checkLocation(1, 0);

    }

}
