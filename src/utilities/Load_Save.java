package utilities; /**
 * Created by dyeung on 2/2/16.
 */


import models.*;
import models.Entity;
import models.area_effects.*;
import models.items.*;

import org.w3c.dom.*;
import org.xml.sax.SAXParseException;

import views.Display;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.InterruptedIOException;

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
    private final static String filePathExtension = "src/res/save_files/";
    private static Map gameMap;
    private static Entity avatar;


    public static Entity getAvatar() {
        return avatar;
    }
    public static Map getGameMap() {
        return gameMap;
    }
    public static void setGameMap(Map map) {
        gameMap = map;
    }
    public static void setAvatar(Entity a) {
        avatar = a;
    }

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
/*-----------------------------For Loading --------------------------------*/
    public static void load(String fileName){
        System.out.println("File name is: " + fileName);
        currentFileName = fileName;
        String filePath = filePathExtension + fileName;
        loadMap(IOMediator.getInstance().map, filePath);
        loadAvatar(filePath);
        //In this case the loadMap will just load the static map in IOMediator
        Display.getInstance().repaint(); //To repaint the entity back on the map
    }
    public static void loadMap(Map inputMap, String fileName){ //Function will load the map in xml (Through fileName) to the inputMap
        try{
            // Get the xml filepath string ensuring file separators are specific to the use's OS.
            //TODO: Uncomment when done testing
            String filepath = fileName.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));

            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            // Normalize
            doc.getDocumentElement().normalize();

            NodeList mapList = doc.getElementsByTagName("map"); //Maybe in the future we have multiple maps?
            Element map = (Element) mapList.item(0); //Only 1 map element atm
            // Get the width and height of the map
            int mapWidth = Integer.parseInt(map.getAttribute("width"));
            int mapHeight = Integer.parseInt(map.getAttribute("height"));

            // Create an empty array of tiles
            Tile[][] tiles = new Tile[mapHeight][mapWidth];

            NodeList rows = doc.getElementsByTagName("row");

            for(int i=0; i<rows.getLength(); i++){
                Element row = (Element) rows.item(i);
                NodeList tileNodes = row.getElementsByTagName("tile");

                for(int j=0; j<tileNodes.getLength(); j++){
                    Element tileElement = (Element) tileNodes.item(j);

                    // Declare variables use to construct a tile
                    Terrain terrain = null;
                    AreaEffect areaEffect = null;
                    Item item = null;
                    Entity entity = null;

                    // Get the terrain on the tile
                    Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0);
                    String terrainType = terrainElement.getAttribute("type");
                    terrain = new Terrain(terrainType);

                    // Get the areaEffect if there is one
                    NodeList areaEffectNodes = tileElement.getElementsByTagName("area-effect");
                    if(areaEffectNodes.getLength() > 0){
                        Element areaEffectElement = (Element) areaEffectNodes.item(0);
                        String areaEffectType = areaEffectElement.getAttribute("type");
                        switch(areaEffectType){
                            case "take-damage":
                                areaEffect = new TakeDamage();
                                break;
                            case "heal-damage":
                                areaEffect = new HealDamage();
                                break;
                            case "level-up":
                                areaEffect = new LevelUp();
                                break;
                            case "instant-death":
                                areaEffect = new InstantDeath();
                                break;
                        }
                    }

                    // Get the item if there is one
                    NodeList itemNodes = tileElement.getElementsByTagName("item");
                    if(itemNodes.getLength() > 0){
                        Element itemElement = (Element) itemNodes.item(0);
                        String itemType = itemElement.getAttribute("type");
                        int id = Integer.parseInt(itemElement.getAttribute("id"));

                        //if statements for the different types of items

                        //if take-able
                        if(itemType.equals(Item.Type.TAKEABLE.toString())){
                            item = new TakeableItem(TakeableItem.Items.values()[id]);
                        }
                        else if(itemType.equals(Item.Type.ONE_SHOT.toString())){
                            item = new OneShotItem(OneShotItem.Effects.values()[id]);
                        }
                        else if(itemType.equals((Item.Type.INTERACTIVE.toString()))){
                            item = new InteractiveItem(InteractiveItem.Quests.values()[id]);
                        }else if(itemType.equals(Item.Type.OBSTACLE.toString())){
                            item = new Obstacle(Obstacle.Obstacles.Jorge);
                        }else{
                            System.out.println("What the fuck");
                        }

                    }

                    // Get any entities that are on the tile.
                    NodeList entityNodes = tileElement.getElementsByTagName("entity");
                    if(entityNodes.getLength() > 0){
                        Element entityElement = (Element) entityNodes.item(0);
                        //TODO: Load whatever attributes are necessary
                        entity = new Entity();
                    }

                    tiles[i][j] = new Tile(terrain, areaEffect, item, entity);
                }
            }// End of for loops
            inputMap.setMapInfo(mapHeight, mapWidth, tiles);

        }catch(SAXParseException e){
            System.out.println("Error parsing");
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }

    }
    public static void loadAvatar(String filepath){
        // Get the xml filepath string ensuring file separators are specific to the use's OS.
        String file = filepath.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));
        Entity avatar = IOMediator.getInstance().entity;
        Map m = IOMediator.getInstance().map;
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(file));
            doc.getDocumentElement().normalize();

            NodeList entities = doc.getElementsByTagName("entity"); //used for future
            //Checks the type of the entity and sets information
            for (int i = 0; i < entities.getLength(); i++){
                Element entity = (Element) entities.item(i);
                if ( entity.getAttribute("type").equals("avatar") ){
                    int x = Integer.parseInt(entity.getAttribute("location_x"));
                    int y = Integer.parseInt(entity.getAttribute("location_y"));
                    avatar.updateLocation(x,y);
                    avatar.updateOrientation(entity.getAttribute("orientation"));
                    loadStats(avatar.getStats(), entity); //Separate function to handle loading stats
                    //Adds avatar to the map
                    m.insertEntityAtLocation(x,y,avatar);
                }
            }

            System.out.println("Finish loading entity: " + avatar.getLocation()[0] + "," + avatar.getLocation()[1] + "," + avatar.getOrientation());
        }catch(Exception e){
            System.out.println("Problem parsing avatar");
            e.printStackTrace();
        }

    }
    public static void loadStats(Stats avatarStats, Element entity){
        NodeList tmp = entity.getElementsByTagName("pStats"); //Hacky shit
        Element pStats = (Element) tmp.item(0); //Basically needs to get the tag "pStats" from xml
        NodeList tmp2 = entity.getElementsByTagName("dStats"); //Hacky shit
        Element dStats = (Element) tmp2.item(0);

        avatarStats.loadLives(Integer.parseInt(pStats.getAttribute("lives")));
        avatarStats.loadStr(Integer.parseInt(pStats.getAttribute("strength")));
        avatarStats.loadAgi(Integer.parseInt(pStats.getAttribute("agility")));
        avatarStats.loadInt(Integer.parseInt(pStats.getAttribute("intellect")));
        avatarStats.loadTough(Integer.parseInt(pStats.getAttribute("hardiness")));
        avatarStats.loadExp(Integer.parseInt(pStats.getAttribute("experience")));
        avatarStats.loadMov(Integer.parseInt(pStats.getAttribute("movement")));

        avatarStats.loadLvl(Integer.parseInt(dStats.getAttribute("level")));
        avatarStats.loadHealth(Integer.parseInt(dStats.getAttribute("health")));
        avatarStats.loadMana(Integer.parseInt(dStats.getAttribute("mana")));
        avatarStats.loadOff(Integer.parseInt(dStats.getAttribute("offensiveRating")));
        avatarStats.loadDef(Integer.parseInt(dStats.getAttribute("defensiveRating")));
        avatarStats.loadArm(Integer.parseInt(dStats.getAttribute("armorRating")));

        avatarStats.loadMaxHealth(Integer.parseInt(dStats.getAttribute("maxHealth")));
        avatarStats.loadMaxMana(Integer.parseInt(dStats.getAttribute("maxMana")));
        avatarStats.loadExpReqLvl(Integer.parseInt(dStats.getAttribute("expReqLvUp")));
        avatarStats.loadLastLvlExp(Integer.parseInt(dStats.getAttribute("lastLvlExpReq")));
        avatarStats.loadWeaponModifier(Integer.parseInt(dStats.getAttribute("weaponModifier")));
        avatarStats.loadArmorModifier(Integer.parseInt(dStats.getAttribute("armorModifier")));



    }
