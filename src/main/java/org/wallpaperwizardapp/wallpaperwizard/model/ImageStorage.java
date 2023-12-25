package org.wallpaperwizardapp.wallpaperwizard.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class ImageStorage {

    // Specify the target directory within the resources folder
    private final String targetDirectory = "src/main/resources/images/";
    private Path imagePath;
    String fileName;

    public ImageStorage() {
        this.imagePath = null;
        this.fileName = null;
    }

    private void deleteStoredImage(){

        System.out.println("[ImageStorage] Deleting the directory: " + targetDirectory);

        try {
            Path directory = Paths.get(targetDirectory);

            // Perform recursive deletion of the directory and its contents
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

            System.out.println("[ImageStorage] Target directory and its contents deleted successfully.");
        } catch (IOException e) {
            System.out.println("[ImageStorage] Failed to delete the target directory!");
            e.printStackTrace();
        }
    }

    public void storeImage(Path sourcePath) {

        deleteStoredImage();

        System.out.println("[ImageStoring] Storing the image in: " + targetDirectory + "...");

        try {
            // Read the image file
            //Path sourcePath = Paths.get(imagePath);
            byte[] imageData = Files.readAllBytes(sourcePath);

            // Create the target directory if it doesn't exist
            Path directoryPath = Paths.get(targetDirectory);
            Files.createDirectories(directoryPath);

            // Determine the target file path
            this.fileName = sourcePath.getFileName().toString();
            Path targetFilePath = directoryPath.resolve(fileName);

            // Write the image data to the target file
            Files.write(targetFilePath, imageData);

            System.out.println("[ImageStorage] Image successfully stored in: " + targetFilePath);
            this.imagePath = targetFilePath;
        } catch (IOException e) {
            System.out.println("[ImageStorage] Failed to store the image!");
            e.printStackTrace();
        }
    }

    public Path getImagePath() {
        return imagePath;
    }

    public String getFileName() {
        return fileName;
    }

}
