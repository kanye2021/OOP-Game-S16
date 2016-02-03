package models;

/**
 * Created by Bradley on 2/1/16.
 */
public class AreaEffect {
    private String type;
    private int statsModifier;

    public AreaEffect(String type, int statsModifier){
        this.type = type;
        this.statsModifier = statsModifier;
    }

    public String getType(){
        return type;
    }

    public int getstatsModifier(){
        return statsModifier;
    }

    public void onTouch(Entity entity){
        // TODO: Implement area effect triggering.
    }
}
