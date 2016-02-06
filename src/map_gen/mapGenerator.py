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

# <map width="30" height="30">
#     <row>
#         <tile>
#             <terrain type="mountain"></terrain>
#             <areaEffect type="take-damage" statsModifier="10"></areaEffect>
#         </tile>
#         <tile>
#             <terrain type="grass"></terrain>
#             <item description="a mini skirt for jorge in the jungle" type="take-able" name="iron-sword"></item>
#         </tile>
#     </row>
# </map>

def getTerrainType():
    terrainTypeList = [
    ("grass", 0.0, 0.8),
    ("mountain", 0.8, 0.9),
    ("sea", 0.9, 1.0)
    ]
    randFloat = random.random()
    for terrainType in terrainTypeList:
        if (randFloat >= terrainType[1] and randFloat < terrainType[2]):
            return terrainType[0]

def getItemType(numItemsToAdd, mapSize, totalItemCount):
    if (random.random() < (float(numItemsToAdd) / float(mapSize))):
        return random.randint(0, totalItemCount - 1)

    return -1;

def generate(mapSizeX, mapSizeY, numItemsToAdd, totalItemCount, outputFileName):
    itemCount = 0

    root = Element("map")
    root.set('width', str(mapSizeX))
    root.set('height', str(mapSizeY))

    for x in range(0, mapSizeX):
        row = SubElement(root, "row")
        for y in range(0, mapSizeY):
            element = SubElement(row, "tile")

            terrain = SubElement(element, "terrain")
            terrainType = getTerrainType()
            terrain.set("type", terrainType)

            if (itemCount < numItemsToAdd):
                itemType = getItemType(numItemsToAdd, mapSizeX * mapSizeY, totalItemCount)

                if (itemType != -1):
                    item = SubElement(element, "item")
                    item.set("id", str(itemType))
                    item.set("type", "take-able")


    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))


def getTerrainType(x, y):
    if (x % 6 == 0 and y > 4  and y < 10):
        return "mountain"
    else:
        return "grass"

if __name__ == "__main__":

    # mapSizeX, mapSizeY, numItemsToAdd, totalItemCount, outputFileName

    if (len(sys.argv) < 6):
        print "Invalid number of parameters"
        exit()

    mapSizeX = int(sys.argv[1])
    mapSizeY = int(sys.argv[2])
    numItemsToAdd = int(sys.argv[3])
    totalItemCount = int(sys.argv[4])
    outputFileName = sys.argv[5]

    if (mapSizeX <= 0 or mapSizeY <= 0):
        print "The map size can not be negative"
        exit()

    if (numItemsToAdd > mapSizeX * mapSizeY):
        print "There are too many items for the current map size"
        exit()

    generate(mapSizeX, mapSizeY, numItemsToAdd, totalItemCount, outputFileName)
