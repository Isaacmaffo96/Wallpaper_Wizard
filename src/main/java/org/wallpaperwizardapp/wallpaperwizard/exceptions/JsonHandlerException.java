package org.wallpaperwizardapp.wallpaperwizard.exceptions;

/**
 * This is a custom exception class named JsonHandlerException.
 * It is used to represent a specific exceptional condition in the application.
 */
public class JsonHandlerException extends Exception{

    /**
     * Constructs a new JsonHandlerException with a specified error message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public JsonHandlerException(String message) {
        super(message);
    }

}
