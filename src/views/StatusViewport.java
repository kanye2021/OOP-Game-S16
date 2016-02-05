package views;

import models.Entity;
import models.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bradley on 2/3/16.
 */
public class StatusViewport extends View {
    private Entity entity;
    // Constants representing the viepwport dimensions.
    private final int AREA_WIDTH = View.B_WIDTH;
    private final int AREA_HEIGHT = View.B_HEIGHT * 1 / 4;

    private final int OFFSET_LEFT = 20;
    private final int OFFSET_TOP = View.B_HEIGHT * 3 / 4 + 8;
    private String resourceBasePath = "./src/res/etc/";
    private Font font;
    private FontMetrics fm;

    // Stats to display
    String level;
    int exp;
    int expToNextLvl;

    int health;
    int maxHealth;

    int mana;
    int maxMana;

    int lives;


    public StatusViewport(Entity entity) {

        this.entity = entity;
        font = new Font("Courier New", 1, 24);
        resourceBasePath = resourceBasePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));

    }


    @Override
    public void render(Graphics g) {
        Stats stats = entity.getStats();

        // Get the new stats
        level = "Level: " + stats.getLevel();
        exp = stats.getExperience();
        expToNextLvl = stats.getExpReqLvUp();
        health = stats.getHealth();
        maxHealth = stats.getMaxHealth();
        mana = stats.getMana();
        maxMana = stats.getMaxMana();
        lives = stats.getLivesLeft();


        // Display a gray background.
        fm = g.getFontMetrics(font);
        g.setColor(new Color(32, 32, 32));
        g.fillRect(0, View.B_HEIGHT * 3 / 4, View.B_WIDTH, View.B_HEIGHT * 1 / 4);


        // Setup the font
        g.setFont(font);


        // Display the entity's level
        drawLevel(g);

        // Draw the entity's lives.
        drawLives(g);

        // Draw the health bar
        drawHealthBar(g);

        // Draw the mana bar
        drawManaBar(g);

        // Draw the experience bar
        drawExperienceBar(g);
    }

    private void drawLevel(Graphics g){
        int levelX = OFFSET_LEFT;
        int levelY = OFFSET_TOP + font.getSize();
        g.setColor(Color.WHITE);
        g.drawString(level, levelX, levelY);
    }

    private void drawLives(Graphics g){
        // Display the Lives text
        int livesX = View.B_WIDTH * 3/4 + OFFSET_LEFT;
        int livesY = OFFSET_TOP+ font.getSize();

        g.setColor(Color.WHITE);
        g.drawString("Lives: ", livesX, livesY);

        // Get the bounding rect of the lives string.
        Rectangle2D livesRect = fm.getStringBounds("Lives: ", g);
        int widthOfLivesText = (int) livesRect.getWidth();

        // Load the heart image
        ImageIcon lifeIcon = new ImageIcon(resourceBasePath + "life-heart.png");
        Image lifeImg = lifeIcon.getImage();

        // Set the spacing between hearts
        int marginBtwnHearts = 10;

        int heartsX = livesX + widthOfLivesText;
        int heartsY = OFFSET_TOP;
        for(int i=0; i<lives; i++){
            g.drawImage(lifeImg, heartsX, heartsY, Display.getInstance());
            heartsX += lifeImg.getWidth(null) + marginBtwnHearts;
        }
    }

    private void drawHealthBar(Graphics g){
        // Determine how large text is and where to place the Health string
        Rectangle2D healthRect = fm.getStringBounds("EXP: ", g);
        int healthX = B_WIDTH * 1/4 - 45;
        int healthY = OFFSET_TOP + font.getSize()  + 5 * (int) healthRect.getHeight() - 10;

        // Draw the health string.
        g.setColor(Color.WHITE);
        g.drawString("HP: ", healthX, healthY );


        // Set the location and size of the health bar.
        int healthBarX = (int) (healthX + healthRect.getWidth());
        int healthBarY = healthY - (int)healthRect.getHeight() + 8;
        int healthBarHeight = (int) healthRect.getHeight() - 5;
        int healthBarWidth = B_WIDTH*1/4 - 35;

        // Draw the outline of the health bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);


        // Determine what fraction of the healthbar should be shown.
        double healthFraction = (double) health/ (double) maxHealth;
        healthBarWidth = (int) (healthFraction * healthBarWidth);

        // Fill the healthbar
        g.setColor(Color.RED);
        g.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
    }

    private void drawManaBar(Graphics g){

        // Determine where to place the mana string.
        Rectangle2D manaRect = fm.getStringBounds("MP: ", g);
        int manaX = B_WIDTH /2 + 3;
        int manaY = OFFSET_TOP + font.getSize()  + 5 * (int) manaRect.getHeight() - 10;

        // Display the Mana text
        g.setColor(Color.WHITE);
        g.drawString("MP: ", manaX, manaY );


        // Set the location and size of the mana bar.
        int manaBarX = (int) (manaX + manaRect.getWidth());
        int manaBarY = manaY - (int)manaRect.getHeight() + 8;
        int manaBarHeight = (int) manaRect.getHeight() - 5;
        int manaBarWidth = B_WIDTH*1/4 - 35;

        // Draw the outline of the mana bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(manaBarX, manaBarY, manaBarWidth, manaBarHeight);


        // Determine what fraction of the mana bar should be shown.
        double manaFraction = (double) mana/ (double) maxMana;
        manaBarWidth = (int) (manaFraction * manaBarWidth);

        // Fill the mana bar
        g.setColor(Color.BLUE);
        g.fillRect(manaBarX, manaBarY, manaBarWidth, manaBarHeight);
    }

    private void drawExperienceBar(Graphics g){

        // Determine where to place the exp string.
        Rectangle2D expRect = fm.getStringBounds("EXP: ", g);
        int expX = B_WIDTH * 1/4 - 45;
        int expY = OFFSET_TOP + font.getSize()  + 6 * (int) expRect.getHeight() - 5;
//
        // Display the exp text
        g.setColor(Color.WHITE);
        g.drawString("EXP: ", expX, expY );

////
        // Set the location and size of the exp bar.
        // A little trick to align the exp bar with the health bar (Since health is a longer word.)
        Rectangle2D healthRect = fm.getStringBounds("EXP: ", g);
//
        int expBarX = (int) (expX + healthRect.getWidth());
        int expBarY = expY - (int)expRect.getHeight() + 8;
        int expBarHeight = (int) expRect.getHeight() - 5;
        int expBarWidth = B_WIDTH*1/2;
//
        // Draw the outline of the exp bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(expBarX, expBarY, expBarWidth, expBarHeight);
//
//
        // Determine what fraction of the exp bar should be shown.
        double expFraction = (double) exp/ (double) expToNextLvl;
        expBarWidth = (int) (expFraction * expBarWidth);

        // Fill the exp bar
        g.setColor(Color.YELLOW);
        g.fillRect(expBarX, expBarY, expBarWidth, expBarHeight);
    }
}



