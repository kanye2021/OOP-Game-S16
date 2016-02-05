package utilities; /**
 * Created by dyeung on 2/2/16.
 */


import models.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/*
Layout of the XML file
<models.Entity>
    <Avatar>
        <models.Stats>
            <Primary>
            ...
            </Primary>
            <Derived>
            ...
            </Derived>
        <models.Inventory>
            <Equipped>
                <Item1>
                <Item2>
                ...
            </Equipped>
            <Unequipped>
                <Item1>
                <Item2>
                ...
            </Unequipped>
        <Occupation>
        <Location>
            <Orientation> //Might not be necessary
</models.Entity>
<models.Map>
    //Need more information...
</models.Map>

 */

// This class is a Singleotn
public class Load_Save {

    private static String currentFileName;

    // Singleton initilization
    private static Load_Save instance = new Load_Save();

    // Static 'instance' method
    public static Load_Save getInstance() {
        return instance;
    }

    private Load_Save() {
        currentFileName = "";
    }

    public static void setCurrentFileName(String name) {
        currentFileName = name;
    }

    public static String getCurrentFileName() {
        return currentFileName;
    }

    public static void load(String fileName){

    }

    //For future use it will include map, items, stats
    public static void save(Map main_map, Entity avatar) {
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            String filePath = "src/res/save_files/" + currentFileName + ".xml";

            Element mainRootElement = doc.createElementNS(filePath, "Save_File"); //1 will be edited in the feature
            doc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getEntity(doc, avatar));
            mainRootElement.appendChild(getMap(doc, main_map));

            //Write to XML
            writeToXml(doc,filePath);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


    private static Node getEntity(Document doc, Entity e) {
        Element entity = doc.createElement("entities");

    //----  Get Type, location and orientation      ------
        entity.appendChild(getEntityInfo(doc,e));

        //TODO: Future cases of multiple entities

        return entity;
    }
    private static Node getEntityInfo(Document doc, Entity e){
        Element type = doc.createElement("entity");

        //Get attributes such as location, orientation and type
        Attr e_type = doc.createAttribute("type");
        e_type.setValue(e.getType());
        type.setAttributeNode(e_type);


        Attr x = doc.createAttribute("location_x");
        x.setValue(Integer.toString(e.getLocation()[0]));
        type.setAttributeNode(x);

        Attr y = doc.createAttribute("location_y");
        y.setValue(Integer.toString(e.getLocation()[1]));
        type.setAttributeNode(y);

        Attr orientation = doc.createAttribute("orientation");
        orientation.setValue(e.getOrientation());
        type.setAttributeNode(orientation);

        Attr occupation = doc.createAttribute("occupation");
        occupation.setValue(e.getOccupation());
        type.setAttributeNode(occupation);

        //Get stats and inventory of the entity
        type.appendChild( getInventory(doc, e.getInventory()) );
        type.appendChild(getStats (doc, e.getStats()) );

        return type;
    }
    private static Node getInventory(Document doc, Inventory inv){
        Element inventory = doc.createElement("inventory");

        return inventory;
    }
    private static Node getStats(Document doc, Stats stat){
        Element stats = doc.createElement("stats");

        return stats;
    }
    private static Node getMap(Document doc, Map m){
        Element map = doc.createElement("map");
        Tile[][] tiles = m.getTiles();
        int getHeight = tiles.length;
        int getWidth = tiles[0].length;
        Attr width = doc.createAttribute("width");
        width.setValue( Integer.toString(getWidth) );
        map.setAttributeNode(width);

        Attr height = doc.createAttribute("height");
        height.setValue( Integer.toString(getHeight) );
        map.setAttributeNode(height);

        for (int i = 0; i < tiles.length; i++ ){
            Element row = doc.createElement("row");
            for (int j = 0; j < tiles[0].length; j++) {
                row.appendChild(getTile(doc, tiles[i][j]));
            }
            map.appendChild(row);
        }
        return map;
    }
    private static Node getTile(Document doc, Tile t){
        Element tile = doc.createElement("tile");
        ///Terrain
        Element terrain = doc.createElement("terrain");

        Attr type = doc.createAttribute("type");
        type.setValue(t.getTerrain().getType());
        terrain.setAttributeNode(type);
        tile.appendChild(terrain);

        //Entity
        if (t.getEntity() != null) {
        //TODO: Still not sure what will be stored here
        }
        //Area of Effect
        if (t.getAreaEffect() != null) {
            Element areaEffect = doc.createElement("areaEffect");

            Attr aType = doc.createAttribute("type");
            aType.setValue(t.getAreaEffect().getType());
            areaEffect.setAttributeNode(aType);

            Attr stats = doc.createAttribute("statsModifier");
            stats.setValue(Integer.toString(t.getAreaEffect().getstatsModifier())); //Returns an int for stat modifier needs to be converted to string
            areaEffect.setAttributeNode(stats);
            tile.appendChild(areaEffect);
        }
        //Item
        if (t.getItem() != null) {
            Element item = doc.createElement("item");

            Attr desc = doc.createAttribute("description");
            desc.setValue(t.getItem().getDescription());
            item.setAttributeNode(desc);

            Attr iType = doc.createAttribute("type");
            iType.setValue(t.getItem().getType());
            item.setAttributeNode(iType);

            Attr name = doc.createAttribute("name");
            name.setValue(t.getItem().getName());
            item.setAttributeNode(name);
            tile.appendChild(item);
        }
        return tile;
    }

    //----------Function to transform saved (doc) into Xml and the Console -------
    public static void writeToXml(Document doc, String fileName){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));

            //Formats it nicely (5 is the max number of child nodes
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformerFactory.setAttribute("indent-number", 5);
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount",
                    Integer.toString(5));
            transformer.transform(source, result);

            //Output to console for testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
            System.out.println("Saved success!");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
