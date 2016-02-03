package views;

import models.Entity;

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
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, View.B_HEIGHT*3/4, View.B_WIDTH, View.B_HEIGHT*1/4);
        g.setColor(Color.WHITE);
        g.drawString("PUT STATS HERE", 50, View.B_HEIGHT*3/4 + 50);
    }
}
