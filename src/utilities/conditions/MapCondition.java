package utilities.conditions;

import models.Map;
import utilities.IOMediator;

/**
 * Created by aseber
 * on 2/9/16.
 */
public abstract class MapCondition extends Condition {

    public enum Maps {

        DEFAULT {public Map getMap() {return IOMediator.map;}};

        public abstract Map getMap();

    }

    private enum Equality {

        EQUAL {public boolean check(int i1, int i2) {return i1 == i2;}},
        NOT_EQUAL {public boolean check(int i1, int i2) {return i1 != i2;}};

        public abstract boolean check(int i1, int i2);

    }

    public enum Location {

        LOCATED_AT() {public boolean checkLocation(int i1, int i2) {return Equality.EQUAL.check(i1, i2);}},
        NOT_LOCATED_AT {public boolean checkLocation(int i1, int i2) {return Equality.NOT_EQUAL.check(i1, i2);}};

        public abstract boolean checkLocation(int i1, int i2);

    }

}
