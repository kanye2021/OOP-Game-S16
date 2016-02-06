import os
import sys
from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom
import random
import time

"""
    Return a pretty-printed XML string for the Element.
"""
def prettify(data):
    rough_string = ElementTree.tostring(data, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="    ")

# Returns the terrain type corresponding to the txt file
def getTerrain(content, mapWidth, mapHeight):
    terrain = {}
    for i, row in enumerate(content):
        for j in range(0, mapWidth):
            char = row[j]
            if(char == "g"):
                terrain[i, j] = "grass"
            elif(char == "m"):
                terrain[i, j] = "mountain"
            elif(char == "s"):
                terrain[i, j] = "sea"
            else:
                print("WTF why didnt that work?")

    # print terrain[20][13]
    return terrain

# Returns an integer representing the type of item (position in the enum)
def getItemType(numItemsToAdd, mapSize, totalItemCount):
    if (random.random() < (float(numItemsToAdd) / float(mapSize))):
        return random.randint(0, totalItemCount - 1)

    return -1

# Returns the type of area effect
def getAreaEffectType(mapSize, numAreaEffectsToAdd):
    # Generate a random number (the probability of success is based on the numAreaEffectsToAdd and mapSize)
    if(random.random() < (float(numAreaEffectsToAdd) / float(mapSize))):
        i = random.randint(0, 4)
        if(i==0):
            return "level-up"
        elif(i==1):
            return "heal-damage"
        elif(i==2):
            return "take-damage"
        elif(i==3):
            return "instant-death"
    return -1

# Generate the XML file
def generate(mapSizeX, mapSizeY, terrainArray, numItemsToAdd, totalItemCount, numAreaEffectsToAdd, outputFileName):
    root = Element("map")
    root.set('width', str(mapSizeX))
    root.set('height', str(mapSizeY))

    for x in range(0, mapSizeX):
        row = SubElement(root, "row")
        for y in range(0, mapSizeY):
            element = SubElement(row, "tile")

            # Set the terrain
            terrain = SubElement(element, "terrain")
            terrainType = terrainArray[x, y]
            terrain.set("type", terrainType)

            # Add an item (if applicable)
            itemType = getItemType(numItemsToAdd, mapSizeX * mapSizeY, totalItemCount)
            if (itemType != -1):
                item = SubElement(element, "item")
                item.set("id", str(itemType))
                item.set("type", "take-able")

            # Add an area effec (if applicable)
            areaEffectType = getAreaEffectType(mapSizeX * mapSizeY, numAreaEffectsToAdd)
            if(areaEffectType != -1):
                areaEffect = SubElement(element, "area-effect")
                areaEffect.set("type", areaEffectType)

    # Write to the file
    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))


if __name__ == "__main__":
    #read the map file
    with open("map.txt") as f:
        content = f.readlines()

    # Extract the width and height from the first two lines and remove them from the list
    mapWidth = int(content[0])
    mapHeight = int(content[1])
    content.pop(0)
    content.pop(0)

    # Get the terrain from the file
    terrain = getTerrain(content, mapWidth, mapHeight);

    # TODO: Generate items and area effects in the same way (non random in a text file)

    # Generate the xml file
    generate(mapWidth, mapHeight, terrain, 10, 7, 40, "generated_map")
    print "Map successfully created!"
