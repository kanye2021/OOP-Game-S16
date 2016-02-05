package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/4/16.
 */
public class LevelUp extends AreaEffect {
    @Override
    public void onTouch(Entity entity){
        entity.getStats().levelUp();
    }

    @Override
    public String getType(){
        return "level-up";
    }

    @Override
    public String getImageName(){
        return "gold-star.png";
    }
}
