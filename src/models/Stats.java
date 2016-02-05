
package models;


import java.util.*;
import java.util.TimerTask;

/**
 * Created by ben on 2/2/16.
 */



public class Stats {

    private int livesLeft = 0;

    private int health = 0;
    private int mana = 0;
    private int strength = 0;
    private  int agility = 0;
    private int intellect = 0;
    private int hardiness = 0;
    private int movement = 0;

    private int experience = 0;
    private int expReqLvUp = 0;
    private int level = 0;

    private int maxHealth = 0;
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
        maxHealth = hardiness + level;
        maxMana = intellect + level;

        health = maxHealth;
        mana = maxMana;

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
        maxHealth = hardiness + level;
        maxMana = intellect + level;

        health = maxHealth;
        mana = maxMana;

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
            return (maxHealth);
        }
    public int getMaxMana(){return (maxMana);}
    public int getHealth(){return (health);}
    public int getMana(){
            return (mana);
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

    public void modifymaxHealth(){
        maxHealth = hardiness + level;
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
        modifymaxHealth();
    }
    public void modifyIntellect(int delta){
        intellect = intellect + delta;
        modifyMaxMana();
    }

    public void loseALife(){
        modifyHealth(-maxHealth);
    }

    // Nice wrapper for increasing experience to level up.
    public void levelUp(){
        modifyExperience(expReqLvUp);
    }

    public void modifyLevel(int delta){
        level = level + delta;
        int prizes = delta * 10;
        modifyStrength(prizes);
        modifyAgility(prizes);
        modifyIntellect(prizes);
        modifyHardiness(prizes);
        modifyHealth(maxHealth);
        modifymana(maxMana);
        expReqLvUp = expReqLvUp +100;
        experience = 0;
    }

    // TODO: Test with item that increases exprience
    public void modifyExperience(int delta){
        int stopAtExp =  experience + delta;
        int sign = delta > 0 ? 1 : -1;
        int increment = sign * (expReqLvUp)/100; // keep the exp bar consistant at all levels.
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoudnary = delta > 0 ? experience > stopAtExp : experience < stopAtExp;

                if(!pastBoudnary && experience + increment < expReqLvUp){
                    experience += increment;
                }else{
                    this.cancel();
                    if(experience + increment >= expReqLvUp){
                        modifyLevel(1);
                    }
                }
            }
        };
        Timer timer = new Timer();

        // scheduling the task at fixed rate delay
        int delay = 10;
        timer.scheduleAtFixedRate(tasknew,100,delay);
    }
    //////////////////////////
    public void modifyMovement(int currentSpeed){
        movement = currentSpeed;
    }

    public void modifyHealth(int delta){
        int stopAtHealth = health + delta;
        int sign = delta > 0 ? 1 : -1;
        int increment = sign * (maxHealth)/10;
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoundrary = delta > 0 ? health > stopAtHealth : health < stopAtHealth;

                if(!pastBoundrary && ((health + increment) < maxHealth) && (health > 0)){
                    health += increment;
                }else{
                    // increment is included to keep the bar form overfilling
                    if(health + increment >= maxHealth){
                        health = maxHealth;
                    }
                    if(health == 0){
                        health = maxHealth;
                        modifyLivesLeft(-1);
                    }
                    this.cancel();
                }
            }
        };
        Timer timer = new Timer();

        // scheduling the task at fixed rate delay
        int delay = 75;
        timer.scheduleAtFixedRate(tasknew,100,delay);
    }


    // TODO: Implement the animation for mana!
    public void modifymana(int delta){
        if((mana + delta) <= 0 )//So livesLeft does not drop below zero
        {
            mana = 0;
        }
        else if((health + delta) >= maxMana )//So livesLeft does not go beyond the max
        {
            mana = maxMana;
        }
        else{
            mana = mana + delta;
        }
    }

    public void modifyLivesLeft(int delta){
        livesLeft--;
        if(livesLeft < 1){
            //TODO: Implement GAME
            System.out.println("Game Over");
        }

        health = maxHealth;
    }


}

