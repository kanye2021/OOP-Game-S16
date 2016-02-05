package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/4/16.
 */
public class HealDamage extends AreaEffect {
    private final int AMOUNT_OF_DAMAGE = 5;

    @Override
    public void onTouch(Entity entity){
        entity.getStats().modifyLifeLeft(AMOUNT_OF_DAMAGE);
    }

    @Override
    public String getType(){ return "heal-damage"; }

    @Override
    public String getImageName() { return "red-cross.png"; }
}
