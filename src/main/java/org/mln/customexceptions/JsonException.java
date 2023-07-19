package org.mln.customexceptions;

/**
 * This class is a custom exception class that extends the CustomException class
 */
public class JsonException extends CustomException{

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
    public JsonException(Throwable cause) {
        super("There is some json exception" + cause);
    }
}
