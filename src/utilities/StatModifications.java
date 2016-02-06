package utilities;

import java.util.ArrayList;

import models.Entity;

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
	
	public void modifyStats(Entity entity, StatModification.DIRECTION direction) {
		
		for (StatModification modification : this) {
			
			modification.modifyStats(entity, direction);
			
		}
		
	}
	
}
