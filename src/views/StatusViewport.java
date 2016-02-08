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
    private final int MARGIN_HORIZONTAL = 20;
    private final int MARGIN_VERTICAL = View.B_HEIGHT * 4 / 5 + 8;
    private String resourceBasePath = "./src/res/etc/";
    private Font font;
    private Font smallFont;
    private FontMetrics fm;
    private int borderRadius;

    public StatusViewport(Entity entity) {
        this.entity = entity;
        font = new Font("Courier New", 1, 24);
        smallFont = new Font("Courier New", 1, 18);
        resourceBasePath = resourceBasePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        borderRadius = 10;
    }

    @Override
    public void render(Graphics g) {
        Stats stats = entity.getStats();

        // Display a gray background.
        fm = g.getFontMetrics(font);
        g.setColor(new Color(32, 32, 32));
        g.fillRect(0, View.B_HEIGHT * 4 / 5, View.B_WIDTH, View.B_HEIGHT * 1 / 5);

        // Display the entity's level
        drawLevel(g, stats);

        // Draw the entity's lives.
        drawLives(g, stats);

        // Draw the health bar
        drawHealthBar(g, stats);

        // Draw the mana bar
        drawManaBar(g, stats);

        // Draw the experience bar
        drawExperienceBar(g, stats);

        // Draw the strength,
        drawCenteredStats(g, stats);
    }

    private void drawLevel(Graphics g, Stats stats) {
        // Get the necessary stats
        String level = "Level: " + stats.getLevel();

        // Set the font
        g.setFont(font);

        // Determine the size the string takes up
        Rectangle2D levelRect = fm.getStringBounds(level, g);


        // Display the Lives text
        int levelX = View.B_WIDTH - (int) levelRect.getWidth() - MARGIN_HORIZONTAL;
        int levelY = MARGIN_VERTICAL + font.getSize();

        g.setColor(Color.WHITE);
        g.drawString(level, levelX, levelY);
    }

    private void drawLives(Graphics g, Stats stats) {
        // Get the necessary stats
        int lives = stats.getLivesLeft();

        // Set the font
        g.setFont(font);

        int livesX = MARGIN_HORIZONTAL;
        int livesY = MARGIN_VERTICAL + font.getSize();

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
        int heartsY = livesY - font.getSize();
        for (int i = 0; i < lives; i++) {
            g.drawImage(lifeImg, heartsX, heartsY, Display.getInstance());
            heartsX += lifeImg.getWidth(null) + marginBtwnHearts;
        }
    }

    private void drawHealthBar(Graphics g, Stats stats) {

        // Get the necessary stats
        int health = stats.getHealth();
        int maxHealth = stats.getMaxHealth();

        // Set the font
        g.setFont(font);

        // Determine how large text is and where to place the Health string
        Rectangle2D healthRect = fm.getStringBounds("EXP: ", g);
        int healthX = B_WIDTH * 1 / 4 - 45;
        int healthY = B_HEIGHT - (int) healthRect.getHeight() - 10;

        // Draw the health string.
        g.setColor(Color.WHITE);
        g.drawString("HP: ", healthX, healthY);


        // Set the location and size of the health bar.
        int healthBarX = (int) (healthX + healthRect.getWidth());
        int healthBarY = healthY - (int) healthRect.getHeight() + 8;
        int healthBarHeight = (int) healthRect.getHeight() - 5;
        int healthBarWidth = B_WIDTH * 1 / 4 - 35;

        // Draw the outline of the health bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight, borderRadius, borderRadius);


        // Determine what fraction of the healthbar should be shown.
        double healthFraction = (double) health / (double) maxHealth;
        int fillWidth = (int) (healthFraction * healthBarWidth);

        // Fill the healthbar
        g.setColor(Color.RED);
        g.fillRoundRect(healthBarX, healthBarY, fillWidth, healthBarHeight, borderRadius, borderRadius);

        // Display the fraction of health
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        String fraction = "(" + health + "/" + maxHealth + ")";

        // Place the font at the right of the bar
        Rectangle2D fractionRect = fm.getStringBounds(fraction, g);

        int fractionX = healthBarX + healthBarWidth - (int) fractionRect.getWidth() + 15;
        int fractionY = healthY - 2;
        g.drawString(fraction, fractionX, fractionY);
    }

    private void drawManaBar(Graphics g, Stats stats) {

        // Get the ncessary stats
        int mana = stats.getMana();
        int maxMana = stats.getMaxMana();

        // Set the font
        g.setFont(font);

        // Determine where to place the mana string.
        Rectangle2D manaRect = fm.getStringBounds("MP: ", g);
        int manaX = B_WIDTH / 2 + 3;
        int manaY = B_HEIGHT - (int) manaRect.getHeight() - 10;

        // Display the Mana text
        g.setColor(Color.WHITE);
        g.drawString("MP: ", manaX, manaY);


        // Set the location and size of the mana bar.
        int manaBarX = (int) (manaX + manaRect.getWidth());
        int manaBarY = manaY - (int) manaRect.getHeight() + 8;
        int manaBarHeight = (int) manaRect.getHeight() - 5;
        int manaBarWidth = B_WIDTH * 1 / 4 - 35;

        // Draw the outline of the mana bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(manaBarX, manaBarY, manaBarWidth, manaBarHeight, borderRadius, borderRadius);


        // Determine what fraction of the mana bar should be shown.
        double manaFraction = (double) mana / (double) maxMana;
        int fillWidth = (int) (manaFraction * manaBarWidth);

        // Fill the mana bar
        g.setColor(Color.BLUE);
        g.fillRoundRect(manaBarX, manaBarY, fillWidth, manaBarHeight, borderRadius, borderRadius);

        // Display the fraction of mana
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        String fraction = "(" + mana + "/" + maxMana + ")";

        // Place the font at the right of the bar
        Rectangle2D fractionRect = fm.getStringBounds(fraction, g);

        int fractionX = manaBarX + manaBarWidth - (int) fractionRect.getWidth() + 10;
        int fractionY = manaY - 2;
        g.drawString(fraction, fractionX, fractionY);
    }

    private void drawExperienceBar(Graphics g, Stats stats) {

        // Get the necessary stats
        int exp = stats.getExperience();
        int expToNextLvl = stats.getExpReqLvUp();
        int lastLvlExpReq = stats.getLastLvlExpReq();

        // Set the font
        g.setFont(font);

        // Determine where to place the exp string.
        Rectangle2D expRect = fm.getStringBounds("EXP: ", g);
        int expX = B_WIDTH * 1 / 4 - 45;
        int expY = B_HEIGHT - 10;

        // Display the exp text
        g.setColor(Color.WHITE);
        g.drawString("EXP: ", expX, expY);


        // Set the location and size of the exp bar.
        // A little trick to align the exp bar with the health bar (Since health is a longer word.)
        Rectangle2D healthRect = fm.getStringBounds("EXP: ", g);

        int expBarX = (int) (expX + healthRect.getWidth());
        int expBarY = expY - (int) expRect.getHeight() + 8;
        int expBarHeight = (int) expRect.getHeight() - 5;
        int expBarWidth = B_WIDTH * 1 / 2;

        // Draw the outline of the exp bar.
        g.setColor(Color.LIGHT_GRAY);
        g.drawRoundRect(expBarX, expBarY, expBarWidth, expBarHeight, borderRadius, borderRadius);

        // Determine what fraction of the exp bar should be shown.
        double expFraction = (double) (exp - lastLvlExpReq) / (double) (expToNextLvl - lastLvlExpReq);
        int fillWidth = (int) (expFraction * expBarWidth);

        // Fill the exp bar
        g.setColor(Color.YELLOW);
        g.fillRoundRect(expBarX, expBarY, fillWidth, expBarHeight, borderRadius, borderRadius);

        // Display the fraction of experience
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        String fraction = "(" + exp + "/" + expToNextLvl + ")";

        // Place the font at the right of the bar
        Rectangle2D fractionRect = fm.getStringBounds(fraction, g);

        int fractionX = expBarX + expBarWidth - (int) fractionRect.getWidth() + 15;
        int fractionY = expY - 2;
        g.drawString(fraction, fractionX, fractionY);

    }

    // Displays strength agility intellect hardines
    private void drawCenteredStats(Graphics g, Stats stats) {
        // Get the ncessary stats
        String strength = "Strength: " + stats.getStrength();
        String agility = "Agility: " + stats.getAgility();
        String intellect = "Intellect: " + stats.getIntellect();
        String hardiness = "Hardiness: " + stats.getHardiness();
        String offensiveRating = "Offense: " + stats.getOffensiveRating();
        String defensiveRating = "Defense: " + stats.getDefensiveRating();
        String armorRating = "Armor: " + stats.getArmorRating();
        String movement = "Speed: " + stats.getMovement();


        // Set the font
        g.setFont(smallFont);

        // Setup the rects for positioning
        Rectangle2D strengthRect = fm.getStringBounds(strength, g);
        Rectangle2D agilityRect = fm.getStringBounds(agility, g);
        Rectangle2D intellectRect = fm.getStringBounds(intellect, g);
        Rectangle2D hardinessRect = fm.getStringBounds(hardiness, g);
        Rectangle2D offensiveRect = fm.getStringBounds(offensiveRating, g);
        Rectangle2D defensiveRect = fm.getStringBounds(defensiveRating, g);
        Rectangle2D armorRect = fm.getStringBounds(armorRating, g);
        Rectangle2D movementRect = fm.getStringBounds(movement, g);


        // Determine the width that all the stats will take up using the rectangles

        // Get the dimensions of the first column and determine which is biggest;
        int strengthWidth = (int) strengthRect.getWidth();
        int agilityWidth = (int) agilityRect.getWidth();
        int firstColumnWidth = strengthWidth > agilityWidth ? strengthWidth : agilityWidth;

        // Get the width of the second column
        int intellectWidth = (int) intellectRect.getWidth();
        int hardinessWidth = (int) hardinessRect.getWidth();
        int secondColumnWidth = intellectWidth > hardinessWidth ? intellectWidth : hardinessWidth;

        // Get the dimensions of the third column
        int offensiveWidth = (int) offensiveRect.getWidth();
        int defensiveWidth = (int) defensiveRect.getWidth();
        int thirdColumnWidth = offensiveWidth > defensiveWidth ? offensiveWidth : defensiveWidth;

        // Ge the width of the fourth column
        int armorWidth = (int) armorRect.getWidth();
        int movementWidth = (int) movementRect.getWidth();
        int fourthColumnWidth = armorWidth > movementWidth ? armorWidth : movementWidth;

        // Set the margin between columns
        int columnMargin = 5;

        // Determine the total width and use it to postiion the first column
        int totalWidth = firstColumnWidth + secondColumnWidth + thirdColumnWidth + fourthColumnWidth + columnMargin * 3;

        // Find the positioning of and draw strength
        int strengthX = B_WIDTH / 2 - totalWidth / 2;
        int strengthY = B_HEIGHT - 4 * (int) strengthRect.getHeight() - 10;
        g.drawString(strength, strengthX, strengthY);

        // Find the positioning of and draw agility
        int agilityX = strengthX;
        int agilityY = strengthY + (int) agilityRect.getHeight();
        g.drawString(agility, agilityX, agilityY);


        // Draw the intellect
        int intellectX = strengthX + (strengthWidth > agilityWidth ? strengthWidth : agilityWidth) + columnMargin;
        int intellectY = strengthY;
        g.drawString(intellect, intellectX, intellectY);

        // Draw the hardiness
        int hardinessX = intellectX;
        int hardinessY = agilityY;
        g.drawString(hardiness, hardinessX, hardinessY);


        // Draw the offensive Rating
        int offensiveX = intellectX + (intellectWidth > hardinessWidth ? intellectWidth : hardinessWidth) + columnMargin;
        int offensiveY = strengthY;
        g.drawString(offensiveRating, offensiveX, offensiveY);

        // Draw the defensive Rating
        int defensiveX = offensiveX;
        int defensiveY = hardinessY;
        g.drawString(defensiveRating, defensiveX, defensiveY);


        // Draw the Armor
        int armorX = offensiveX + (offensiveWidth > defensiveWidth ? offensiveWidth : defensiveWidth) + columnMargin;
        int armorY = offensiveY;
        g.drawString(armorRating, armorX, armorY);

        // Draw the Speed
        int speedX = armorX;
        int speedY = defensiveY;
        g.drawString(movement, speedX, speedY);
    }
}
