package kasei.springmvc.config.exceptionhandler;

/**
 * 该异常的主要作用是用于演示  {@link MyExceptionResolver} 的工作原理
 * */
public class MyExceptionResolverException extends RuntimeException {
    
    public MyExceptionResolverException(String message) {
        super(message);
    }

    public MyExceptionResolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyExceptionResolverException(Throwable cause) {
        super(cause);
    }
}
