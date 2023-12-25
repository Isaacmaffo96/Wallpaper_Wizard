package org.wallpaperwizardapp.wallpaperwizard;

import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;
import org.wallpaperwizardapp.wallpaperwizard.model.Wallpaper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Main] Wallpaper Wizard Started");

        //Path imagePath = Paths.get("C:\\Users\\isaac\\Pictures\\Cars Theme\\Leclerc2021Turkish.jpg");
        Path imagePath = Paths.get("C:\\Users\\isaac\\Pictures\\Cars Theme\\Leclerc2022Monza.jpg");


        Wallpaper wallpaper = new Wallpaper();
        try {
            wallpaper.setWallpaper(imagePath);
        } catch (PathDoesNotExistException e) {
            e.printStackTrace();
        }

    }
}