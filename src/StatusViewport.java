import java.awt.*;

/**
 * Created by Bradley on 2/3/16.
 */
public class StatusViewport extends View {
    private Entity entity;
    private final int AREA_WIDTH = B_WIDTH;
    private final int AREA_HEIGHT = B_HEIGHT * 1/4;

    public StatusViewport(Entity entity){
        this.entity = entity;
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, B_HEIGHT*3/4, B_WIDTH, B_HEIGHT*1/4);
        g.setColor(Color.WHITE);
        g.drawString("PUT STATS HERE", 50, B_HEIGHT*3/4 + 50);
    }

}