//
//
//
////        int x = 10;
////        int y = 50;
////        g.setColor(Color.LIGHT_GRAY);
////        g.fillRect(0, View.B_HEIGHT*3/4, View.B_WIDTH, View.B_HEIGHT*1/4);
////        g.setColor(Color.WHITE);
////
////        g.drawString("Level: ", x, View.B_HEIGHT*3/4 + y - 20);
////        g.drawString(Integer.toString(stats.getLevel()), x+50, View.B_HEIGHT*3/4 + y - 20);
////
////        g.drawString("Exp: ", x, View.B_HEIGHT*3/4 + y);
////        g.drawString(Integer.toString(stats.getExperience()), x+50, View.B_HEIGHT*3/4 + y);
////        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y);
////        g.drawString(Integer.toString(stats.getExpReqLvUp()), x+80, View.B_HEIGHT*3/4 + y);
////
////
////        g.drawString("Health", x, View.B_HEIGHT*3/4 + y+20);
////        g.drawString(Integer.toString(stats.getHealth()), x+50, View.B_HEIGHT*3/4 + y+20);
////        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+20);
////        g.drawString(Integer.toString(stats.getMaxHealth()), x+80, View.B_HEIGHT*3/4+ y+ 20);
////
////        g.drawString("Mana", x, View.B_HEIGHT*3/4 + y+40);
////        g.drawString(Integer.toString(stats.getMana()), x+50, View.B_HEIGHT*3/4 + y+40);
////        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+40);
////        g.drawString(Integer.toString(stats.getMaxMana()), x+80, View.B_HEIGHT*3/4 + y+40);
////
////        g.drawString("Lives: ", x, View.B_HEIGHT*3/4 + y+60);
////        g.drawString(Integer.toString(stats.getLivesLeft()), x+50, View.B_HEIGHT*3/4 + y+60);
////
////
////        g.drawString("Strength", x+120, View.B_HEIGHT*3/4 + y-20);
////        g.drawString(Integer.toString(stats.getStrength()), x+195, View.B_HEIGHT*3/4 + y-20);
////
////        g.drawString("Hardiness", x+120, View.B_HEIGHT*3/4 + y+0);
////        g.drawString(Integer.toString(stats.getHardiness()), x+195, View.B_HEIGHT*3/4 + y+0);
////
////        g.drawString("Agility", x+120, View.B_HEIGHT*3/4 + y+20);
////        g.drawString(Integer.toString(stats.getAgility()), x+195, View.B_HEIGHT*3/4 + y+20);
////
////        g.drawString("Intellect", x+120, View.B_HEIGHT*3/4 + y+40);
////        g.drawString(Integer.toString(stats.getIntellect()), x+195, View.B_HEIGHT*3/4 + y+40);
////
////        g.drawString("Offensive Rating", x+240, View.B_HEIGHT*3/4 + y-20);
////        g.drawString(Integer.toString(stats.getOffensiveRating()), x+360, View.B_HEIGHT*3/4 + y-20);
////
////        g.drawString("Defensive Rating", x+240, View.B_HEIGHT*3/4 + y+0);
////        g.drawString(Integer.toString(stats.getDefensiveRating()), x+360, View.B_HEIGHT*3/4 + y+0);
////
////        g.drawString("Armor Rating", x+240, View.B_HEIGHT*3/4 + y+20);
////        g.drawString(Integer.toString(stats.getArmorRating()), x+360, View.B_HEIGHT*3/4 + y+20);
////
////        g.drawString("Movement", x+240, View.B_HEIGHT*3/4 + y+40);
////        g.drawString(Integer.toString(stats.getMovement()), x+360, View.B_HEIGHT*3/4 + y+40);
//    }
//}
