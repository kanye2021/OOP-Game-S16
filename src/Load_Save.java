/**
 * Created by dyeung on 2/2/16.
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/*
Layout of the XML file
<Entity>
    <Avatar>
        <Stats>
            <Primary>
            ...
            </Primary>
            <Derived>
            ...
            </Derived>
        <Inventory>
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
</Entity>
<Map>
    //Need more information...
</Map>

 */

public class Load_Save {
    public Load_Save(){

    }
    //For future use it will include map, items, stats
    public void save(Entity avatar, Map main_map) {
        File output =  new File("C:\\file.xml");
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            String fileName = "SaveFile_1.xml";
            Element mainRootElement = doc.createElementNS(fileName, "Save_File"); //1 will be edited in the feature
            doc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getAvatar(doc, avatar.getLocation()[0], avatar.getLocation()[1], avatar.getOrientation()));
            //mainRootElement.appendChild(getMap(doc));

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
    private Node getMap(Document doc){
        Element map = doc.createElement("map");
        return map;
    }
    private Node getTile(Document doc, int x, int y){
        Element tile = doc.createElement("tile");

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
