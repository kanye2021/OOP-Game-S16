
package models;


import java.util.*;
import java.util.TimerTask;

/**
 * Created by ben on 2/2/16.
 */



public class Stats {
    
    // Primary stats
    private int livesLeft;
    private int strength;
    private  int agility;
    private int intellect;
    private int hardiness;
    private int experience;
    private int movement;
    
    // Derived stats
    private int level;
    private int health; // Is life in the requirements (got confusing with life and livesLeft
    private int mana;
    private int offensiveRating;
    private int defensiveRating;
    private int armorRating;
    
    // Other useful parameters
    private int expReqLvUp;
    private int lastLvlExpReq;
    private int maxHealth;
    private int maxMana;
    private int weaponModifier;
    private int armorModifier;


    public Stats(){
        
        // Init primary stats
        livesLeft = 3;
        strength = 10;
        agility = 12;
        intellect = 10;
        hardiness = 10;
        experience = 0;
        movement = 10;
        
        // Set up necessary parameters
        maxHealth = hardiness + level;
        maxMana = intellect + level;
        weaponModifier = 0;
        armorModifier = 0;
        expReqLvUp = 100;
        lastLvlExpReq = 0;


        // Derived stats
        level = 1;
        health = maxHealth;
        mana = maxMana;
        offensiveRating = weaponModifier + strength + level;
        defensiveRating = agility + level;
        armorRating = armorModifier + hardiness;

    }
    public Stats(String occupation){//Initializes the original stats from occupation

        // Set up the stats that are not dependent on occupation
        livesLeft = 3;
        experience = 0;
        experience = 0;
        movement = 10;

        if(occupation == "smasher") {
            strength = 12;
            agility = 10;
            intellect = 8;
            hardiness = 10;
        }
        else if(occupation == "summoner"){
            strength = 10;
            agility = 8;
            intellect = 12;
            hardiness = 10;
        }
        else if(occupation == "sneak"){
            strength = 10;
            agility = 12;
            intellect = 10;
            hardiness = 10;
        }

        // Set up necessary parameters
        maxHealth = hardiness + level;
        maxMana = intellect + level;
        weaponModifier = 0;
        armorModifier = 0;
        expReqLvUp = 100;
        lastLvlExpReq = 0;

        // Derived stats
        level = 1;
        health = maxHealth;
        mana = maxMana;
        offensiveRating = weaponModifier + strength + level;
        defensiveRating = agility + level;
        armorRating = armorModifier + hardiness;
    }


    // Getters for primary stats
    public int getLivesLeft(){return livesLeft;}
    public int getStrength(){return (strength);}
    public int getAgility(){return (agility);}
    public int getIntellect(){return (intellect);}
    public int getHardiness(){return (hardiness);}
    public int getExperience(){return (experience);}
    public int getMovement(){return (movement);}

    // Getteres for derived stats
    public int getLevel(){
            return level;
        }
    public int getHealth(){return (health);}
    public int getMana(){
        return (mana);
    }
    public int getOffensiveRating(){return (offensiveRating);}
    public int getDefensiveRating(){return (defensiveRating);}
    public int getArmorRating(){return (armorRating);}

    // Getters for other parameters
    public int getMaxHealth(){
            return (maxHealth);
        }
    public int getMaxMana(){return (maxMana);}
    public int getExpReqLvUp(){return (expReqLvUp);}
    public int getLastLvlExpReq(){return lastLvlExpReq; }
    public int getWeaponModifier(){return weaponModifier; }
    public int getArmorModifier(){ return armorModifier; }

    
    // Mutator for primary stats
    public void modifyLivesLeft(int delta){
        livesLeft--;
        if(livesLeft < 1){
            //TODO: Implement GAME
            System.out.println("Game Over");
        }

        health = maxHealth;
    }
    // Wrapper to animate loosing a life (health bar slides down)
    public void loseALife(){
        modifyHealth(-maxHealth);
    }
    
    public void modifyStrength(int delta){
        strength = strength + delta;
        updateOffensiveRating();
    }
    
    public void modifyAgility(int delta){
        agility = agility +delta;
        updateDefensiveRating();
    }
    
    public void modifyIntellect(int delta){
        intellect = intellect + delta;
        updateMaxMana();
    }
    
    public void modifyHardiness(int delta){
        hardiness = hardiness + delta;
        updateArmorRating();
        updateMaxHealth();
    }
    
    // TODO: Test with item that increases exprience
    public void modifyExperience(int delta){
        // Increases experience gradually so it "fills" the bar
        int stopAtExp =  experience + delta;
        int sign = delta > 0 ? 1 : -1;
        int increment = sign * (expReqLvUp - lastLvlExpReq)/100; // keep the exp bar consistant at all levels.
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
    
    public void modifyMovement(int delta){
        
        movement = movement + delta;
    }
    
    // Mutators for derived stats
    
    // Wrapper for modify level 
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
        modifyMovement(prizes);
        modifyHealth(maxHealth);
        modifymana(maxMana);
        lastLvlExpReq = expReqLvUp;
        expReqLvUp = expReqLvUp +100;
        experience = lastLvlExpReq;
    }
    

    public void modifyHealth(int delta){
        int stopAtHealth = health + delta;
        int sign = delta > 0 ? 1 : -1;
        int increment = sign * (maxHealth)/10;
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoundrary = delta > 0 ? health > stopAtHealth : health < stopAtHealth;

                if(!pastBoundrary && ((health + increment) < maxHealth) && ((health + increment) > 0)){
                    health += increment;
                }else{
                    // increment is included to keep the bar form overfilling
                    if(health + increment >= maxHealth){
                        health = maxHealth;
                    }
                    if(health + increment <= 0){
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

    // TODO: Test this with something that modifys mana.
    public void modifymana(int delta){
        int stopAtMana = mana + delta;
        int sign = delta > 0 ? 1 : -1;
        int increment = sign * (maxMana)/10;
        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoundrary = delta > 0 ? mana > stopAtMana : mana < stopAtMana;

                if(!pastBoundrary && ((mana + increment) < maxMana) && ((mana + increment) > 0)){
                    mana += increment;
                }else{
                    // increment is included to keep the bar form overfilling
                    if(mana + increment >= maxMana){
                        mana = maxMana;
                    }
                    if(mana + increment <= 0){
                        mana = 0;
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

    //derived status or ones that require a formula to calculate
    public void updateOffensiveRating(){
        offensiveRating = weaponModifier + strength + level;
    }

    public void updateDefensiveRating(){
        defensiveRating = agility + level;
    }

    public void updateArmorRating(){
        armorRating = armorModifier + hardiness;
    }

    
    // Mutators for other parameters

    
    public void updateMaxHealth(){
        maxHealth = hardiness + level;
    }

    public void updateMaxMana(){
        maxMana = intellect + level;
    }

    public void setWeaponModifier(int modifier){
        weaponModifier = modifier;
    }

    public void setArmorModifier(int modifier){
        armorModifier = modifier;
    }
}

