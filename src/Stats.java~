package game;

/**
 * Created by ben on 2/2/16.
 */






public class Stats extends Obeservable{

        int livesLeft = 0;
        int manaLeft = 0;
        int strength = 0;
        int agility = 0;
        int intellect = 0;
        int hardiness = 0;
        int movement = 0;

        int experience = 0;
        int expReqLvUp = 0;
        int level = 0;

        int maxLife = 0;
        int maxMana = 0;

        int offensiveRating = 0;
        int defensiveRating = 0;
        int armorRating = 0;




        public Stats(String type){//Initializes the original stats from occupation
        //the constructor

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

            livesLeft = maxLife;
            manaLeft = maxMana;

            offensiveRating = /*weapon + */strength + level;
            defensiveRating = agility + level;
            armorRating = /*armor*/ + hardiness;

            expReqLvUp = 100;
        }
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
            if((livesLeft + delta) <= 0 )//So livesLeft does not drop below zero
            {
                livesLeft = 0;
            }
            else if((livesLeft + delta) >= maxLife )//So livesLeft does not go beyond the max
            {
                livesLeft = maxLife;
            }
            else{
                livesLeft = livesLeft + delta;
            }
        }

        public void modifyManaLeft(int delta){
            if((manaLeft + delta) <= 0 )//So livesLeft does not drop below zero
            {
                manaLeft = 0;
            }
            else if((livesLeft + delta) >= maxMana )//So livesLeft does not go beyond the max
            {
                manaLeft = maxMana;
            }
            else{
                manaLeft = manaLeft + delta;
            }
        }



    /*public void modifyLifeLeft(int delta) {
        if((livesLeft + delta) <= 0 )//So livesLeft does not drop below zero
        {
            livesLeft = 0;
        }
        else if((livesLeft + delta) >= maxLife )//So livesLeft does not go beyond the max
        {
            livesLeft = maxLife;
        }
        else{
            livesLeft = livesLeft + delta;
        }

    }

    public void modifyManaLeft(int delta) {
        if((manaLeft + delta) <= 0 )//So livesLeft does not drop below zero
        {
            manaLeft = 0;
        }
        else if((livesLeft + delta) >= maxMana )//So livesLeft does not go beyond the max
        {
            manaLeft = maxMana;
        }
        else{
            manaLeft = manaLeft + delta;
        }

    }

    public void modifyStrength(int delta) {
        strength = strength + delta;
    }

    public void modifyagility(int delta) {
        agility = agility + delta;
    }

    public void modifyintellect(int delta) {
        intellect = intellect + delta;
        modifyMaxMana(delta);
        modifyManaLeft(delta);
    }

    public void modifyhardiness(int delta) {
        hardiness = hardiness + delta;
        modifyMaxLife(delta);
        modifyLifeLeft(delta);
    }

    public void modifyexperience(int delta) {
        experience = experience + delta;

    }

    public void modifyMovement(int delta) {
        movement = movement + delta;
    }

    public void modifyLevel(int delta) {//same as level up...for the most part
        level = level + delta;


    }

    public void modifyMaxLife(int delta) {
        maxLife = maxLife + delta;
    }

    public void modifyMaxMana(int delta) {
        maxMana = maxMana + delta;
    }

    public void modifyOffensiveRating(int delta) {
        offensiveRating = offensiveRating + delta;

    }

    public void modifyDefensiveRating(int delta) {
        defensiveRating = defensiveRating + delta;
    }

    public void modifyArmorRating(int delta) {
        armorRating = armorRating + delta;
    }
*/
}

