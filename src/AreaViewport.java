import javax.swing.*;
import java.awt.*;

/**
 * Created by Bradley on 2/2/16.
 */
public class AreaViewport extends View implements Observer{
    private Map map;
    private Entity entity;
    private  String terrainBaseFilepath = "./src/res/terrain/";
    private  String areaEffectBaseFilepath = "./src/res/area-effects/";
    private  String itemBaseFilepath = "./src/res/items/";
    private  String entityBaseFilepath = "./src/res/entitys/";
    private final int AREA_WIDTH = 10;
    private final int AREA_HEIGHT = 5;
    private final int TILE_SIZE = 25;

    public AreaViewport(Map map, Entity entity){
        super();
        this.map = map;
        this.entity = entity;
        map.addObserver(this);
        entity.addObserver(this);
//        this.viewController = new GameController(this);

        // Modify the filepaths based on the user's OS
        terrainBaseFilepath = terrainBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        areaEffectBaseFilepath = areaEffectBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        itemBaseFilepath = itemBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        entityBaseFilepath = entityBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
    }

    @Override
    void render(Graphics g){
        // Render only the tiles within a certain radius of the avatar.
        int r = entity.getLocation()[1] - AREA_HEIGHT/2;
        int c = entity.getLocation()[0] - AREA_WIDTH/2;

        // Make sure to stay within the bounds of the map
        if(r < 0){
            r=0;
        }
        if(r > map.getMapHeight() - AREA_HEIGHT){
            r = map.getMapHeight() - AREA_HEIGHT;
        }
        if(c < 0){
            c=0;
        }
        if(c > map.getMapWidth() - AREA_WIDTH){
            c = map.getMapHeight() - AREA_WIDTH;
        }

        int displayX = 0;
        int displayY = 0;

        // REMOVE AFTER TESTING
        r = 0;
        c = 0;

        // THE MAP MUST BE LARGER THAN THE AREA VIEWPORT
        for(int i=r; i<AREA_HEIGHT; i++){
            displayX = 0;
            for(int j=c; j<AREA_WIDTH; j++){

                // Get the terrain at this location
                Terrain t = map.getTerrainAtLocation(j, i);
                System.out.println("TERRAIN TYPE: " + t.getType());
                System.out.println("AT: (" + displayX + ", " + displayY);
                ImageIcon ii = new ImageIcon(terrainBaseFilepath + t.getType() + ".png");
                Image terrainImg = ii.getImage();
                g.drawImage(terrainImg, displayX, displayY, this);

                //TODO: Do the same for areaEffect, item, and entity

                displayX += TILE_SIZE;
            }
            displayY += TILE_SIZE;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }

    @Override
    public void update(){
        //TODO: implement
    }

}
