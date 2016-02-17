/**
 * Created by aseber
 * on 2/8/16.
 */

package utilities.conditions;

import java.util.ArrayList;
import java.util.List;

public class ORCondition extends Condition {

    ArrayList<Condition> conditions = new ArrayList<Condition>();

    public ORCondition(Condition... conditionsToAdd) {

        for (Condition condition : conditionsToAdd) {

            conditions.add(condition);

        }

    }

    public boolean checkCondition() {

        for (Condition condition : conditions) {

            if (condition.checkCondition()) {

                return true;

            }

        }

        return false;

    }

}