/*----------------------------------For Saving --------------------------------*/
    //For future use it will include map, items, stats
    public static void save() {
        System.out.println("Saving: " + currentFileName);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            String filePath = "src/res/save_files/" + currentFileName;

            Element mainRootElement = doc.createElementNS(filePath, "Save_File"); //1 will be edited in the feature
            doc.appendChild(mainRootElement);

            // append child elements to root element
            mainRootElement.appendChild(getEntity(doc, avatar));
            mainRootElement.appendChild(getMap(doc, gameMap));

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

        Element pStats = doc.createElement("pStats");
    //-----Primary Stats----
        Attr lives = doc.createAttribute("lives");
        lives.setValue(Integer.toString(stat.getLivesLeft()));
        pStats.setAttributeNode(lives);

        Attr strength = doc.createAttribute("strength");
        strength.setValue(Integer.toString(stat.getStrength()));
        pStats.setAttributeNode(strength);

        Attr agility = doc.createAttribute("agility");
        agility.setValue(Integer.toString(stat.getAgility()));
        pStats.setAttributeNode(agility);

        Attr intellect = doc.createAttribute("intellect");
        intellect.setValue(Integer.toString(stat.getIntellect()));
        pStats.setAttributeNode(intellect);

        Attr hardiness = doc.createAttribute("hardiness");
        hardiness.setValue(Integer.toString(stat.getHardiness()));
        pStats.setAttributeNode(hardiness);

        Attr experience = doc.createAttribute("experience");
        experience.setValue(Integer.toString(stat.getExperience()));
        pStats.setAttributeNode(experience);

        Attr movement = doc.createAttribute("movement");
        movement.setValue(Integer.toString(stat.getMovement()));
        pStats.setAttributeNode(movement);

        stats.appendChild(pStats); //Add pstats into main stats

        Element dStats = doc.createElement("dStats"); //Derived stats
    //------Derived Stats
        Attr level = doc.createAttribute("level");
        level.setValue(Integer.toString(stat.getLevel()));
        dStats.setAttributeNode(level);

        Attr health = doc.createAttribute("health");
        health.setValue(Integer.toString(stat.getHealth()));
        dStats.setAttributeNode(health);

        Attr mana = doc.createAttribute("mana");
        mana.setValue(Integer.toString(stat.getMana()));
        dStats.setAttributeNode(mana);

        Attr offensiveRating = doc.createAttribute("offensiveRating");
        offensiveRating.setValue(Integer.toString(stat.getOffensiveRating()));
        dStats.setAttributeNode(offensiveRating);

        Attr defensiveRating = doc.createAttribute("defensiveRating");
        defensiveRating.setValue(Integer.toString(stat.getDefensiveRating()));
        dStats.setAttributeNode(defensiveRating);

        Attr armorRating = doc.createAttribute("armorRating");
        armorRating.setValue(Integer.toString(stat.getArmorRating()));
        dStats.setAttributeNode(armorRating);

        // -----Other parameters

        Attr expReqLvUp = doc.createAttribute("expReqLvUp");
        expReqLvUp.setValue(Integer.toString(stat.getExpReqLvUp()));
        dStats.setAttributeNode(expReqLvUp);

        Attr lastLvlExpReq = doc.createAttribute("lastLvlExpReq");
        lastLvlExpReq.setValue(Integer.toString(stat.getLastLvlExpReq()));
        dStats.setAttributeNode(lastLvlExpReq);

        Attr maxHealth = doc.createAttribute("maxHealth");
        maxHealth.setValue(Integer.toString(stat.getMaxHealth()));
        dStats.setAttributeNode(maxHealth);

        Attr maxMana = doc.createAttribute("maxMana");
        maxMana.setValue(Integer.toString(stat.getMaxMana()));
        dStats.setAttributeNode(maxMana);

        Attr weaponModifier = doc.createAttribute("weaponModifier");
        weaponModifier.setValue(Integer.toString(stat.getWeaponModifier()));
        dStats.setAttributeNode(weaponModifier);

        Attr armorModifier = doc.createAttribute("armorModifier");
        armorModifier.setValue(Integer.toString(stat.getArmorModifier()));
        dStats.setAttributeNode(armorModifier);



        stats.appendChild(dStats); //Add dstats into main stats

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
            Element areaEffect = doc.createElement("area-effect");

            Attr aType = doc.createAttribute("type");
            aType.setValue(t.getAreaEffect().getType());
            areaEffect.setAttributeNode(aType);
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
