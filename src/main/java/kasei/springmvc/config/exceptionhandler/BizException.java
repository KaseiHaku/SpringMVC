package kasei.springmvc.config.exceptionhandler;

/**
 * 所有业务异常的基类
 * @ResponseStatus 用于表示当系统返回当前类型的异常时，怎么返回给前端
 * */
public class BizException extends RuntimeException {
    
    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
