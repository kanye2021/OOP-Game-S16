package models;


import utilities.IOMediator;
import views.Display;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ben on 2/2/16.
 */

public class Stats {

    // Primary stats
    private int livesLeft;
    private int strength;
    private int agility;
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

    private TimerTask currentTask;
    private String lastTaskType;

    public static enum Type {

        LIVES("Lives left") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyLivesLeft(delta);}
            public int get(Entity entity) {return entity.getStats().getLivesLeft();}
        },
        /*MAX_HEALTH("Max Health") {
            public void modify(Entity avatar, int delta) {avatar.getStats().modifyH (delta);}
            public int get(Entity avatar) {return avatar.getStats().getHealth();}
        },
        MAX_MANA("Max Mana") {
            public void modify(Entity avatar, int delta) {avatar.getStats().modifyMana(delta);}
            public int get(Entity avatar) {return avatar.getStats().getMana();}
        },*/
        CURRENT_HEALTH("Current Health") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyHealth(delta);}
            public int get(Entity entity) {return entity.getStats().getHealth();}
        },
        CURRENT_MANA("Current Mana") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyMana(delta);}
            public int get(Entity entity) {return entity.getStats().getMana();}
        },
        EXPERIENCE("Experience") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyExperience(delta);}
            public int get(Entity entity) {return entity.getStats().getExperience();}
        },
        MOVEMENT("Movement") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyMovement(delta);}
            public int get(Entity entity) {return entity.getStats().getMovement();}
        },
        LEVEL("Level") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyLevel(delta);}
            public int get(Entity entity) {return entity.getStats().getLevel();}
        },
        STRENGTH("Strength") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyStrength(delta);}
            public int get(Entity entity) {return entity.getStats().getStrength();}
        },
        AGILITY("Agility") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyAgility(delta);}
            public int get(Entity entity) {return entity.getStats().getAgility();}
        },
        INTELLECT("Intellect") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyIntellect(delta);}
            public int get(Entity entity) {return entity.getStats().getIntellect();}
        },
        HARDINESS("Hardiness") {
            public void modify(Entity entity, int delta) {entity.getStats().modifyHardiness(delta);}
            public int get(Entity entity) {return entity.getStats().getHardiness();}
        };

        private String s;

        public abstract void modify(Entity entity, int amount);

        public abstract int get(Entity entity);

        Type(String s) {

            this.s = s;

        }

        public String toString() {

            return s;

        }

    }

    //Loaders for Stats
    // ------ Primary Stats ------
    public void loadLives(int lives) {
        this.livesLeft = lives;
    }

    public void loadStr(int str) {
        strength = str;
    }

    public void loadAgi(int agi) {
        agility = agi;
    }

    public void loadInt(int i) {
        intellect = i;
    }

    public void loadTough(int t) {
        hardiness = t;
    }

    public void loadExp(int e) {
        experience = e;
    }

    public void loadMov(int m) {
        movement = m;
    }

    // ------Derived Stats -----
    public void loadLvl(int i) {
        level = i;
    }

    public void loadHealth(int h) {
        health = h;
    }

    public void loadMana(int m) {
        mana = m;
    }

    public void loadOff(int o) {
        offensiveRating = o;
    }

    public void loadDef(int d) {
        defensiveRating = d;
    }

    public void loadArm(int a) {
        armorRating = a;
    }

    //----Animation stuff
    public void loadMaxHealth(int a) {
        maxHealth = a;
    }

    public void loadMaxMana(int m) {
        maxMana = m;
    }

    public void loadExpReqLvl(int e) {
        expReqLvUp = e;
    }

    public void loadLastLvlExp(int l) {
        lastLvlExpReq = l;
    }

    public void loadWeaponModifier(int w) {
        weaponModifier = w;
    }

    public void loadArmorModifier(int a) {
        armorModifier = a;
    }

    // Getters for primary stats
    public int getLivesLeft() {
        return livesLeft;
    }

    public int getStrength() {
        return (strength);
    }

    public int getAgility() {
        return (agility);
    }

    public int getIntellect() {
        return (intellect);
    }

    public int getHardiness() {
        return (hardiness);
    }

    public int getExperience() {
        return (experience);
    }

    public int getMovement() {
        return (movement);
    }

    // Getteres for derived stats
    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return (health);
    }

    public int getMana() {
        return (mana);
    }

    public int getOffensiveRating() {
        return (offensiveRating);
    }

    public int getDefensiveRating() {
        return (defensiveRating);
    }

    public int getArmorRating() {
        return (armorRating);
    }

    // Getters for other parameters
    public int getMaxHealth() {
        return (maxHealth);
    }

    public int getMaxMana() {
        return (maxMana);
    }

    public int getExpReqLvUp() {
        return (expReqLvUp);
    }

    public int getLastLvlExpReq() {
        return lastLvlExpReq;
    }

    public int getWeaponModifier() {
        return weaponModifier;
    }

    public int getArmorModifier() {
        return armorModifier;
    }


    // Mutator for primary stats
    public void decrementLives() {
        IOMediator.setActiveView(IOMediator.Views.GAME);
        livesLeft--;
        if (livesLeft < 1) {
            //TODO: Implement GAME
            System.out.println("Game Over");
            IOMediator.setActiveView(IOMediator.Views.DEATH);
        }

        health = maxHealth;
        Display.getInstance().repaint();
    }

    public void modifyLivesLeft(int delta) {
        livesLeft += delta;
        Display.getInstance().repaint();
    }

    // Wrapper to animate loosing a life (health bar slides down)
    public void loseALife() {
        modifyHealth(-maxHealth);
        Display.getInstance().repaint();
    }

    public void modifyStrength(int delta) {
        strength = strength + delta;
        updateOffensiveRating();
        Display.getInstance().repaint();
    }

    public void modifyAgility(int delta) {
        agility = agility + delta;
        updateDefensiveRating();
        Display.getInstance().repaint();
    }

    public void modifyIntellect(int delta) {
        intellect = intellect + delta;
        updateMaxMana();
        Display.getInstance().repaint();
    }

    public void modifyHardiness(int delta) {
        hardiness = hardiness + delta;
        updateArmorRating();
        updateMaxHealth();
        Display.getInstance().repaint();
    }

    // TODO: Test with item that increases exprience
    public void modifyExperience(final int delta) {
        // Increases experience gradually so it "fills" the bar
        final int stopAtExp = experience + delta;
        int sign = delta > 0 ? 1 : -1;
        final int increment = sign * (expReqLvUp - lastLvlExpReq) / 100; // keep the exp bar consistant at all levels.

        // Terminate any existing tasks
        if (currentTask != null && lastTaskType.equals("modifyExperience"))
            currentTask.cancel();


        lastTaskType = "modifyExperience";
        currentTask = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoudnary = delta > 0 ? experience > stopAtExp : experience < stopAtExp;

                if (!pastBoudnary && experience + increment < expReqLvUp) {
                    experience += increment;
                    Display.getInstance().repaint();
                } else {
                    if (experience + increment >= expReqLvUp) {
                        modifyLevel(1);
                    }
                    Display.getInstance().repaint();
                    this.cancel();
                    lastTaskType = "";
                }
            }
        };
        Timer timer = new Timer();

        // scheduling the task at fixed rate delay
        int delay = 10;
        timer.scheduleAtFixedRate(currentTask, 100, delay);
    }

    public void modifyMovement(int delta) {

        movement = movement + delta;
        movement = Math.max(movement, 0);
        Display.getInstance().repaint();
    }

    // Mutators for derived stats

    // Wrapper for modify level 
    public void levelUp() {

        modifyExperience(expReqLvUp);
        Display.getInstance().repaint();
    }

    public void modifyLevel(int delta) {
        level = level + delta;
        int prizes = delta * 10;
        modifyStrength(prizes);
        modifyAgility(prizes);
        modifyIntellect(prizes);
        modifyHardiness(prizes);
        modifyMovement(prizes);
        modifyHealth(maxHealth);
        modifyMana(maxMana);
        lastLvlExpReq = expReqLvUp;
        expReqLvUp = expReqLvUp + 100;
        experience = lastLvlExpReq;
        Display.getInstance().repaint();
    }


    public void modifyHealth(final int delta) {
        final int stopAtHealth = health + delta;
        int sign = delta > 0 ? 1 : -1;
        final int increment = sign * (maxHealth) / 10;

        // Terminate any existing tasks
        if (currentTask != null && lastTaskType.equals("modifyHealth")) {
            currentTask.cancel();
        }


        lastTaskType = "modifyHealth";
        currentTask = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoundrary = delta > 0 ? health > stopAtHealth : health < stopAtHealth;

                if (!pastBoundrary && ((health + increment) < maxHealth) && ((health + increment) > 0)) {
                    health += increment;
                    Display.getInstance().repaint();
                } else {
                    // increment is included to keep the bar form overfilling
                    if (health + increment >= maxHealth) {
                        health = maxHealth;
                    }
                    if (health + increment <= 0) {
                        health = maxHealth;
                        decrementLives();
                    }
                    Display.getInstance().repaint();
                    this.cancel();
                    lastTaskType = "";
                }
            }
        };
        Timer timer = new Timer();

        // scheduling the task at fixed rate delay
        int delay = 75;
        timer.scheduleAtFixedRate(currentTask, 100, delay);
    }

    // TODO: Test this with something that modifys mana.
    public void modifyMana(final int delta) {
        final int stopAtMana = mana + delta;
        int sign = delta > 0 ? 1 : -1;
        final int increment = sign * (maxMana) / 10;

        // Terminate any existing tasks
        if (currentTask != null && lastTaskType.equals("modifyMana")) {
            currentTask.cancel();
        }


        lastTaskType = "modifyMana";
        currentTask = new TimerTask() {
            @Override
            public void run() {
                boolean pastBoundrary = delta > 0 ? mana > stopAtMana : mana < stopAtMana;

                if (!pastBoundrary && ((mana + increment) < maxMana) && ((mana + increment) > 0)) {
                    mana += increment;
                    Display.getInstance().repaint();
                } else {
                    // increment is included to keep the bar form overfilling
                    if (mana + increment >= maxMana) {
                        mana = maxMana;
                    }
                    if (mana + increment <= 0) {
                        mana = 0;
                    }

                    Display.getInstance().repaint();
                    this.cancel();
                    lastTaskType = "";
                }
            }
        };
        Timer timer = new Timer();

        // scheduling the task at fixed rate delay
        int delay = 75;
        timer.scheduleAtFixedRate(currentTask, 100, delay);
    }

    //derived status or ones that require a formula to calculate
    public void updateOffensiveRating() {

        offensiveRating = weaponModifier + strength + level;
        Display.getInstance().repaint();
    }

    public void updateDefensiveRating() {

        defensiveRating = agility + level;
        Display.getInstance().repaint();
    }

    public void updateArmorRating() {

        armorRating = armorModifier + hardiness;
        Display.getInstance().repaint();
    }


    // Mutators for other parameters
    public void updateMaxHealth() {

        maxHealth = hardiness + level;
        Display.getInstance().repaint();
    }

    public void updateMaxMana() {

        maxMana = intellect + level;
        Display.getInstance().repaint();
    }

    public void setWeaponModifier(int modifier) {

        weaponModifier = modifier;
        Display.getInstance().repaint();
    }

    public void setArmorModifier(int modifier) {

        armorModifier = modifier;
        Display.getInstance().repaint();
    }
}

