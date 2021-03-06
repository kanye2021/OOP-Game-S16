package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/4/16.
 */
public class LevelUpAreaEffect extends AreaEffect {
    @Override
    public void onTouch(Entity entity) {
        entity.getStats().levelUp();
    }

    @Override
    public String getType() {
        return "level-up";
    }

}
