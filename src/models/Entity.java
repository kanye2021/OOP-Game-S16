package models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utilities.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Bradley on 2/1/16.
 */

public class Entity extends Observable {

    protected final int START_X = 10;
    protected final int START_Y = 5;

    //Entity properties
    protected String lastAtemptedDirection;
    protected String occupation;
    protected Stats stats;
    protected Inventory inventory;
    protected int[] location;
    protected String filePathExtension = "src/res/save_files/";
    protected String filePathName = "SaveFile_1.xml"; //tmp

    public Entity() {
        // Default to smasher
        initEntity("smasher");
    }
    public Entity(String occupation){

        //initEntity(occupation);
    }

    private void initEntity(String occupation) {
        this.location = new int[2];
        this.location[0] = START_X;
        this.location[1] = START_Y;
        this.lastAtemptedDirection = "N";
        this.occupation = occupation;
        this.inventory = new Inventory();
        this.stats = new Stats(occupation);
        //Avatar parsing
        initXML(filePathExtension + filePathName);
    }
    /*--------------Load from XML --------------*/
    public void initXML(String filepath){
        // Get the xml filepath string ensuring file separators are specific to the use's OS.
        String file = filepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(file));
            doc.getDocumentElement().normalize();

            NodeList entities = doc.getElementsByTagName("entity"); //used for future
            for (int i = 0; i < entities.getLength(); i++){
                Element entity = (Element) entities.item(i);
                System.out.println(entity.getAttribute("type"));
                if ( entity.getAttribute("type").equals("avatar") ){
                    System.out.println("In here");
                    location[0] = Integer.parseInt(entity.getAttribute("location_x"));
                    location[1] = Integer.parseInt(entity.getAttribute("location_y"));
                    lastAtemptedDirection = entity.getAttribute("orientation");
                }
                //TODO: Add the inventory and stats to this
            }

            System.out.println("All the stuff: " + location[0] + "," + location[1] + "," + lastAtemptedDirection);



        }catch(Exception e){
            System.out.println("Problem parsing avatar");
            e.printStackTrace();
        }

    }
    /*----------Get and Setters --------*/
    public int[] getLocation(){
        return location;
    }
    public String getOrientation(){
        return this.lastAtemptedDirection;
    }
    public String getOccupation(){
        return this.occupation;
    }
    public Stats getStats(){
        return this.stats;
    }
    public Inventory getInventory(){
        return this.inventory;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    // Each "type" (subclass) of entity will override this method to return its type.
    public String getType() {return "entity";}
    public void updateOrientation(String orientation){
        lastAtemptedDirection = orientation;
    }
    public String getImageName(){
        return "entity-" + lastAtemptedDirection + ".png" ;
    }
    /* ------End of Getters and Setters -----*/

    //All this is going to do is update orientation/location of the entity

    public void moveTo(int x, int y, String direction) {
        location[0] = x;
        location[1] = y;
        updateOrientation(direction);

        /* These functions exist in the utilities.Observable class */
        setChanged();
        notifyObservers();  //Observers are the views
    }



}
