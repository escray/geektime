package geektime.unjunable.domain.common.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String errMessage) {
        super(errMessage);
    }
}
