package inventory.exception;

public class InvalidChemicalException extends InventoryException{
    public InvalidChemicalException(String message) {
        super(message);
    }

    public InvalidChemicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
