import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Bradley on 2/1/16.
 */
public class Map extends Observable{

    public Tile[][] tiles;
    private int mapWidth;
    private int mapHeight;
    private final String DEFAULT_MAP = "./src/res/maps/default_map.xml";
    private int changedX;
    private int changedY;

    public Map(){
        initMap();
    }

    private void initMap(){

        try{
            // Get the xml filepath string ensuring file separators are specific to the use's OS.
            String filepath = DEFAULT_MAP.replaceAll("\\\\|/", "\\"+System.getProperty("file.separator"));

            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            // Normalize
            doc.getDocumentElement().normalize();

            // Get the width and height of the map
            mapWidth = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
            mapHeight = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));

            // Create an empty array of tiles
            tiles = new Tile[mapHeight][mapWidth];

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
                    NodeList areaEffectNodes = tileElement.getElementsByTagName("areaEffect");
                    if(areaEffectNodes.getLength() > 0){
                        Element areaEffectElement = (Element) areaEffectNodes.item(0);
                        String areaEffectType = areaEffectElement.getAttribute("type");
                        int areaEffectStatsModifier = Integer.parseInt(areaEffectElement.getAttribute("statsModifier"));
                        areaEffect = new AreaEffect(areaEffectType, areaEffectStatsModifier);
                    }

                    // Get the item if there is one
                    NodeList itemNodes = tileElement.getElementsByTagName("item");
                    if(itemNodes.getLength() > 0){
                        Element itemElement = (Element) itemNodes.item(0);
                        String itemType = itemElement.getAttribute("type");
                        String itemDescription = itemElement.getAttribute("description");
                        String itemName = itemElement.getAttribute("name");
                        item = new Item(itemName, itemType, itemDescription, 1);
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
            }
        }catch(SAXParseException e){
            System.out.println("Error parsing");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Terrain getTerrainAtLocation(int x, int y){
        return tiles[y][x].getTerrain();
    }

    public AreaEffect getAreaEffectAtLocation(int x, int y){
        return tiles[y][x].getAreaEffect();
    }

    public Item getItemAtLocation(int x, int y){
        return tiles[y][x].getItem();
    }

    public Entity getEntityAtLocation(int x, int y){
        return tiles[y][x].getEntity();
    }

    //Mutator functions to tiles notify AreaViewport
    public void insertItemAtLocation(int x, int y, Item item){
        tiles[y][x].addItem(item);
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void insertEntityAtLocation(int x, int y, Entity entity){
        tiles[y][x].addEntity(entity);
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void removeItemFromLocation(int x, int y){
        tiles[y][x].removeItem();
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public void removeEntityFromLocation(int x, int y){
        tiles[y][x].removeEntity();
        this.setChanged();
        this.changedX = x;
        this.changedY = y;
        this.notifyObservers();
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public int getMapHeight(){
        return mapHeight;
    }

    //TODO: Remove this after debugging
    public static void main(String[] args){
        Map m = new Map();
    }
}
