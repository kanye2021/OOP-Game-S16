package models;

/**
 * Created by Bradley on 2/1/16.
 * Modified by Austin on 2/2/16.
 */
public abstract class Item {

    //Attributes that all Items will have
    protected String name;
    protected String type;
    protected String description;
    protected int id;


    //Overloaded Constructor that takes in name, type, description and id
    //that construct a generic item
    public Item(String name, String type, String description, int id){
        this.name=name;
        this.type=type;
        this.description=description;
        this.id  = id;
    }

    //Getters
    public int getID() {
    	return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    
    public String getDescription() { return description; }

    public abstract boolean onTouch(Entity entity);

    public abstract String getImageName();
    //Do not touch
    @Override
	public int hashCode() {

    	return id;

    }

    //Do not touch
    @Override
    public boolean equals(Object o) {
    	
    	Item otherItem = (Item) o;
    	
    	if (this.getID() == otherItem.getID()) {
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }


    public String toString() {
        return id + ": " + name + " | " + type;
    }

    //Modifies stats when used
    public void statModifier(){

    }

}
