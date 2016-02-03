
public abstract class Observable {
	//Innate variables of observables and whether the observable has changed
	protected Observer[] observers = new Observer[10];
	protected int arraySize = 0;
	protected boolean hasChanged;
	
	
	//Methods of an observable
	
	//Subscribes an observer
	public void addObserver(Observer obs){
		observers[arraySize] = obs;
		arraySize++;
	};
	
	//Deletes a specific observer TODO: FIX REFERENCES TO NULL AFTER DELETION
	public void deleteObserver(Observer obs){
		for(int i = 0; i < arraySize; i++){
			if(observers[i] == obs){
				observers[i] = null;
			}
		}
	}; 
	
	//Deletes All Observers
	public void deleteAllObservers(){
		for(int i = 0; i < arraySize; i++){
			observers[i] = null;
		}
	}; 
	
	//Checks for changes in the set of class variables
	protected void setChanged(){ hasChanged = true;};
	
	//Unchecks flag for set changes
	protected void clearChanged(){ hasChanged = false;};
	
	//Returns the if the set has changed
	public boolean hasChanged(){ return hasChanged;};
	
	//Notifies all observers and calls their update method so they do there stuff with the new info
	public void notifyObservers(){
		for(int i = 0; i < arraySize; i++){
			observers[i].update();
		}
		this.clearChanged();
	};
	
}
