package org.wallpaperwizardapp.wallpaperwizard.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.wallpaperwizardapp.wallpaperwizard.exceptions.JsonHandlerException;

public class JsonHandler {

    /**
     * Extract the value field of the specified key from the JSON response
     *
     * @param key key to extract the value from
     * @param jsonResponse String containing the JSON file
     * @param consoleStamp true for write a JSON string in a pretty human-readable format to the console
     * @return value string of the specified key from the JSON response
     * @throws JsonProcessingException
     * @throws JsonHandlerException
     */
    public static String jsonExtractKey(String key, String jsonResponse, boolean consoleStamp) throws JsonProcessingException, JsonHandlerException {

        if(key == null)
            throw new JsonHandlerException("[JsonHandler] key can't be null");

        if(jsonResponse == null)
            throw new JsonHandlerException("[JsonHandler] jsonResponse can't be null");

        // Create ObjectMapper instance (Jackson's object mapper)
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON string and map it to a JsonNode object
        JsonNode jsonNode = mapper.readTree(jsonResponse);

        // Extract the value field of the specified key from the JSON response
        String value = jsonNode.get(key).asText();

        // if consoleStamp true (or default)
        if(consoleStamp) {
            // write a JSON string in a pretty, human-readable format to the console
            jsonConsoleWriter(jsonNode);
            // write the extracted key value
            System.out.println("[JsonHandler] " + key + ": " + value);
        }

        return value;
    }

    /**
     * Extract the value field of the specified key from the JSON response and
     * write a JSON string in a pretty human-readable format to the console
     *
     * @param key key to extract the value from
     * @param jsonResponse String containing the JSON file
     * @return value string of the specified key from the JSON response
     * @throws JsonProcessingException
     * @throws JsonHandlerException
     */
    public static String jsonExtractKey(String key, String jsonResponse) throws JsonProcessingException, JsonHandlerException {
        return jsonExtractKey(key, jsonResponse, true);
    }

    /**
     *  write a JSON string in a pretty, human-readable format to the console
     *
     * @param jsonNode JSON node instance
     * @throws JsonHandlerException
     */
    public static void jsonConsoleWriter(JsonNode jsonNode) throws JsonHandlerException {

        if(jsonNode==null)
            throw new JsonHandlerException("[JsonHandler] Json node can't be null");

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Enable pretty printing/indentation
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Convert JSON object to a pretty-printed JSON string and print to console
        try {
            String prettyJson = objectMapper.writeValueAsString(jsonNode);
            System.out.println("[JsonHandler] writing a pretty-printed JSON string...");
            System.out.println(prettyJson);
        } catch (Exception e) {
            System.out.println("[JsonHandler] error while writing a pretty-printed JSON string!");
            e.printStackTrace();
        }

    }

    /**
     * write a JSON string in a pretty, human-readable format to the console
     *
     * @param jsonResponse String containing the JSON file
     * @throws JsonProcessingException
     * @throws JsonHandlerException
     */
    public static void jsonConsoleWriter(String jsonResponse) throws JsonProcessingException, JsonHandlerException {

        if(jsonResponse == null)
            throw new JsonHandlerException("[JsonHandler] Json response can't be null");

        // Create ObjectMapper instance (Jackson's object mapper)
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON string and map it to a JsonNode object
        JsonNode jsonNode = mapper.readTree(jsonResponse);

        jsonConsoleWriter(jsonResponse);

    }

}
