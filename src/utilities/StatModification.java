package utilities;

import models.Entity;
import models.Stats;

public class StatModification {

    // A bit of an issue with percentages, the value needs to be saved.

    public static enum NumberType {

        POINT {
            protected int getModifierAmount(int initialStatNumber, int delta, Direction direction) {
                return delta;
            }

            ;},
        PERCENTAGE {
            protected int getModifierAmount(int initialStatNumber, int delta, Direction direction) {
                if (direction == Direction.REVERSE) {

                    return (int) ((2 * initialStatNumber) * ((double) (delta - 100) / 100));

                }

                return (int) (initialStatNumber * ((double) (delta - 100) / 100));
            }

            ;

        };

        protected abstract int getModifierAmount(int initialStatNumber, int delta, Direction direction);

    }

    public static enum Direction {

        FORWARD(1),
        REVERSE(-1);

        int modifier;

        private Direction(int modifier) {

            this.modifier = modifier;

        }

        protected int getModifier() {

            return modifier;

        }

    }

    private Stats.Type statisticToModify;
    private int delta;
    private NumberType type;

    public StatModification(Stats.Type statisticToModify, int delta, NumberType type) {

        this.statisticToModify = statisticToModify;
        this.delta = delta;
        this.type = type;

    }

    private Stats.Type getStatisticToModify() {

        return statisticToModify;

    }

    private int getDelta(Entity entity, Direction direction) {

        int newDelta = type.getModifierAmount(getStatisticToModify().get(entity), delta, direction);
        return newDelta;

    }

    public void modifyStats(Entity entity, Direction direction) {

        getStatisticToModify().modify(entity, getDelta(entity, direction) * direction.getModifier());

    }

}
