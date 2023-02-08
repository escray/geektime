package geektime.unjunable.domain.common.exception;

public class DirtyDataException extends RuntimeException {
    public DirtyDataException(String errMessage) {
        super(errMessage);
    }
}
