package geektime.unjunable.common.framework;

public class BusinessException extends RuntimeException {
    public BusinessException(String errMessage) {
        super(errMessage);
    }
}
