package views;

import models.Entity;
import models.Stats;

import java.awt.*;

/**
 * Created by Bradley on 2/3/16.
 */
public class StatusViewport extends View {
    private Entity entity;
    private final int AREA_WIDTH = View.B_WIDTH;
    private final int AREA_HEIGHT = View.B_HEIGHT * 1/4;

    public StatusViewport(Entity entity){
        this.entity = entity;
    }

    @Override
    public void render(Graphics g){
        int x = 10;
        int y = 50;
        Stats stats = entity.getStats();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, View.B_HEIGHT*3/4, View.B_WIDTH, View.B_HEIGHT*1/4);
        g.setColor(Color.WHITE);

        g.drawString("Level: ", x, View.B_HEIGHT*3/4 + y - 20);
        g.drawString(stats.getLevel(), x+50, View.B_HEIGHT*3/4 + y - 20);

        g.drawString("Exp: ", x, View.B_HEIGHT*3/4 + y);
        g.drawString(stats.getExperience(), x+50, View.B_HEIGHT*3/4 + y);
        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y);
        g.drawString(stats.getExpReqLvUp(), x+80, View.B_HEIGHT*3/4 + y);


        g.drawString("Health", x, View.B_HEIGHT*3/4 + y+20);
        g.drawString(stats.getHealth(), x+50, View.B_HEIGHT*3/4 + y+20);
        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+20);
        g.drawString(stats.getMaxHealth(), x+80, View.B_HEIGHT*3/4+ y+ 20);

        g.drawString("Mana", x, View.B_HEIGHT*3/4 + y+40);
        g.drawString(stats.getMana(), x+50, View.B_HEIGHT*3/4 + y+40);
        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+40);
        g.drawString(stats.getMaxMana(), x+80, View.B_HEIGHT*3/4 + y+40);

        g.drawString("Lives: ", x, View.B_HEIGHT*3/4 + y+60);
        g.drawString(stats.getLivesLeft(), x+50, View.B_HEIGHT*3/4 + y+60);
        g.drawString("/", x+70, View.B_HEIGHT*3/4 + y+60);
        g.drawString(stats.getMaxMana(), x+80, View.B_HEIGHT*3/4 + y+60);

        g.drawString("Strength", x+120, View.B_HEIGHT*3/4 + y-20);
        g.drawString(stats.getStrength(), x+195, View.B_HEIGHT*3/4 + y-20);

        g.drawString("Hardiness", x+120, View.B_HEIGHT*3/4 + y+0);
        g.drawString(stats.getHardiness(), x+195, View.B_HEIGHT*3/4 + y+0);

        g.drawString("Agility", x+120, View.B_HEIGHT*3/4 + y+20);
        g.drawString(stats.getAgility(), x+195, View.B_HEIGHT*3/4 + y+20);

        g.drawString("Intellect", x+120, View.B_HEIGHT*3/4 + y+40);
        g.drawString(stats.getIntellect(), x+195, View.B_HEIGHT*3/4 + y+40);

        g.drawString("Offensive Rating", x+240, View.B_HEIGHT*3/4 + y-20);
        g.drawString(stats.getOffensiveRating(), x+360, View.B_HEIGHT*3/4 + y-20);

        g.drawString("Defensive Rating", x+240, View.B_HEIGHT*3/4 + y+0);
        g.drawString(stats.getDefensiveRating(), x+360, View.B_HEIGHT*3/4 + y+0);

        g.drawString("Armor Rating", x+240, View.B_HEIGHT*3/4 + y+20);
        g.drawString(stats.getArmorRating(), x+360, View.B_HEIGHT*3/4 + y+20);

        g.drawString("Movement", x+240, View.B_HEIGHT*3/4 + y+40);
        g.drawString(stats.getMovement(), x+360, View.B_HEIGHT*3/4 + y+40);
    }
}
