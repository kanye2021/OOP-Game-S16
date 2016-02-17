package utilities.conditions;

import models.Entity;
import models.Map;
import models.items.Item;
import models.items.TakeableItem;

/**
 * Created by aseber
 * on 2/6/16.
 */

public class MapEntityCondition extends MapCondition {

    Condition.Entities entity;
    MapCondition.Location location;
    int x;
    int y;
    MapCondition.Maps map;

    public MapEntityCondition(Condition.Entities entity, MapCondition.Location location, int x, int y, MapCondition.Maps map) {

        this.entity = entity;
        this.location = location;
        this.x = x;
        this.y = y;
        this.map = map;

    }

    public boolean checkCondition() {

        Entity entityOnMap = map.getMap().getEntityAtLocation(x, y);

        if (entityOnMap != null) {

            int i1 = entityOnMap.getID();
            int i2 = entity.getEntity().getID();

            return location.checkLocation(i1, i2);

        }

        // A bit hacky, but it allows us to return true when the item isn't in that location, or false when it is.
        // Dependent on the equality checker!
        return location.checkLocation(1, 0);

    }

}
