package utilities;

import javax.swing.*;

/**
 * Created by aseber
 * on 2/9/16.
 */

/*
 * The purpose of this class is to hold default functions that are used throughout the program.
 * I've included our file system dependent path setter, and a function for getting pictures.
 * The purpose for including a picture getter is so that if the image does not exist,
 * the program has a way to complain and also display what is wrong with a red "X"
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
