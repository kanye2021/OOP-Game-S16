package views;

import models.Entity;
import models.Map;
import models.Terrain;
import utilities.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bradley on 2/2/16.
 */
public class AreaViewport extends View implements Observer {
    private Map map;
    private Entity entity;
    private  String terrainBaseFilepath = "./src/res/terrain/";
    private  String areaEffectBaseFilepath = "./src/res/area-effects/";
    private  String itemBaseFilepath = "./src/res/items/";
    private  String entityBaseFilepath = "./src/res/entitys/";
    private final int AREA_WIDTH = View.B_WIDTH;
    private final int AREA_HEIGHT = View.B_HEIGHT * 3/4;
    private final int TILE_SIZE = 50;

    public AreaViewport(Map map, Entity entity){
        super();
        this.map = map;
        this.entity = entity;
        map.addObserver(this);
        entity.addObserver(this);
//        this.viewController = new controllers.GameViewController(this);

        // Modify the filepaths based on the user's OS
        terrainBaseFilepath = terrainBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        areaEffectBaseFilepath = areaEffectBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        itemBaseFilepath = itemBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        entityBaseFilepath = entityBaseFilepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
    }

    @Override
    public void render(Graphics g){

        /*  There is essentially a transformation between two different coordinate systems. There is the logical coordinate
            system (the array of tiles in the map) and a visual coordinate system (what will be shown on the display).
        */

        // The width of each (visual) tile is used here to convert the visual dismensions of the area viewport to the
        // logical dimensions(how much of the tiles in the array will be used.
        int logicalWidth = AREA_WIDTH/TILE_SIZE;
        int logicalHeight = AREA_HEIGHT/TILE_SIZE;

        // r and c represent the row and column in the tile array that correspond the top left corner of the visible map.
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


        // r and c will be mapped to te top left corner of the display (0,0).
        int displayX = 0;
        int displayY = 0;

        // Loop through the array of tiles mapping each tile to its location on the display.
        // Note: The map should be larger than the area viewport to render nicely.
        for(int i=r; i<logicalHeight + r ; i++){
            displayX = 0;
            for(int j=c; j<logicalWidth + c; j++){


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

                    // Center the entity in the tile
                    int offsetX = (TILE_SIZE - avatarImage.getWidth(null))/2;
                    int offsetY = (TILE_SIZE - avatarImage.getHeight(null))/2;
                   // g.drawRect(displayX, displayY, TILE_SIZE, TILE_SIZE);
//                    int w = avatarImage.getWidth();
//                    int h =  avatarImage.getHeight();
                    g.drawImage(avatarImage, displayX + offsetX, displayY + offsetY, Display.getInstance());
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