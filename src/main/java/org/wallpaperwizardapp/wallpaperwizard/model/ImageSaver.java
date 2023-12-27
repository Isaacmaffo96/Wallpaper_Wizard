package org.wallpaperwizardapp.wallpaperwizard.model;

import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A class that store a byte array to a file on the pc
 */
public class ImageSaver {

    /**
     * Saves the image byte array to a file.
     *
     * @param imageBytes The byte array of the image.
     * @param downloadedImagePath  The path of the file where to save the image.
     * @throws IOException If an I/O error occurs while saving the image.
     */
    public static void saveImageToFile(byte[] imageBytes, String downloadedImagePath) throws IOException {

        System.out.println("[ImageSaver] Saving te image to:  " + downloadedImagePath + " ...");

        try (FileOutputStream fos = new FileOutputStream(downloadedImagePath)) {
            fos.write(imageBytes);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * save the downloaded image in the 'Documents/Wallpapers' directory
     *
     * @param imageBytes The byte array of the image.
     * @param fileName the name of the image
     * @return the path of the downloaded image
     */
    public static String saveToWallpapers(byte[] imageBytes, String fileName) throws IOException, PathDoesNotExistException {

        String home = System.getProperty("user.home"); // user's home directory (user.home)
        String documentsPath = home + File.separator + "Documents" + File.separator + "Wallpapers";
        System.out.println("[ImageSaver] Wallpapers path: " + documentsPath);

        // Create the directory if it doesn't exist
        File wallpapersDir = new File(documentsPath);
        if (!wallpapersDir.exists()) {
            boolean created = wallpapersDir.mkdirs();
            if (!created) // problem with creating the directory
                throw new PathDoesNotExistException("[ImageSaver] Failed to create directory for wallpapers");
        }

        String downloadedImagePath = documentsPath + File.separator + fileName ;

        // Saves the image byte array to a file.
        saveImageToFile(imageBytes,downloadedImagePath);

        System.out.println("[ImageSaver] Image saved to: " + downloadedImagePath);

        return downloadedImagePath;
    }

}
