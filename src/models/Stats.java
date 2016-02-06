
package models;


/**
 * Created by ben on 2/2/16.
 */

public class Stats {

	public static enum Type {
		
		LIVES_LEFT("Lives left") {public void modify(Entity entity, int delta) {entity.getStats().modifyLivesLeft(delta);};},
		EXPERIENCE("Experience") {public void modify(Entity entity, int delta) {entity.getStats().modifyExperience(delta);};},
		MOVEMENT("Movement") {public void modify(Entity entity, int delta) {entity.getStats().actuallyModifyMovement(delta);};},
		LEVEL("Level") {public void modify(Entity entity, int delta) {entity.getStats().modifyLevel(delta);};},
		STRENGTH("Strength") {public void modify(Entity entity, int delta) {entity.getStats().modifyStrength(delta);};},
		AGILITY("Agility") {public void modify(Entity entity, int delta) {entity.getStats().modifyAgility(delta);};},
		INTELLECT("Intellect") {public void modify(Entity entity, int delta) {entity.getStats().modifyIntellect(delta);};},
		HARDINESS("Hardiness") {public void modify(Entity entity, int delta) {entity.getStats().modifyHardiness(delta);};};
		
		private String s;
		
		public abstract void modify(Entity entity, int amount);
		
		private Type(String s) {
			
			this.s = s;
			
		}
		
		public String toString() {
			
			return s;
			
		}
		
	}
	
    private int livesLeft = 0;

    private int lifeLeft = 0;
    private int manaLeft = 0;
    private int strength = 0;
    private  int agility = 0;
    private int intellect = 0;
    private int hardiness = 0;
    private int movement = 0;

    private int experience = 0;
    private int expReqLvUp = 0;
    private int level = 0;

    private int maxLife = 0;
    private int maxMana = 0;

    private int offensiveRating = 0;
    private int defensiveRating = 0;
    private int armorRating = 0;

    public Stats(){
        livesLeft = 3;
        experience = 0;
        movement = 10;
        level = 1;
        strength = 10;
        agility = 12;
        intellect = 10;
        hardiness = 10;
        maxLife = hardiness + level;
        maxMana = intellect + level;

        lifeLeft = maxLife;
        manaLeft = maxMana;

        offensiveRating = /*weapon + */strength + level;
        defensiveRating = agility + level;
        armorRating = /*armor*/ + hardiness;

        expReqLvUp = 100;
    }
    public Stats(String type){//Initializes the original stats from occupation
        //the constructor
        livesLeft = 3;
        experience = 0;
        movement = 10;
        level = 1;

        if(type == "smasher") {
            strength = 12;
            agility = 10;
            intellect = 8;
            hardiness = 10;
        }
        else if(type == "summoner"){
            strength = 10;
            agility = 8;
            intellect = 12;
            hardiness = 10;
        }
        else if(type == "sneak"){
            strength = 10;
            agility = 12;
            intellect = 10;
            hardiness = 10;
        }
        maxLife = hardiness + level;
        maxMana = intellect + level;

        lifeLeft = maxLife;
        manaLeft = maxMana;

        offensiveRating = /*weapon + */strength + level;
        defensiveRating = agility + level;
        armorRating = /*armor*/ + hardiness;

        expReqLvUp = 100;
    }


    public int getLivesLeft(){return livesLeft;}
    public int getLevel(){
            return level;
        }
    public int getExperience(){return (experience);}
    public int getExpReqLvUp(){return (expReqLvUp);}
    public int getMaxHealth(){
            return (maxLife);
        }
    public int getMaxMana(){return (maxMana);}
    public int getHealth(){return (lifeLeft);}
    public int getMana(){
            return (manaLeft);
        }
    public int getStrength(){return (strength);}
    public int getAgility(){return (agility);}
    public int getIntellect(){return (intellect);}
    public int getHardiness(){return (hardiness);}
    public int getMovement(){return (movement);}
    public int getOffensiveRating(){return (offensiveRating);}
    public int getDefensiveRating(){return (defensiveRating);}
    public int getArmorRating(){return (armorRating);}

    //derived status or ones that require a formula to calculate
    public void modifyOffensiveRating(){
        offensiveRating = /*weapon + */strength + level;
    }

    public void modifyDefensiveRating(){
        defensiveRating = agility + level;
    }

    public void modifyArmorRating(){
        armorRating = /*armor*/ + hardiness;
    }

    public void modifyMaxLife(){
        maxLife = hardiness + level;
    }

    public void modifyMaxMana(){
        maxMana = intellect + level;
    }

    //////////////////////////
    public void modifyStrength(int delta){
        strength = strength + delta;
        modifyOffensiveRating();
    }
    public void modifyAgility(int delta){
        agility = agility +delta;
        modifyDefensiveRating();
    }
    public void modifyHardiness(int delta){
        hardiness = hardiness + delta;
        modifyArmorRating();
        modifyMaxLife();
    }
    public void modifyIntellect(int delta){
        intellect = intellect + delta;
        modifyMaxMana();
    }
    public void modifyLevel(int delta){
        level = level + delta;
        int prizes = delta * 10;
        modifyStrength(prizes);
        modifyAgility(prizes);
        modifyIntellect(prizes);
        modifyHardiness(prizes);
        lifeLeft = maxLife;
        manaLeft = maxMana;
        expReqLvUp = expReqLvUp +100;
    }

    public void modifyExperience(int delta){
        experience = experience + delta;
        while(experience >= expReqLvUp)
        {
            modifyLevel(1);
            experience = experience - expReqLvUp;
        }
    }
    //////////////////////////
    public void modifyMovement(int currentSpeed){
        movement = currentSpeed;
    }

    public void actuallyModifyMovement(int delta) {
    	
    	movement = this.getMovement() + delta;
    	movement = Math.max(movement, 0);
    	
    }
    
    public void modifyLifeLeft(int delta){
        if((lifeLeft + delta) <= 0 )//So livesLeft does not drop below zero
        {
            lifeLeft = 0;
        }
        else if((lifeLeft + delta) >= maxLife )//So livesLeft does not go beyond the max
        {
            lifeLeft = maxLife;
        }
        else{
            lifeLeft = lifeLeft + delta;
        }
    }

    public void modifyLivesLeft(int delta){
        livesLeft--;
        if(livesLeft < 0){
            //TODO: Implement GAME
            System.out.println("Game Over");
        }

        lifeLeft = maxLife;
    }

    public void modifyManaLeft(int delta){
        if((manaLeft + delta) <= 0 )//So livesLeft does not drop below zero
        {
            manaLeft = 0;
        }
        else if((lifeLeft + delta) >= maxMana )//So livesLeft does not go beyond the max
        {
            manaLeft = maxMana;
        }
        else{
            manaLeft = manaLeft + delta;
        }
    }
}

