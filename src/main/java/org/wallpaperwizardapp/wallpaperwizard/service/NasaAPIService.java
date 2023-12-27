package org.wallpaperwizardapp.wallpaperwizard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wallpaperwizardapp.wallpaperwizard.config.APIKeyConfig;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.JsonHandlerException;
import org.wallpaperwizardapp.wallpaperwizard.util.JsonHandler;

/**
 * Service class that manage the NASA API
 */
@Service
public class NasaAPIService {

    /**
     * provides a high-level API for interacting with RESTful services
     */
    private final RestTemplate restTemplate;

    /**
     * the APIKeyConfig class loads API keys from environment variables
     */
    private final APIKeyConfig apiKeyConfig;

    /**
     * Base Url of the Astronomy Photography Of the Day (APOD)
     */
    private static final String APOD_BASE_URL = "https://api.nasa.gov/planetary/apod";

    @Autowired
    public NasaAPIService(APIKeyConfig apiKeyConfig, RestTemplate restTemplate) {
        this.apiKeyConfig = apiKeyConfig;
        this.restTemplate = restTemplate;
    }

    /**
     * get the Json response of the Astronomy Photography Of the Day from the Nasa API
     * and extract the url of the image
     *
     * @param hd high definition
     * @return url of the image
     * @throws JsonProcessingException
     */
    public String getImageOfTheDayUrl(boolean hd) {
        String apiUrl = APOD_BASE_URL + "?api_key=" + apiKeyConfig.getNasaApiKey();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        // Extract the image URL from the response (JSON parsing)
        String output = "";
        try {
            output = (hd) ?
                    JsonHandler.jsonExtractKey("hdurl", jsonResponse) : // hd url
                    JsonHandler.jsonExtractKey("url", jsonResponse); // no hd url
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            return output;
        }
    }

    /**
     * get the Json response of the Astronomy Photography Of the Day from the Nasa API in HD (default)
     * and extract the url of the image
     *
     * @return url of the image
     * @throws JsonProcessingException
     */
    public String getImageOfTheDayUrl() {
        return getImageOfTheDayUrl(true);
    }

}
