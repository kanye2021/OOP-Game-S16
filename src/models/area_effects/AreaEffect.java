package models.area_effects;

import models.Entity;

/**
 * Created by Bradley on 2/1/16.
 */
public abstract class AreaEffect {

	protected String type; //Can't have an axbstract final type in abstract class
    public abstract void onTouch(Entity entity);
    public abstract String getType();
    public abstract String getImageName();
}
