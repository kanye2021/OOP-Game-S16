package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/4/16.
 */
public class InstantDeath extends AreaEffect {

    @Override
    public void onTouch(Entity entity){
        entity.getStats().loseALife();
    }

    @Override
    public String getType(){
        return "instant-death";
    }

    @Override
    public String getImageName(){
        return "skull-and-crossbones.png";
    }
}
