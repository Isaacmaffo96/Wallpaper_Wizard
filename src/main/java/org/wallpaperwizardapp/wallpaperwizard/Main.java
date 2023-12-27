package org.wallpaperwizardapp.wallpaperwizard;

import org.springframework.context.ConfigurableApplicationContext;
import org.wallpaperwizardapp.wallpaperwizard.controller.ImageDownloader;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;
import org.wallpaperwizardapp.wallpaperwizard.model.ImageResizer;
import org.wallpaperwizardapp.wallpaperwizard.model.ImageSaver;
import org.wallpaperwizardapp.wallpaperwizard.model.Wallpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wallpaperwizardapp.wallpaperwizard.service.NasaAPIService;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("[Main] Wallpaper Wizard Started");

        // Get the NASAImageService bean
        NasaAPIService nasaImageService = context.getBean(NasaAPIService.class);

        // Get the ImageDownloader bean
        ImageDownloader imageDownloader = context.getBean(ImageDownloader.class);

        // Get te i mage of the day URL
        String imageUrl = nasaImageService.getImageOfTheDayUrl(true);

        // Print the extracted image URL
        System.out.println("[Main] Image URL: " + imageUrl);

        // Construct a Class to Store te image
        ImageSaver imageSaver = new ImageSaver();

        // Download the image
        imageDownloader.downloadImage(imageUrl, imageSaver);

        // Resize the image
        // lockScale: True doesn't change the scale, false change the scale to match the screen sizes
        ImageResizer.resizeImage(imageSaver.getDownloadedImagePathString(), false);

        // Set the wallpaper
        Wallpaper wallpaper = new Wallpaper();
        try {
            wallpaper.setWallpaper(imageDownloader.getDownloadedImagePath());
        } catch (PathDoesNotExistException e) {
            e.printStackTrace();
        }

        System.out.println("[Main] Closing Wallpaper Wizard...");
        System.out.println("------------------------------------------------------------------------");

        // Close the Spring context
        context.close();

    }
}