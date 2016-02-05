package views;

import controllers.GameViewController;
import models.Entity;
import models.Map;
import utilities.Load_Save;
import utilities.NavigationMediator;

import java.awt.*;

/**
 * Created by Bradley on 2/2/16.
 */
public class GameView extends View {
    private AreaViewport areaViewport;
    private StatusViewport statusViewport;

    public GameView(Map map, Entity avatar){
        NavigationMediator mediator = new NavigationMediator(map, avatar);
        areaViewport = new AreaViewport(map, avatar);
        viewController = new GameViewController(this, mediator);
        statusViewport = new StatusViewport(avatar);

        // Set the Load_Save Singleton to the current map and avatar
        Load_Save.getInstance().setAvatar(avatar);
        Load_Save.getInstance().setGameMap(map);

    }

    @Override
    public void render(Graphics g){
        areaViewport.render(g);
        statusViewport.render(g);
    }

}
