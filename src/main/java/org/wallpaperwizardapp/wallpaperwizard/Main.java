package org.wallpaperwizardapp.wallpaperwizard;

import org.springframework.context.ConfigurableApplicationContext;
import org.wallpaperwizardapp.wallpaperwizard.controller.ImageDownloader;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;
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

        String imageUrl = nasaImageService.getImageOfTheDayUrl(true);

        ImageDownloader imageDownloader = context.getBean(ImageDownloader.class);

        // Print the extracted image URL
        System.out.println("[Main] Image URL: " + imageUrl);

        imageDownloader.downloadImage(imageUrl);

        Wallpaper wallpaper = new Wallpaper();
        try {
            wallpaper.setWallpaper(imageDownloader.getDownloadedImagePath());
        } catch (PathDoesNotExistException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------------------------------------------");

        // Close the Spring context
        context.close();

    }
}