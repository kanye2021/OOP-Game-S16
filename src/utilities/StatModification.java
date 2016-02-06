package utilities;

import models.Stats;

public class StatModification {

	private Stats.Type statisticToModify;
	private int delta;
	
	public StatModification(Stats.Type statisticToModify, int delta) {
		
		this.statisticToModify = statisticToModify;
		this.delta = delta;
		
	}
	
	public Stats.Type getStatisticToModify() {
		
		return statisticToModify;
		
	}
	
	public int getDelta() {
		
		return delta;
		
	}
	
}
