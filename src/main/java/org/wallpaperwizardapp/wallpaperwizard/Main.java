package org.wallpaperwizardapp.wallpaperwizard;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ConfigurableApplicationContext;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;
import org.wallpaperwizardapp.wallpaperwizard.model.Wallpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wallpaperwizardapp.wallpaperwizard.service.NasaAPIService;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        //SpringApplication.run(Main.class, args);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("[Main] Wallpaper Wizard Started");

        Path imagePath = Paths.get("C:\\Users\\isaac\\Pictures\\Cars Theme\\Leclerc2021Turkish.jpg");
        //Path imagePath = Paths.get("C:\\Users\\isaac\\Pictures\\Cars Theme\\Leclerc2022Monza.jpg");

        Wallpaper wallpaper = new Wallpaper();
        try {
            wallpaper.setWallpaper(imagePath);
        } catch (PathDoesNotExistException e) {
            e.printStackTrace();
        }

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // Get the NASAImageService bean
        NasaAPIService nasaImageService = context.getBean(NasaAPIService.class);

        String imageUrl = null;
        try {
            imageUrl = nasaImageService.getImageOfTheDayUrl();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Print the extracted image URL
        System.out.println("Image URL: " + imageUrl);

        // Close the Spring context
        context.close();

    }
}