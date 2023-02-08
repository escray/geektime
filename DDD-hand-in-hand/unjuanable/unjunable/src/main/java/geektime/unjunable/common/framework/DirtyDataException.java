package geektime.unjunable.common.framework;

public class DirtyDataException extends RuntimeException {
    public DirtyDataException(String errMessage) {
        super(errMessage);
    }
}
