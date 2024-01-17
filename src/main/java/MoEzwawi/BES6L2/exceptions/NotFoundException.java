package MoEzwawi.BES6L2.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Element not found");
    }
}
