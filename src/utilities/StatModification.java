package utilities;

import models.Entity;
import models.Stats;

public class StatModification {

	public static enum NumberType {
		
		POINT {protected int getModifierAmount(int initialStatNumber, double delta) {return (int) delta;};},
		PERCENTAGE {protected int getModifierAmount(int initialStatNumber, double delta) {return (int) (initialStatNumber * (delta));};};
		
		protected abstract int getModifierAmount(int initialStatNumber, double delta);
		
	}
	
	public static enum DIRECTION {
		
		FORWARD(1),
		REVERSE(-1);
		
		int modifier;
		
		private DIRECTION(int modifier) {
			
			this.modifier = modifier;
			
		}
		
		protected int getModifier() {
			
			return modifier;
			
		}
		
	}
	
	private Stats.Type statisticToModify;
	private double delta;
	private NumberType type;
	
	public StatModification(Stats.Type statisticToModify, double delta, NumberType type) {
		
		this.statisticToModify = statisticToModify;
		this.delta = delta;
		this.type = type;
		
	}
	
	private Stats.Type getStatisticToModify() {
		
		return statisticToModify;
		
	}
	
	private int getDelta(Entity entity) {
		
		int newDelta = type.getModifierAmount(getStatisticToModify().get(entity), delta);
		return newDelta;
		
	}
	
	public void modifyStats(Entity entity, DIRECTION direction) {
		
		getStatisticToModify().modify(entity, getDelta(entity) * direction.getModifier());
		
	}
	
}
