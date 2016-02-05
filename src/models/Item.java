package models;

/**
 * Created by Bradley on 2/1/16.
 * Modified by Austin on 2/2/16.
 */

public abstract class Item {

	public static enum Type {
		TAKEABLE("take-able"),
		ONE_SHOT("one-shot"),
		INTERACTIVE("interactive"),
		OBSTACLE("obstacle");
		
		private String s;
		
		private Type(String s) {
			
			this.s = s;
		
		}
		
		public String toString() {
			
			return s;
			
		}
	}
	
    //Attributes that all Items will have
    protected int id;
	protected String name;
    protected Type type;
    protected String description;
    protected String pathToPicture;


    //Overloaded Constructor that takes in name, type, description and id
    //that construct a generic item
    protected Item(int id, String name, Type type, String description, String pathToPicture){
    	this.id  = id;
    	this.name = name;
        this.type = type;
        this.description = description;
        this.pathToPicture = pathToPicture;
    }

    protected Item(int id, Type type) {
    	this.id = id;
    	this.type = type;
    }
    
    //Getters
    public final int getID() {
    	
    	return id;
    	
    }
    
    public Type getType() {
        
    	return type;
    
    }
    
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getPathToPicture();

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

}
