
package models;


/**
 * Created by ben on 2/2/16.
 */



public class Stats {

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


    public String getLivesLeft(){return Integer.toString(livesLeft);}
    public String getLevel(){
            return Integer.toString(level);
        }
    public String getExperience(){return Integer.toString(experience);}
    public String getExpReqLvUp(){return Integer.toString(expReqLvUp);}
    public String getMaxHealth(){
            return Integer.toString(maxLife);
        }
    public String getMaxMana(){return Integer.toString(maxMana);}
    public String getHealth(){return Integer.toString(lifeLeft);}
    public String getMana(){
            return Integer.toString(manaLeft);
        }
    public String getStrength(){return Integer.toString(strength);}
    public String getAgility(){return Integer.toString(agility);}
    public String getIntellect(){return Integer.toString(intellect);}
    public String getHardiness(){return Integer.toString(hardiness);}
    public String getMovement(){return Integer.toString(movement);}
    public String getOffensiveRating(){return Integer.toString(offensiveRating);}
    public String getDefensiveRating(){return Integer.toString(defensiveRating);}
    public String getArmorRating(){return Integer.toString(armorRating);}

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

        public void modifyLivesLeft(int delta){
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

