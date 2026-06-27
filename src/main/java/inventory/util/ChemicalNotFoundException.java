package inventory.util;

public class ChemicalNotFoundException extends InventoryException{
    public ChemicalNotFoundException(String message) {
        super(message);
    }
    public ChemicalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
