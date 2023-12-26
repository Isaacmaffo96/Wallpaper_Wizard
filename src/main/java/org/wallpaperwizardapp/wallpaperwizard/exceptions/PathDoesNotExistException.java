package org.wallpaperwizardapp.wallpaperwizard.exceptions;

/**
 * This is a custom exception class named PathDoesNotExistException.
 * It is used to represent a specific exceptional condition in the application.
 */
public class PathDoesNotExistException extends Exception{

    /**
     * Constructs a new PathDoesNotExistException with a specified error message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public PathDoesNotExistException(String message) {
        super(message);
    }
}
