package com.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFoundException with a default message.
     */
    public UserNotFoundException() {
        // Printing a message when the exception is thrown
        System.out.println("The user is not found");
    }

    /**
     * Constructs a new UserNotFoundException with a custom message.
     * @param message The custom message to be displayed.
     */
    public UserNotFoundException(String message) {
        // Printing a custom message when the exception is thrown
        System.out.println(message);
    }

    /**
     * Provides a string representation of this exception.
     * @return A string representation of this exception.
     */
    @Override
    public String toString() {
        // Returning a custom message when this exception is converted to string
        return "User not found";
    }

}
