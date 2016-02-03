

/**
 * Created by Bradley on 2/1/16.
 */
public class Tile{
    private Terrain terrain;
    private AreaEffect areaEffect;
    private Item item;
    private Entity entity;

    public Tile(Terrain terrain, AreaEffect areaEffect, Item item, Entity entity){
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.item = item;
        this.entity = entity;
    }

    public Terrain getTerrain(){
        return terrain;
    }

    public AreaEffect getAreaEffect(){
        return areaEffect;
    }

    public Item getItem(){
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

    public void removeEntity(){
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
