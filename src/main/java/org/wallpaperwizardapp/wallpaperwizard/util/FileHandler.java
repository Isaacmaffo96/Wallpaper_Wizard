package org.wallpaperwizardapp.wallpaperwizard.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileHandler {


    /**
     * extract the fileName from the url
     *
     * @param url complete url of the image
     * @return the file name
     * @throws URISyntaxException
     */
    public static String getFileNameFromUrl(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return Paths.get(uri.getPath()).getFileName().toString();
    }

}
