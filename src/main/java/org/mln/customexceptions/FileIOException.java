package org.mln.customexceptions;

/**
 * This class is a custom exception class that extends the CustomException class. It is used to throw an exception when
 * there is an error in reading or writing to a file
 */
public class FileIOException extends CustomException{
    public FileIOException(String path ,String message) {
        super(message + " :" + path);
    }

    public FileIOException(String path,String message, Throwable cause) {
        super(message + " :" + path, cause);
    }
    public FileIOException(String path) {
        super("File IO Exception occurred in" + " :" + path +".Please check.");
    }
}
