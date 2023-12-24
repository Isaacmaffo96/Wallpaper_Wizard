package org.wallpaperwizardapp.wallpaperwizard.model;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public class Wallpaper {

    public void setWallpaper() {
        try {
            User32.INSTANCE.SystemParametersInfo(0x0014, 0, "C:\\Users\\isaac\\Pictures\\Cars Theme\\Leclerc2022Monza.jpg", 1);
            System.out.println("wallpaper set successfully");
        } catch (Exception e) {
            System.out.println("an error occurred:");
            e.printStackTrace();
        }
    }

    public interface User32 extends Library {
        User32 INSTANCE = Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        void SystemParametersInfo(int one, int two, String s, int three); // or boolean
    }
}