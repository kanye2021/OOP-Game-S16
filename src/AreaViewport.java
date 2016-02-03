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
    private final int AREA_WIDTH = B_WIDTH;
    private final int AREA_HEIGHT = B_HEIGHT * 3/4;
    private final int TILE_SIZE = 50;

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

        int logicalWidth = AREA_WIDTH/TILE_SIZE;
        int logicalHeight = AREA_HEIGHT/TILE_SIZE;

        int r = entity.getLocation()[1] - logicalHeight/2;
        int c = entity.getLocation()[0] - logicalWidth/2;

        // Make sure to stay within the bounds of the map
        if(r < 0){
            r=0;
        }
        if(r > map.getMapHeight() - logicalHeight){
            r = map.getMapHeight() - logicalHeight;
        }
        if(c < 0){
            c=0;
        }
        if(c > map.getMapWidth() - logicalWidth){
            c = map.getMapWidth() - logicalWidth;
        }



        int displayX = 0;
        int displayY = 0;

        // THE MAP MUST BE LARGER THAN THE AREA VIEWPORT
        for(int i=r; i<AREA_HEIGHT/TILE_SIZE + r ; i++){
            displayX = 0;
            for(int j=c; j<AREA_WIDTH/TILE_SIZE + c; j++){


                // Get the terrain at this location
                Terrain t = map.getTerrainAtLocation(j, i);

                ImageIcon ii = new ImageIcon(terrainBaseFilepath + t.getType() + ".png");
                Image terrainImg = ii.getImage();
                g.drawImage(terrainImg, displayX, displayY, Display.getInstance());

                //TODO: Do the same for areaEffect, item, and entity
                Entity e = map.getEntityAtLocation(j, i);
                if(e!=null){
                    ImageIcon avatar_icon = new ImageIcon(entityBaseFilepath + e.getOrientation() + ".png");
                    Image avatarImage = avatar_icon.getImage();
                   // g.drawRect(displayX, displayY, TILE_SIZE, TILE_SIZE);
//                    int w = avatarImage.getWidth();
//                    int h =  avatarImage.getHeight();
                    g.drawImage(avatarImage, displayX, displayY, Display.getInstance());
                }

                displayX += TILE_SIZE;
            }
            displayY += TILE_SIZE;
        }
    }


    @Override
    public void update(){
        //TODO: implement
    }

}
