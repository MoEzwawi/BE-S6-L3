package MoEzwawi.BES6L2.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id) {
        super("Element id "+id+" not found");
    }
}
