package cn.cst.exception;

public class LoginException extends RuntimeException{

    private String code;
    private String message;

    public LoginException(String message, String code ) {
        super(message);
        this.code = code;
        this.message = message;
    }


}
