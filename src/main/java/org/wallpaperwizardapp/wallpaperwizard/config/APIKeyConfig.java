package org.wallpaperwizardapp.wallpaperwizard.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * the APIKeyConfig class loads API keys from environment variables
 */
@Configuration
public class APIKeyConfig {

    /**
     * NASA API
     */
    @Value("${user.nasa.api.key}")
    private String nasaApiKey;

    /**
     * get the user NASA API key
     *
     * @return the user api key for the NASA API portal
     */
    public String getNasaApiKey() {
        return nasaApiKey;
    }

}
