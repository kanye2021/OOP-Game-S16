package utilities.conditions;

/**
 * Created by denzel on 2/6/16.
 */

import models.Entity;
import models.Map;
import utilities.IOMediator;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */

public abstract class Condition {

    public enum Entities {

        AVATAR() {public Entity getEntity() {return IOMediator.entity;}};

        public abstract Entity getEntity();

    }

    //The Item Comparison Enum
    public enum Comparison {
        AT_LEAST(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount >= requiredCount;
            }
        },
        EXACTLY(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount == requiredCount;
            }
        },
        AT_MOST(){
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount <= requiredCount;
            }
        };

        //Function to compare
        protected abstract boolean isValid(int inventoryCount, int count);

        //Returns the ordinal for the Enum
        public int getID() {
            return ordinal();
        }
    }

    //Properties of the Condition object
    protected volatile Entity entity;
    protected Map map;

    //Check the conditions
    protected abstract boolean checkCondition();

}

