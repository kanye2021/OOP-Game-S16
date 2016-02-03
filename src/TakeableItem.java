/**
 * Created by denzel on 2/3/16.
 */

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {

    //Constructor
    public TakeableItem(){
        super();
        this.type = "take-able";
    }

    /**
     *Returns true if it should be removed from the map
     */
    @Override
    public boolean onTouch(Entity entity) {

        //entity.getInventory().addItem(this);
        return true;
    }
}
