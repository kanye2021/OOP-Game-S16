package utilities;

import models.Entity;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class StatModifications extends ArrayList<StatModification> {

    public StatModifications(StatModification... modifications) {

        for (StatModification modification : modifications) {

            add(modification);

        }

    }

    @Override
    public boolean add(StatModification statistic) {

        return super.add(statistic);

    }

    public void modifyStats(Entity entity, StatModification.Direction direction) {

        for (StatModification modification : this) {

            modification.modifyStats(entity, direction);

        }

    }

}
