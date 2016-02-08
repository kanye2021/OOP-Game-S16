package models;

import models.area_effects.AreaEffect;
import models.items.Item;
import utilities.Load_Save;
import utilities.Observable;
import views.Decal;

/**
 * Created by Bradley on 2/1/16.
 */
public class Map extends Observable {

    public Tile[][] tiles;
    private int mapWidth;
    private int mapHeight;
    private String defaultMap = "./src/res/maps/default_map.xml";
    private int changedX;
    private int changedY;

    public Map() {
        defaultMap = defaultMap.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        Load_Save.getInstance().loadMap(this, defaultMap); //whenever it is initialized, the default map will be loaded
    }

    public void initMap() {
        //Load_Save.getInstance().loadMap(defaultMap);
    }

    public void setMapInfo(int width, int height, Tile[][] tileArray) {
        mapHeight = height;
        mapWidth = width;
        tiles = tileArray;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Terrain getTerrainAtLocation(int x, int y) {
        if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[0].length)
            return null;

        return tiles[y][x].getTerrain();
    }

    public AreaEffect getAreaEffectAtLocation(int x, int y) {
        return tiles[y][x].getAreaEffect();
    }

    public Decal getDecalAtLocation(int x, int y) {

        return tiles[y][x].getDecal();

    }

    public Item getItemAtLocation(int x, int y) {
        return tiles[y][x].getItem();
    }

    public Entity getEntityAtLocation(int x, int y) {
        return tiles[y][x].getEntity();
    }


    //Mutator functions to tiles notify views.AreaViewport
    public void insertItemAtLocation(int x, int y, Item item) {
        tiles[y][x].addItem(item);
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void insertEntityAtLocation(int x, int y, Entity entity) {
        tiles[y][x].addEntity(entity);
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void removeItemFromLocation(int x, int y) {
        tiles[y][x].removeItem();
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void removeEntityFromLocation(int x, int y) {
        tiles[y][x].removeEntity();
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

}