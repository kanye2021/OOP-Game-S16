package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/1/16.
 */
public abstract class AreaEffect {

	private abstract final String type
    public abstract void onTouch(Entity entity);
    public abstract String getType();
    public abstract String getImageName();
}
