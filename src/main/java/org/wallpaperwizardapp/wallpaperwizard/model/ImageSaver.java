package org.wallpaperwizardapp.wallpaperwizard.model;

import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that store a byte array to a file on the pc
 */
public class ImageSaver {

    /**
     * path of the downloaded image
     */
    private String downloadedImagePath;

    /**
     * save the downloaded image in the 'Documents/Wallpapers' directory
     *
     * @param imageBytes The byte array of the image.
     * @param fileName the name of the image
     * @return the path of the downloaded image
     */
    public String saveToWallpapers(byte[] imageBytes, String fileName) throws IOException, PathDoesNotExistException {

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

        this.downloadedImagePath = documentsPath + File.separator + fileName ;

        // Saves the image byte array to a file.
        saveImageToFile(imageBytes);

        System.out.println("[ImageSaver] Image saved to: " + downloadedImagePath);

        return downloadedImagePath;
    }

    public Path getDownloadedImagePath() {
        return Paths.get(downloadedImagePath);
    }

    public String getDownloadedImagePathString() {
        return downloadedImagePath;
    }

    /**
     * Saves the image byte array to a file.
     *
     * @param imageBytes The byte array of the image.
     * @throws IOException If an I/O error occurs while saving the image.
     */
    private void saveImageToFile(byte[] imageBytes) throws IOException {

        System.out.println("[ImageSaver] Saving te image to:  " + this.downloadedImagePath + " ...");

        try (FileOutputStream fos = new FileOutputStream(this.downloadedImagePath)) {
            fos.write(imageBytes);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
