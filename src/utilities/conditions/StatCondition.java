package utilities.conditions;

import models.Entity;
import models.Stats;
import models.items.TakeableItem;

/**
 * Created by denzel on 2/6/16.
 */
public class StatCondition extends Condition {

    //Properties of StatCondition
    private Condition.Entities entity;
    private Condition.Comparison comparison;
    private Stats.Type stat;
    private int requiredAmount;

    //Constructor
    public StatCondition(Condition.Entities entity, Condition.Comparison comparison, int requiredAmount, Stats.Type stat){
        this.entity = entity;
        this.comparison = comparison;
        this.stat = stat;
        this.requiredAmount = requiredAmount;
    }

    @Override
    protected boolean checkCondition() {
       int entityStat = stat.get(entity.getEntity());
       int requiredStat = this.requiredAmount;

       return (comparison.isValid(entityStat, requiredStat));

    }
}
