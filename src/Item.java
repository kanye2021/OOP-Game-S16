<<<<<<< HEAD

public class Item {

	int id;
	String name;
	
	public Item (int inputID, String inputName) {
		
		id = inputID;
		name = inputName;
		
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public String toString() {
		
		return id + ": " + name;
		
	}
	
=======
/**
 * Created by Bradley on 2/1/16.
 */
public class Item {
    private String name;
    private String type;
    private String description;

    public Item(String name, String type, String description){
        this.name=name;
        this.type=type;
        this.description=description;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDescription() { return description; }

    public boolean onTouch(Entity entity){
        //TODO: Implement how an item should be triggered.
        return true;
    }
>>>>>>> refs/heads/master
}
