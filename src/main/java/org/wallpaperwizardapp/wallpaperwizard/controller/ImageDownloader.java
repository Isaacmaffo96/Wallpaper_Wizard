package org.wallpaperwizardapp.wallpaperwizard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;
import org.wallpaperwizardapp.wallpaperwizard.model.ImageSaver;
import org.wallpaperwizardapp.wallpaperwizard.util.FileHandler;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Spring Boot class to download an image from a given URL.
 */
@RestController
public class ImageDownloader {

    /**
     * RestTemplate to make HTTP requests.
     */
    private RestTemplate restTemplate;

    /**
     * Path of the downloaded image in the file system
     */
    private Path downloadedImagePath;

    /**
     * a class that allows us to store a byte array to a file on the file system
     */
    private ImageSaver imageSaver;

    /**
     * Constructor for ImageDownloader.
     *
     * @param restTemplate RestTemplate to make HTTP requests.
     */
    @Autowired
    ImageDownloader(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.downloadedImagePath = null;
    }

    /**
     * Endpoint to download an image from the specified URL.
     *
     * @return A boolean indicating the success or failure of image download.
     *      <code>true</code>: Image downloaded successfully
     *      <code>false</code>: Failed to download image
     */
    @GetMapping("/downloadImage")
    public boolean downloadImage(String imageUrl, ImageSaver imageSaver) {

        this.imageSaver = imageSaver;

        try {
            if (imageUrl==null)
                throw new PathDoesNotExistException("[ImageDownloader] imageUrl can't be null");
            byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);

            String fileName = "";
            try {
                fileName = FileHandler.getFileNameFromUrl(imageUrl);
                System.out.println("[ImageDownloader] Extracted file name: " + fileName);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (imageBytes != null && fileName != null) {
                this.downloadedImagePath = Paths.get(this.imageSaver.saveToWallpapers(imageBytes, fileName));
                return true; //Image downloaded successfully
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Failed to download image
    }

    /**
     * Get the path of the downloaded image in the file system
     *
     * @return the path of the downloaded image
     */
    public Path getDownloadedImagePath() {
        return downloadedImagePath;
    }

}
