package models;

/**
 * Created by Bradley on 2/1/16.
 */
public abstract class AreaEffect {

    public abstract void onTouch(Entity entity);
    public abstract String getType();
    public abstract String getImageName();
}
