package models;

import models.Entity;
import models.Terrain;
import models.area_effects.AreaEffect;
import models.items.Item;
import views.Decal;

/**
 * Created by Bradley on 2/1/16.
 */
public class Tile{
    private Terrain terrain;
    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;
    private Entity entity;

    public Tile(Terrain terrain, AreaEffect areaEffect, Decal decal, Item item, Entity entity) {
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.decal = decal;
        this.item = item;
        this.entity = entity;
    }

    public Terrain getTerrain(){
        return terrain;
    }

    public AreaEffect getAreaEffect(){
        return areaEffect;
    }

    public Decal getDecal() {

        return decal;

    }

    public Item getItem() {

        return item;
    }

    public Entity getEntity(){
        return entity;
    }

    public void removeItem(){
        item = null;
    }

    public void removeAreaEffect(){
        areaEffect = null;
    }

    public void removeDecal() {

        decal = null;

    }

    public void removeEntity() {
        entity = null;
    }

    // For now putting an item on this tile simply replaces one that was already there.
    // Perhaps in a later iteration multiple items could be on a single tile?
    public void addItem(Item item){
        this.item = item;
    }

    // For now if there is already an entity on the tile. adding an entity will replace that
    // entity with this one. This may or may not be the desired affect so care should be taken
    // in the logic that consumes this function (and it has been).
    public void addEntity(Entity entity){
        this.entity = entity;
    }


}
