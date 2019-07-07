package model.exception;

public class FullHashMapException extends Exception {
    public FullHashMapException(String s) {
        super(s);
    }

    public FullHashMapException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
