import java.awt.*;

/**
 * Created by Bradley on 2/2/16.
 */
public class GameView extends View {
    private AreaViewport areaViewport;
//    private StatusViewport statusViewport;

    public GameView(Map map, Entity entity, NavigationMediator navigationMediator){
        areaViewport = new AreaViewport(map, entity);
        viewController = new GameController(this, navigationMediator);
//        StatusViewport = new StatusViewport(entity);
    }

    @Override
    void render(Graphics g){
        areaViewport.render(g);
//        statusViewport.render(g);
    }

}
