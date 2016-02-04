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

def generate(mapSizeX, mapSizeY, numItems, outputFileName):
    root = Element("map")
    root.set('width', str(mapSizeX))
    root.set('height', str(mapSizeY))

    for x in range(0, mapSizeX):
        row = SubElement(root, "row")
        for y in range(0, mapSizeY):
            element = SubElement(row, "tile")
            terrain = SubElement(element, "terrain")
            terrain.set("type", getTerrainType())

    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))

if __name__ == "__main__":

    # mapSizeX, mapSizeY, percentageGrass, numItems

    mapSizeX = int(sys.argv[1])
    mapSizeY = int(sys.argv[2])
    numItems = int(sys.argv[3])
    outputFileName = sys.argv[4]

    if (mapSizeX <= 0 or mapSizeY <= 0):
        print "The map size can not be negative"
        exit()

    if (numItems > mapSizeX * mapSizeY):
        print "There are too many items for the current map size"
        exit()

    generate(mapSizeX, mapSizeY, numItems, outputFileName)