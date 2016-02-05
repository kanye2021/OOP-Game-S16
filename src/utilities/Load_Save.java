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

public class Load_Save {
    public Load_Save(){

    }
    //For future use it will include map, items, stats
    public void save(Map main_map, Entity avatar, String fileName) {
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            fileName = "SaveFile_1.xml"; // Temporary

            Element mainRootElement = doc.createElementNS(fileName, "Save_File"); //1 will be edited in the feature
            doc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getAvatar(doc, avatar.getLocation()[0], avatar.getLocation()[1], avatar.getOrientation()));
            mainRootElement.appendChild(getMap(doc, main_map));

            //Write to XML
            writeToXml(doc,fileName);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


    private Node getAvatar(Document doc, int x, int y, String orientation ) {
        Element avatar = doc.createElement("Avatar");
        //company.setAttribute("id", id);
        avatar.appendChild(getElements(doc, avatar, "Name", "avatar"));
        avatar.appendChild(getElements(doc, avatar, "Orientation", orientation));
        avatar.appendChild(getElements(doc, avatar, "X-Coordinate", Integer.toString(x)));
        avatar.appendChild(getElements(doc, avatar, "Y-Coordinate", Integer.toString(y)));
        return avatar;
    }
    private Node getMap(Document doc, Map m){
        Element map = doc.createElement("map");
        Tile[][] tiles = m.getTiles();
        for (int i = 0; i < tiles.length; i++ ){
            Element row = doc.createElement("row");
            for (int j = 0; j < tiles[0].length; j++) {
                row.appendChild(getTile(doc, tiles[i][j]));
            }
            map.appendChild(row);
        }
        return map;
    }
    private Node getTile(Document doc, Tile t){
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

            //Attr desc = doc.createAttribute("description");
            //desc.setValue(t.getItem().getDescription());
            //item.setAttributeNode(desc);

            Attr iType = doc.createAttribute("type");
            iType.setValue(t.getItem().getType().toString());
            item.setAttributeNode(iType);

            Attr id = doc.createAttribute("id");
            id.setValue(Integer.toString(t.getItem().getID()));
            item.setAttributeNode(id);
            
            //Attr name = doc.createAttribute("name");
            //name.setValue(t.getItem().getName());
            //item.setAttributeNode(name);
            tile.appendChild(item);
        }
        return tile;
    }
    // utility method to create text node
    private Node getElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    //----------Function to transform saved (doc) into Xml and the Console -------
    public void writeToXml(Document doc, String fileName){
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
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
