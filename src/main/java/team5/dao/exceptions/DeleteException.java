package team5.dao.exceptions;

/**
 * This exception signalize failure of the delete operation in the database
 * @author EugeneDunin
 * @version 1.0
 */
public class DeleteException extends Exception {
    /**
     *
     * @param message short information about exception
     */
    public DeleteException(final String message) {
        super(message);
    }

    /**
     * Empty constructor
     */
    public DeleteException() {}
}
