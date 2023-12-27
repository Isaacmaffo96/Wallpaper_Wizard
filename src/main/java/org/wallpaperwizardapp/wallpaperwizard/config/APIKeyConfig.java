package org.wallpaperwizardapp.wallpaperwizard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The APIKeyConfig class loads API keys from environment variables.
 * Other components, such as services or controllers, can inject the APIKeyConfig bean to access the NASA API key
 */
@Configuration
public class APIKeyConfig {

    /**
     * NASA API: nasaApiKey is populated from the property user.nasa.api.key
     */
    @Value("${user.nasa.api.key}")
    private String nasaApiKey;
    // The @Value annotation injects values from properties files or environment variables into Spring beans.

    @Bean
    public APIKeyConfig apiKeyConfig() {
        return new APIKeyConfig();
    }

    /**
     * get the user NASA API key
     *
     * @return the user api key for the NASA API portal
     */
    public String getNasaApiKey() {
        return nasaApiKey;
    }

}
