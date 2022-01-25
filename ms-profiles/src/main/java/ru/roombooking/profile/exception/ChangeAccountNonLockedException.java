package ru.roombooking.profile.exception;

public class ChangeAccountNonLockedException extends RuntimeException{
    public ChangeAccountNonLockedException() {
        super();
    }

    public ChangeAccountNonLockedException(String message) {
        super(message);
    }

    public ChangeAccountNonLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeAccountNonLockedException(Throwable cause) {
        super(cause);
    }

    protected ChangeAccountNonLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}