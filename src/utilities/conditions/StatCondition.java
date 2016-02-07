package utilities.conditions;

import models.Entity;
import models.Stats;
import models.items.TakeableItem;

/**
 * Created by denzel on 2/6/16.
 */
public class StatCondition extends Condition {
    public enum StatsComparison{
        AT_LEAST(){
            protected boolean isValid(int entityStat, int requiredStat) {
                return entityStat >= requiredStat;
            }
        },
        EXACTLY(){
            protected boolean isValid(int entityStat, int requiredStat) {
                return entityStat == requiredStat;
            }
        },
        AT_MOST(){
            protected boolean isValid(int entityStat, int requiredStat) {
                return entityStat <= requiredStat;
            }
        };

        protected abstract boolean isValid(int entityStat, int requiredStat);

        //Returns the ordinal for the Enum
        public int getID() {
            return ordinal();
        }
    }


    //Properties of StatCondition
    private StatsComparison comparison;
    private Stats.Type stats;
    private int requiredAmount;

    //Constructor
    public StatCondition(Entity entity, StatsComparison comparison, int requiredAmount, Stats.Type stats){
        this.entity = entity;
        this.comparison = comparison;
        this.stats = stats;
        this.requiredAmount = requiredAmount;
    }

    @Override
    protected boolean checkCondition() {
       int entityStat = stats.get(entity);
       int requiredStat = this.requiredAmount;
        
       return (comparison.isValid(entityStat, requiredStat));

    }
}
