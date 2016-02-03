/**
 * Created by dyeung on 2/2/16.
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

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
    //Need more information, possibly use a 2d array?
</Map>

 */

public class Load_Save {
    public Load_Save(){

    }
    //For future use it will include map, items, stats
    public void save(Entity avatar) {
        //File output =  new File("C:\\file.xml");
        //saveEntity(output, avatar);
        try_javax(avatar);
    }

    public void try_javax(Entity avatar){
        try {

            File file = new File("C:\\file.xml");
            JAXBContext entityClass = JAXBContext.newInstance(Entity.class);

            Marshaller entityMarshall = entityClass.createMarshaller();

            // output pretty printed
            entityMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            entityMarshall.marshal(avatar, file);
            entityMarshall.marshal(avatar, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    /* Old XML Stuff that we might need
    public void saveEntity(File out, Entity avatar){
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("file.xml", "Companies");
            doc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getAvatar(doc, avatar.getLocation().getX(), avatar.getLocation().getY(), avatar.getOrientation()));

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getAvatar(Document doc, int x, int y, String orientation ) {
        Element company = doc.createElement("Avatar");
        //company.setAttribute("id", id);
        company.appendChild(getCompanyElements(doc, company, "Name", "avatar"));
        company.appendChild(getCompanyElements(doc, company, "Orientation", orientation));
        company.appendChild(getCompanyElements(doc, company, "X-Coordinate", Integer.toString(x)));
        company.appendChild(getCompanyElements(doc, company, "Y-Coordinate", Integer.toString(y)));
        return company;
    }

    // utility method to create text node
    private static Node getCompanyElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    */

}
