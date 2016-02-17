/**
 * Created by Bradley
 * on 2/2/16.
 */

package views;

import models.Entity;
import models.Map;
import models.Terrain;
import models.area_effects.AreaEffect;
import models.items.Item;
import utilities.Observer;
import utilities.Utilities;

import java.awt.*;
import javax.swing.*;

public class AreaViewport extends View implements Observer {
    private Map map;
    private Entity entity;

    private final String terrainBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/terrain/");
    private final String decalBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/decals/");
    private final String takeableItemBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/items/takeable/");
    private final String oneshotItemBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/items/oneshot/");
    private final String entityBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/entitys/");
    private final String obstacleItemBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/items/obstacle/");
    private final String interactiveItemBaseFilepath = Utilities.getFileSystemDependentPath("./src/res/items/interactive/");

    private final int AREA_WIDTH = B_WIDTH;
    private final int AREA_HEIGHT = B_HEIGHT * 4 / 5;
    private final int TILE_SIZE = 50;


    public AreaViewport(Map map, Entity entity) {
        super();
        this.map = map;
        this.entity = entity;
        map.addObserver(this);
//        this.viewController = new controllers.GameViewController(this);

        // Modify the filepaths based on the user's OS

    }

    @Override
    public void render(Graphics g) {

        /*  There is essentially a transformation between two different coordinate systems. There is the logical coordinate
            system (the array of tiles in the map) and a visual coordinate system (what will be shown on the display).
        */

        // The width of each (visual) tile is used here to convert the visual dismensions of the area viewport to the
        // logical dimensions(how much of the tiles in the array will be used.
        int logicalWidth = (int) Math.ceil((double) AREA_WIDTH / TILE_SIZE);
        int logicalHeight = (int) Math.ceil((double) AREA_HEIGHT / TILE_SIZE);

        // r and c represent the row and column in the tile array that correspond the top left corner of the visible map.
        int r = entity.getLocation().x - logicalHeight / 2;
        int c = entity.getLocation().y - logicalWidth / 2;

        // Make sure to stay within the bounds of the map
        if (r < 0) {
            r = 0;
        }
        if (r > map.getMapHeight() - logicalHeight) {
            r = map.getMapHeight() - logicalHeight;
        }
        if (c < 0) {
            c = 0;
        }
        if (c > map.getMapWidth() - logicalWidth) {
            c = map.getMapWidth() - logicalWidth;
        }


        // r and c will be mapped to te top left corner of the display (0,0).
        int displayX = 0;
        int displayY = 0;

        // Loop through the array of tiles mapping each tile to its location on the display.
        // Note: The map should be larger than the area viewport to render nicely.
        for (int i = r; i < logicalHeight + r; i++) {
            displayX = 0;
            for (int j = c; j < logicalWidth + c; j++) {


                // Get the terrain at this location
                Terrain t = map.getTerrainAtLocation(j, i);

                //OUT OF BOUNDS!!!!!!!!!!!!!!!!
                if (t == null) { continue; }

                ImageIcon ii = Utilities.getImageIcon(terrainBaseFilepath + t.getType() + ".png");

                Image terrainImg = ii.getImage();
                g.drawImage(terrainImg, displayX, displayY, Display.getInstance());


                Item item = map.getItemAtLocation(j, i);


                if (item != null) {
                    String filePath = takeableItemBaseFilepath;
                    switch (item.getType()) {
                        case INTERACTIVE:
                            //System.out.println("Interactive items file path: " + j + "," + i);
                            filePath = interactiveItemBaseFilepath;
                            break;
                        case OBSTACLE:
                            filePath = obstacleItemBaseFilepath;
                            break;
                        case TAKE_ABLE:
                            filePath = takeableItemBaseFilepath;
                            break;
                        case ONE_SHOT:
                            filePath = oneshotItemBaseFilepath;
                            break;
                    }

                    ImageIcon item_icon = Utilities.getImageIcon(filePath + item.getPathToPicture());
                    Image itemImage = item_icon.getImage();

                    // Center the item in the tile
                    int offsetX = (TILE_SIZE - itemImage.getWidth(null)) / 2;
                    int offsetY = (TILE_SIZE - itemImage.getHeight(null)) / 2;
                    g.drawImage(itemImage, displayX + offsetX, displayY + offsetY, Display.getInstance());
                }

                // Display decals
                Decal decal = map.getDecalAtLocation(j, i);
                if (decal != null) {
                    ImageIcon area_effect_icon = Utilities.getImageIcon(decalBaseFilepath + decal.getPathToFile());

                    Image areaEffectImage = area_effect_icon.getImage();

                    // Center the decal in the tile
                    int offsetX = (TILE_SIZE - areaEffectImage.getWidth(null)) / 2;
                    int offsetY = (TILE_SIZE - areaEffectImage.getHeight(null)) / 2;
                    g.drawImage(areaEffectImage, displayX + offsetX, displayY + offsetY, Display.getInstance());
                }

                // Display any entitys at thi slocation
                Entity e = map.getEntityAtLocation(j, i);
                if (e != null) {
                    ImageIcon avatar_icon = Utilities.getImageIcon(entityBaseFilepath + e.getImageName());
                    Image avatarImage = avatar_icon.getImage();

                    // Center the avatar in the tile
                    int offsetX = (TILE_SIZE - avatarImage.getWidth(null)) / 2;
                    int offsetY = (TILE_SIZE - avatarImage.getHeight(null)) / 2;
                    g.drawImage(avatarImage, displayX + offsetX, displayY + offsetY, Display.getInstance());
                }


                displayX += TILE_SIZE;
            }
            displayY += TILE_SIZE;
        }
    }


    @Override
    public void update() {
        //TODO: implement
    }

}