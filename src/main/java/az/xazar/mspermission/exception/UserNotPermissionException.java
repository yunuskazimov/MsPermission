package az.xazar.mspermission.exception;

public class UserNotPermissionException extends RuntimeException {
    public UserNotPermissionException(String msg) {
        super(msg);
    }
}
