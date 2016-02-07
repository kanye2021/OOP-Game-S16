package utilities.conditions;

/**
 * Created by denzel on 2/6/16.
 */

import models.Entity;
import models.Map;
import models.items.TakeableItem;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */

public abstract class Condition {

    //Properties of the Condition object
    protected volatile Entity entity;
    protected Map map;


    //Check the conditions
    protected abstract boolean checkCondition();

}

