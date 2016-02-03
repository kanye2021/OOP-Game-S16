package models;

/**
 * Created by Bradley on 2/1/16.
 * Modified by Austin on 2/2/16.
 */
public class Item {
    private String name;
    private String type;
    private String description;
    private int id;
    
    public Item(String name, String type, String description, int id){
        this.name=name;
        this.type=type;
        this.description=description;
        this.id  = id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getID() {
    	return id;
    }
    
    public String getDescription() { return description; }

    public boolean onTouch(Entity entity){
        //TODO: Implement how an item should be triggered.
        return true;
    }
    
    @Override
	public int hashCode() {

    	return id;

    }
    
    @Override
    public boolean equals(Object o) {
    	
    	Item otherItem = (Item) o;
    	
    	if (this.getID() == otherItem.getID()) {
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
}
