package utilities.conditions;

import java.util.ArrayList;

/**
 * Created by denzel on 2/6/16.
 */
public class Conditions extends ArrayList<Condition> {

    //Constructor that puts all the conditions for each item
    public Conditions(Condition... conditionsList) {
        for (Condition conditions : conditionsList) {
            add(conditions);
        }
    }

    public boolean checkCondition() {
        for (Condition condition : this) {
            if (!condition.checkCondition()) {
                return false;
            }
        }
        return true;
    }

}

