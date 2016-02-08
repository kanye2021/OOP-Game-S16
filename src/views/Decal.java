/**
 * Created by aseber
 * on 2/8/16.
 */

package views;

public class Decal {

    public enum Types {

        RED_CROSS("red-cross.png"),
        SKULL_CROSSBONES("skull-and-crossbones.png"),
        GOLD_STAR("gold-star.png");

        String pathToFile;

        Types(String pathToFile) {

            this.pathToFile = pathToFile;

        }

        public String getPathToFile() {

            return pathToFile;

        }

    }

    private int id;

    public Decal(Types type) {

        id = type.ordinal();

    }

    public int getID() {

        return id;

    }

    public String getPathToFile() {

        return Types.values()[getID()].getPathToFile();

    }

}
