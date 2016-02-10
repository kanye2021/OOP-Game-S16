package utilities;

import javax.swing.*;

/**
 * Created by aseber on 2/9/16.
 */
public class Utilities {

    private static final String placeholderImage = Utilities.getFileSystemDependentPath("./src/res/placeholder.png");

    public static String getFileSystemDependentPath(String string) {

        return string.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));

    }

    public static ImageIcon getImageIcon(String string) {

        ImageIcon image = new ImageIcon(string);

        if (image.getIconWidth() == -1 || image.getIconHeight() == -1) {

            System.out.println("Bad Image Path: " + string);
            image = new ImageIcon(placeholderImage);

        }

        return image;

    }

}
