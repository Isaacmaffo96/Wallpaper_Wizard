package org.wallpaperwizardapp.wallpaperwizard.model;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.PathDoesNotExistException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Wallpaper {

    public static final int SPI_SETDESKWALLPAPER = 20;
    public static final int SPIF_UPDATEINIFILE = 0x01;
    public static final int SPIF_SENDCHANGE = 0x02;

    public void setWallpaper(Path imagePath) throws PathDoesNotExistException {

        System.out.println("------------------------------------------------------------------------");
        System.out.println("[Wallpaper] Setting the wallpaper...");

        System.out.println("[Wallpaper] Checking the image path...");

        try {
            if (!Files.exists(imagePath)) {
                throw new PathDoesNotExistException("[Wallpaper] image path does not exist: " + imagePath);
            }
        } catch (Exception e) {
            System.out.println("[Wallpaper] Exception in image path!");
            e.printStackTrace();
            //throw new PathDoesNotExistException("[Wallpaper] image path does not exist: " + imagePath);
        }

        System.out.println("[Wallpaper] Storing the image...");
        ImageStorage imageStorage = new ImageStorage();
        imageStorage.storeImage(imagePath);

        String resourcePath = Wallpaper.class.getResource("/").getPath() + "images/" + imageStorage.getFileName(); // get the resources/image/filename in the file system
        resourcePath = resourcePath.substring(1); // delete the prefix "/"
        System.out.println("[Wallpaper] Resource path: " + resourcePath);

        try {
            if (!Files.exists(Paths.get(resourcePath))) {
                throw new PathDoesNotExistException("[Wallpaper] resources image path does not exist: " + resourcePath);
            }
        } catch (Exception e) {
            System.out.println("[Wallpaper] Exception in resources image path!");
            e.printStackTrace();
            //throw new PathDoesNotExistException("[Wallpaper] resources image path does not exist: " + resourcePath);
        }

        boolean result = User32.INSTANCE.SystemParametersInfo(SPI_SETDESKWALLPAPER, 0, imagePath.toString(), SPIF_UPDATEINIFILE | SPIF_SENDCHANGE);

        if (result)
            System.out.println("[Wallpaper] wallpaper set successfully");
        else
            System.out.println("[Wallpaper] an error occurred:");

        System.out.println("------------------------------------------------------------------------");

    }

    /**
     * The User32 interface is part of the JNA (Java Native Access) library and serves as a Java representation of the
     * Windows User32.dll, which is a dynamic link library responsible for managing user interactions in the Windows
     * operating system. In JNA, interfaces like User32 are used to define mappings for native functions, structures,
     * and constants present in the native libraries like User32.dll, allowing Java code to interact with these native
     * functions directly.
     */
    public interface User32 extends Library {

        User32 INSTANCE = Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        /**
         *
         * @param uiAction Specifies the action to be performed
         * @param uiParam Additional information about the parameter being set or retrieved
         * @param pvParam The parameter value. For setting the wallpaper, this parameter is the path to the image
         * @param fWinIni A set of flags that determine the behavior of the system when setting the parameter
         * @return <code>True</code> if the function successfully performs the specified action,
         * <code>False</code> if there's an error or the function fails to perform the action
         */
        boolean SystemParametersInfo(int uiAction, int uiParam, String pvParam, int fWinIni);
    }
}