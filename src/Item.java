
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
	
}
