package com.exception;

public class ArtWorkNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ArtWorkNotFoundException with a default message.
     */
    public ArtWorkNotFoundException() {
        // Printing a message when the exception is thrown
        System.out.println("The artwork is not found");
    }

    /**
     * Provides a string representation of this exception.
     * @return A string representation of this exception.
     */
    @Override
    public String toString() {
        // Returning a custom message when this exception is converted to string
        return "Artwork not found";
    }
}
