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
    private final int AREA_WIDTH = View.B_WIDTH;
    private final int AREA_HEIGHT = View.B_HEIGHT * 1/4;
    private final int OFFSET_LEFT = 20;
    private final int OFFSET_TOP = View.B_HEIGHT*3/4 + 8;
    private  String resourceBasePath = "./src/res/etc/";
    private Font font;

    public StatusViewport(Entity entity){

        this.entity = entity;
        font = new Font ("Courier New", 1, 24);
        resourceBasePath = resourceBasePath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));

    }

    @Override
    public void render(Graphics g){
        Stats stats = entity.getStats();

        // Get the new stats
        String level = "Level: " + stats.getLevel();
        int experience = stats.getExperience();
        int maxExp = stats.getExpReqLvUp();
        int health = stats.getHealth();
        int maxHealth = stats.getMaxHealth();
        int mana = stats.getMana();
        int maxMana = stats.getMaxMana();
        int lives = stats.getLivesLeft();


        // Put the background for the viewport
        FontMetrics fm = g.getFontMetrics(font);
        g.setColor(new Color(32, 32, 32));
        g.fillRect(0, View.B_HEIGHT*3/4, View.B_WIDTH, View.B_HEIGHT*1/4);


        // Setup the font
        g.setColor(Color.WHITE);
        g.setFont(font);


        // Display the avatar's level
        g.drawString(level, OFFSET_LEFT, OFFSET_TOP + font.getSize());

        // Display the lives
        g.drawString("Lives: ", View.B_WIDTH * 3/4 + OFFSET_LEFT, OFFSET_TOP+ font.getSize());

        // Get the bounding rect of the lives string.
        Rectangle2D livesRect = fm.getStringBounds("Lives: ", g);
        int livesWidth = (int) livesRect.getWidth();

        // Load the heart image
        ImageIcon lifeIcon = new ImageIcon(resourceBasePath + "life-heart.png");
        Image lifeImg = lifeIcon.getImage();

        int marginBtwnHearts = 10;
        int x = View.B_WIDTH * 3/4 + OFFSET_LEFT + livesWidth;
        for(int i=0; i<lives; i++){
            g.drawImage(lifeImg, x, OFFSET_TOP, Display.getInstance());
            x += lifeImg.getWidth(null) + marginBtwnHearts;
        }

        // Display the Health
        int healthY = OFFSET_TOP + font.getSize() + 5;
        g.drawString("Health: ", OFFSET_LEFT, healthY  + (int) livesRect.getHeight());
        Rectangle2D healthRect = fm.getStringBounds("Health: ", g);
        g.setColor(Color.LIGHT_GRAY);
        int healthBarX = (int) (OFFSET_LEFT + healthRect.getWidth());
        int healthBarHeight = (int)  healthRect.getHeight();
        int healthBarWidth = B_WIDTH*1/4;


        double healthFraction = (double) health/ (double) maxHealth;

        g.drawRect(healthBarX, healthY + 5, healthBarWidth, healthBarHeight);
        g.setColor(Color.RED);
        g.fillRect(healthBarX, healthY + 5, (int) (healthBarWidth*healthFraction), healthBarHeight);
        System.out.println("HEALTH: " + health + " / " + maxHealth + "IS: " +  healthFraction + " " + healthFraction * healthBarWidth);






//        int x = 10;
//        int y = 50;
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillRect(0, View.B_HEIGHT*3/4, View.B_WIDTH, View.B_HEIGHT*1/4);
//        g.setColor(Color.WHITE);
//
//        g.drawString("Level: ", x, View.B_HEIGHT*3/4 + y - 20);
//        g.drawString(Integer.toString(stats.getLevel()), x+50, View.B_HEIGHT*3/4 + y - 20);
//
//        g.drawString("Exp: ", x, View.B_HEIGHT*3/4 + y);
//        g.drawString(Integer.toString(stats.getExperience()), x+50, View.B_HEIGHT*3/4 + y);
//        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y);
//        g.drawString(Integer.toString(stats.getExpReqLvUp()), x+80, View.B_HEIGHT*3/4 + y);
//
//
//        g.drawString("Health", x, View.B_HEIGHT*3/4 + y+20);
//        g.drawString(Integer.toString(stats.getHealth()), x+50, View.B_HEIGHT*3/4 + y+20);
//        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+20);
//        g.drawString(Integer.toString(stats.getMaxHealth()), x+80, View.B_HEIGHT*3/4+ y+ 20);
//
//        g.drawString("Mana", x, View.B_HEIGHT*3/4 + y+40);
//        g.drawString(Integer.toString(stats.getMana()), x+50, View.B_HEIGHT*3/4 + y+40);
//        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+40);
//        g.drawString(Integer.toString(stats.getMaxMana()), x+80, View.B_HEIGHT*3/4 + y+40);
//
//        g.drawString("Lives: ", x, View.B_HEIGHT*3/4 + y+60);
//        g.drawString(Integer.toString(stats.getLivesLeft()), x+50, View.B_HEIGHT*3/4 + y+60);
//
//
//        g.drawString("Strength", x+120, View.B_HEIGHT*3/4 + y-20);
//        g.drawString(Integer.toString(stats.getStrength()), x+195, View.B_HEIGHT*3/4 + y-20);
//
//        g.drawString("Hardiness", x+120, View.B_HEIGHT*3/4 + y+0);
//        g.drawString(Integer.toString(stats.getHardiness()), x+195, View.B_HEIGHT*3/4 + y+0);
//
//        g.drawString("Agility", x+120, View.B_HEIGHT*3/4 + y+20);
//        g.drawString(Integer.toString(stats.getAgility()), x+195, View.B_HEIGHT*3/4 + y+20);
//
//        g.drawString("Intellect", x+120, View.B_HEIGHT*3/4 + y+40);
//        g.drawString(Integer.toString(stats.getIntellect()), x+195, View.B_HEIGHT*3/4 + y+40);
//
//        g.drawString("Offensive Rating", x+240, View.B_HEIGHT*3/4 + y-20);
//        g.drawString(Integer.toString(stats.getOffensiveRating()), x+360, View.B_HEIGHT*3/4 + y-20);
//
//        g.drawString("Defensive Rating", x+240, View.B_HEIGHT*3/4 + y+0);
//        g.drawString(Integer.toString(stats.getDefensiveRating()), x+360, View.B_HEIGHT*3/4 + y+0);
//
//        g.drawString("Armor Rating", x+240, View.B_HEIGHT*3/4 + y+20);
//        g.drawString(Integer.toString(stats.getArmorRating()), x+360, View.B_HEIGHT*3/4 + y+20);
//
//        g.drawString("Movement", x+240, View.B_HEIGHT*3/4 + y+40);
//        g.drawString(Integer.toString(stats.getMovement()), x+360, View.B_HEIGHT*3/4 + y+40);
    }
}
