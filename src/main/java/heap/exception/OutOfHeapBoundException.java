package heap.exception;

public class OutOfHeapBoundException extends Exception{
    public OutOfHeapBoundException() {
        super();
    }

    public OutOfHeapBoundException(String message) {
        super(message);
    }

    public OutOfHeapBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfHeapBoundException(Throwable cause) {
        super(cause);
    }

    protected OutOfHeapBoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
