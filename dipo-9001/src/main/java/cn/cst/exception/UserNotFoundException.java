package cn.cst.exception;

public class UserNotFoundException extends RuntimeException{

    private String code;
    private String message;

    public UserNotFoundException(String code, String message ) {
        super(message);
        this.code = code;
        this.message = message;
    }


}
