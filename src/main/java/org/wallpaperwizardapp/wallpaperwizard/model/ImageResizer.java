package org.wallpaperwizardapp.wallpaperwizard.model;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;

/**
 * This class is responsible for resizing the image
 */
public class ImageResizer {

    /**
     * Reads an image from a specified path, calculates the scaling factors required to fit the screen size,
     * and resizes the image accordingly.
     *
     * @param imagePath path of the image
     * @param lockScale true doesn't change the scale, false change the scale to match the screen sizes
     */
    public static void resizeImage(String imagePath, boolean lockScale) {

        Integer [] screenSize = screenSizeGetter();
        int screenWidth = screenSize[0]; // screen width
        int screenHeight = screenSize[1]; // screen height

        int newWidth = screenWidth;
        int newHeight = screenHeight;

        try {

            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            if (lockScale){
                // Calculate scaling factors for resizing
                double scaleX = (double) screenWidth / originalImage.getWidth();
                double scaleY = (double) screenHeight / originalImage.getHeight();
                double scale = Math.min(scaleX, scaleY);

                // Calculate new dimensions
                newWidth = (int) (originalImage.getWidth() * scale);
                newHeight = (int) (originalImage.getHeight() * scale);
            }

            // Create a new BufferedImage with the new dimensions
            Image resultingImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(resultingImage, 0, 0, null);
            g2d.dispose();

            // Save the resized image to a file
            File output = new File(imagePath);
            // overwrite the current image
            ImageIO.write(resizedImage, "jpg", output);

            System.out.println("[ImageResizer] Image resized successfully in: " + imagePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an image from a specified path, calculates the scaling factors required to fit the screen size,
     * and resizes the image accordingly.
     *
     * @param imagePath path of the image
     */
    public static void resizeImage(String imagePath) {
        resizeImage(imagePath, true);
    }

    /**
     * Retrieve the screen width and height of the user's display
     *
     * @return Integer array [screenWidth, screenHeight]
     */
    public static Integer[] screenSizeGetter() {

        // default values
        int screenWidth = 1920;
        int screenHeight = 1080;

        try {
            /*
            // single monitor configuration
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            screenWidth = screenSize.width;
            screenHeight = screenSize.height;
            */

            ///*
            // multi-monitor configuration
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            screenWidth = gd.getDisplayMode().getWidth();
            screenHeight = gd.getDisplayMode().getHeight();
            //*/
        }
        catch (Exception e){
            System.out.println("[ImageResizer] Exception in retrieving the screen width and height of the user's display");
            e.printStackTrace();
        }

        System.out.println("[ImageResizer] Screen Width: " + screenWidth);
        System.out.println("[ImageResizer] Screen Height: " + screenHeight);

        return new Integer[]{screenWidth, screenHeight};
    }

}
