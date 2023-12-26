package org.wallpaperwizardapp.wallpaperwizard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wallpaperwizardapp.wallpaperwizard.config.APIKeyConfig;

@Service
public class NasaAPIService {

    private final RestTemplate restTemplate;

    private static final String APOD_BASE_URL = "https://api.nasa.gov/planetary/apod";

    /**
     * the APIKeyConfig class loads API keys from environment variables
     */
    private APIKeyConfig apiKeyConfig;

    @Autowired
    public NasaAPIService(APIKeyConfig apiKeyConfig, RestTemplate restTemplate) {
        this.apiKeyConfig = apiKeyConfig;
        this.restTemplate = restTemplate;
    }

    public String getImageOfTheDayUrl() throws JsonProcessingException {
        String apiUrl = APOD_BASE_URL + "?api_key=" + apiKeyConfig.getNasaApiKey();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        System.out.println("json response: " + jsonResponse);
        return extractImageUrl(jsonResponse);
    }

    /**
     *  Extract the image URL from the response (JSON parsing)
     *
     * @param jsonResponse
     * @return
     */
    private String extractImageUrl(String jsonResponse) throws JsonProcessingException {

        // Create ObjectMapper instance (Jackson's object mapper)
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON string and map it to a JsonNode object
        JsonNode jsonNode = mapper.readTree(jsonResponse);

        // Extract the "url" field from the JSON response
        String imageUrl = jsonNode.get("url").asText();

        System.out.println("Image URL: " + imageUrl);

        return imageUrl;
    }

}
