package org.mln.customexceptions;

/**
 * This class is used to throw an exception when the property file is not found or is not in the correct format
 */
public class  PropertyFileUsageException extends CustomException{
    public PropertyFileUsageException(String message) {
        super(message);
    }

    public PropertyFileUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
